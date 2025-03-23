package rpg.battle;

import rpg.character.base.Character;
import rpg.monster.Monster;
import rpg.skill.Skill;
import static rpg.util.IO_Manager.*;

import java.util.*;

public class BattleSystem {
    private List<Character> playerParty;
    private List<Monster> enemies;
    private List<Combatant> combatants;
    private Map<Combatant, BattleAction> turnActions;  // 각 전투 참가자의 행동을 저장
    private int turnCount;

    public BattleSystem() {
        this.playerParty = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.combatants = new ArrayList<>();
        this.turnActions = new LinkedHashMap<>();
        this.turnCount = 0;
    }

    public void startBattle(List<Character> party, List<Monster> enemies) {
        this.playerParty = party;
        this.enemies = enemies;
        
        print("전투 시작!", true);
        battleLoop();
    }

    private void battleLoop() {
        while (!isBattleEnd()) {
            // 1. 턴 시작
            print("\n=== 새로운 턴 시작 ===", true);
            turnActions.clear();
            turnCount++;
            print("턴 수: " + turnCount, true);

            // 2. 전체 전투 참가자 추가
            for (Character character : playerParty) {
                if (character.isAlive()) {
                    combatants.add(character);
                }
            }
            for (Monster enemy : enemies) { 
                if (enemy.isAlive()) {
                    combatants.add(enemy);
                }
            }
            // 3. 속도에 따라 정렬
            Collections.sort(combatants, (a, b) -> b.getSpeed() - a.getSpeed());

            // 4. 행동 정하기
            for (Combatant combatant : combatants) {
                if (combatant instanceof Character) {
                    BattleAction action = getPlayerAction((Character) combatant);
                    turnActions.put(combatant, action);
                } else {
                    BattleAction action = getEnemyAction((Monster) combatant);
                    turnActions.put(combatant, action);
                }
            }

            // 5. 행동 실행 (속도나 우선순위에 따라 정렬 가능)
            executeTurnActions();
        }

        announceResult();
    }

    private BattleAction getPlayerAction(Character character) {
        print("\n" + character.getName() + "의 행동을 선택하세요:", true);
        print("1. 공격", true);
        print("2. 방어", true);
        print("3. 스킬", true);
        print("4. 아이템", true);
        
        int choice = Integer.parseInt(input("선택: "));
        Combatant target = null;

        switch (choice) {
            case 1:
                target = selectTarget(enemies);
                return new BattleAction(ActionType.ATTACK, character, target);
            case 2:
                return new BattleAction(ActionType.DEFEND, character, character);
            case 3:
                Skill skill = selectSkill(character);
                target = selectTarget(enemies);
                return new BattleAction(ActionType.SKILL, character, target, skill);
            case 4:
                if (character.getInventory().getItemCount() == 0) {
                    print("아이템이 없습니다.", true);
                    return getPlayerAction(character);
                }
                print(character.getInventory().getItems());
                int itemIndex = -1;
                while (itemIndex < 0 || itemIndex >= character.getInventory().getItemCount()) {
                    try {
                        String itemChoice = input("사용할 아이템의 번호를 선택하세요: ");
                        itemIndex = Integer.parseInt(itemChoice) - 1;
                    } catch (NumberFormatException e) {
                        print("올바른 숫자를 입력해주세요!", true);
                    }
                }
                if (itemIndex >= 0 && itemIndex < character.getInventory().getItemCount()) {
                    String itemName = character.getInventory().getItemName(itemIndex);
                    return new BattleAction(ActionType.ITEM, character, target, itemName);
                } else {
                    print("잘못된 선택입니다. 다시 선택해주세요.", true);
                    return getPlayerAction(character);
                }
            default:
                print("잘못된 선택입니다. 다시 선택해주세요.", true);
                return getPlayerAction(character);
        }
    }

    private BattleAction getEnemyAction(Monster enemy) {
        Random random = new Random();
        int action = random.nextInt(100);
        
        if (action < 30) {  // 30% 확률로 스킬 사용
            Skill randomSkill = enemy.getRandomSkill();
            Combatant target = playerParty.get(random.nextInt(playerParty.size()));
            return new BattleAction(ActionType.SKILL, enemy, target, randomSkill);
        } else if (action < 60) {  // 30% 확률로 방어
            return new BattleAction(ActionType.DEFEND, enemy, enemy);
        } else {  // 40% 확률로 기본 공격
            Combatant target = playerParty.get(random.nextInt(playerParty.size()));
            return new BattleAction(ActionType.ATTACK, enemy, target);
        }
    }

    private void executeTurnActions() {
        // 속도 순서대로 정렬된 combatants 리스트 활용
        for (Combatant combatant : combatants) {
            BattleAction action = turnActions.get(combatant);
            if (action != null) {
                switch (action.getType()) {
                    case ATTACK:
                        action.getActor().attack(action.getTarget(), action.getActor().getAtk());
                        break;
                    case DEFEND:
                        action.getActor().defend();
                        break;
                    case SKILL:
                        action.getSkill().use(action.getActor(), action.getTarget());
                        break;
                    case ITEM:
                        // 여기서 캐릭터인지 확인하고 인벤토리의 useItem 호출
                        if (action.getActor() instanceof Character) {
                            Character character = (Character) action.getActor();
                            character.getInventory().useItem(action.getItemName());
                        } else {
                            // 몬스터인 경우 (처리하지 않거나 오류 메시지 출력) 사용될일 없음
                            print(action.getActor().getName() + "은(는) 아이템을 사용할 수 없습니다.", true);
                        }
                        break;
                }
            }
        }
    }

    private Combatant selectTarget(List<? extends Combatant> targets) {
        if (targets.size() == 1) return targets.get(0);

        print("\n대상을 선택하세요:", true);
        for (int i = 0; i < targets.size(); i++) {
            Combatant target = targets.get(i);
            print((i + 1) + ". " + target.getName() + " (HP: " + target.getCurrentHp() + "/" + target.getFullHp() + ")", true);
        }

        int choice = Integer.parseInt(input("선택: ")) - 1;
        return targets.get(Math.max(0, Math.min(choice, targets.size() - 1)));
    }

    private Skill selectSkill(Character character) {
        // 스킬 선택 로직 구현 예정
        return null;  // 임시 반환
    }

    private boolean isBattleEnd() {
        boolean allPlayersDead = playerParty.stream().noneMatch(Combatant::isAlive);
        boolean allEnemiesDead = enemies.stream().noneMatch(Combatant::isAlive);
        return allPlayersDead || allEnemiesDead;
    }

    private void announceResult() {
        if (playerParty.stream().anyMatch(Combatant::isAlive)) {
            print("\n전투에서 승리했습니다!", true);
        } else {
            print("\n전투에서 패배했습니다...", true);
        }
    }

    public int getTurnCount() { return turnCount; }
} 