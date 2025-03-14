package rpg.battle;

import rpg.character.base.Character;
import rpg.monster.Monster;
import rpg.skill.Skill;
import static rpg.util.IO_Manager.*;

import java.util.*;

public class BattleSystem {
    private List<Character> playerParty;
    private List<Monster> enemies;
    private Map<Combatant, BattleAction> turnActions;  // 각 전투 참가자의 행동을 저장
    private int turnCount;

    public BattleSystem() {
        this.playerParty = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.turnActions = new HashMap<>();
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

            // 2. 플레이어 파티의 행동 선택
            for (Character character : playerParty) {
                if (character.isAlive()) {
                    BattleAction action = getPlayerAction(character);
                    turnActions.put(character, action);
                }
            }

            // 3. 적의 행동 선택 (AI)
            for (Monster enemy : enemies) {
                if (enemy.isAlive()) {
                    BattleAction action = getEnemyAction(enemy);
                    turnActions.put(enemy, action);
                }
            }

            // 4. 행동 실행 (속도나 우선순위에 따라 정렬 가능)
            executeTurnActions();
        }

        announceResult();
    }

    private BattleAction getPlayerAction(Character character) {
        print("\n" + character.getName() + "의 행동을 선택하세요:", true);
        print("1. 공격", true);
        print("2. 방어", true);
        print("3. 스킬", true);
        
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
            default:
                print("잘못된 선택입니다. 기본 공격을 실행합니다.", true);
                target = selectTarget(enemies);
                return new BattleAction(ActionType.ATTACK, character, target);
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
        // 모든 행동을 실행
        for (Map.Entry<Combatant, BattleAction> entry : turnActions.entrySet()) {
            BattleAction action = entry.getValue();
            
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
            }
        }
    }

    private Combatant selectTarget(List<? extends Combatant> targets) {
        if (targets.size() == 1) return targets.get(0);

        print("\n대상을 선택하세요:", true);
        for (int i = 0; i < targets.size(); i++) {
            Combatant target = targets.get(i);
            print((i + 1) + ". " + target.getName() + " (HP: " + target.getHp() + ")", true);
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