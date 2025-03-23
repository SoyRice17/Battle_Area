package rpg.battle;

import rpg.skill.Skill;
import rpg.character.status.StatusEffect;
import static rpg.util.IO_Manager.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import rpg.skill.AkashicRecords;

/**
 * 전투 참가자를 나타내는 추상 클래스입니다.
 * 캐릭터와 몬스터가 상속받아 사용합니다.
 * 
 * <p>
 * 주요 기능:
 * <ul>
 *  <li>상태이상 관리</li>
 *  <li>스킬 관리</li>
 *  <li>방어 상태 관리</li>
 *  <li>체력, 마나 관리</li>
 * </ul>
 * 
 * <p>
 * 사용 예시:
 * <pre>
 *     Combatant combatant = new Character("캐릭터 이름");
 * </pre>
 * 
 */
public abstract class Combatant {
    protected String name;
    protected boolean isAlive;
    protected int currentHp;
    protected int currentMp;
    protected int fullHp;
    protected int fullMp;
    protected int atk;
    protected int def;
    protected int speed;
    protected int level;
    protected boolean isDefending;  // 방어 상태 체크
    protected List<StatusEffect> statusEffects;  // 상태이상 목록
    protected List<Skill> learnedSkills;  // 스킬 목록
    
    protected Combatant() {
        this.isAlive = true;
        this.statusEffects = new ArrayList<>();
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void attack(Combatant target, int damage) {
        int finalDamage = damage + this.atk;  // 기본 공격력 추가
        target.takeDamage(finalDamage);
    }

    public void defend() {
        this.isDefending = true;  // 방어 태세
        print(this.name + "이(가) 방어 태세를 취했습니다.", true);
    }

    /**
     * 스킬을 배우는 메소드입니다.
     * 스킬이 존재하고 레벨이 충분하면 스킬을 배울 수 있습니다.
     * 
     * @param skill 배울 스킬
     */
    public void learnSkill(Skill skill) {
        if(AkashicRecords.getInstance().isSkillExist(skill)) {
            if(skill.getCanUseLevel() <= this.level) {
                if(!hasSkill(skill)) {
                    learnedSkills.add(skill);
                    print(this.name + "이(가) " + skill.getName() + "을(를) 배웠습니다.", true);
                } else {
                    print(this.name + "이(가) 이미 " + skill.getName() + "을(를) 배웠습니다.", true);
                }
            } else {
                print(this.name + "이(가) 레벨이 부족해 " + skill.getName() + "을(를) 배울 수 없습니다.", true);
            }
        } else {
            print(this.name + "이(가) 이미 " + skill.getName() + "을(를) 배웠습니다.", true);
        }
    }

    /**
     * 스킬을 배우는 메소드입니다.
     * 스킬 이름을 받아 스킬을 배울 수 있습니다.
     * 
     * @param skillName 배울 스킬 이름
     */
    public void learnSkill(String skillName) {
        learnSkill(AkashicRecords.getInstance().getSkill(skillName));
    }

    public boolean hasSkill(Skill skill) {
        return learnedSkills.contains(skill);
    }
    
    /**
     * 턴 시작시 호출되는 메소드입니다.
     * 매턴 모든 상태이상 효과 적용
     */
    public void onTurnStart() {
        Iterator<StatusEffect> iterator = statusEffects.iterator();
        while (iterator.hasNext()) {
            StatusEffect effect = iterator.next();
            effect.applyEffect(this);
            if (!effect.updateDuration()) {
                effect.removeEffect(this);
                iterator.remove();
            }
        }
    }

    /**
     * 상태이상 추가 메소드입니다.
     * 같은 타입의 상태이상이 있다면 갱신
     * 
     * @param effect 추가할 상태이상
     */
    public void addStatusEffect(StatusEffect effect) {
        // 같은 타입의 상태이상이 있다면 갱신
        statusEffects.removeIf(e -> e.getStatusType() == effect.getStatusType());
        statusEffects.add(effect);
        print(this.name + "에게 " + effect.getStatusType().getName() + " 효과가 적용되었습니다!", true);
    }


    public void takeDamage(int damage) {
        int finalDamage;
        
        if (isDefending) {
            finalDamage = (int)(damage * 0.5);  // 방어 시 데미지 50% 감소
            finalDamage = Math.max(0, finalDamage - this.def);  // 방어력 적용
            this.isDefending = false;  // 방어는 1턴만 지속
            print(this.name + "의 방어로 데미지가 감소했습니다!", true);
        } else {
            finalDamage = Math.max(0, damage - this.def);  // 일반 방어력만 적용
        }

        this.currentHp -= finalDamage;
        print(this.name + "이(가) " + finalDamage + "의 데미지를 받았습니다.", true);
        
        if (this.currentHp <= 0) {
            this.isAlive = false;
            print(this.name + "이(가) 쓰러졌습니다!", true);
        }
    }

    public String getName() { return name; }
    public int getCurrentHp() { return currentHp; }
    public int getCurrentMp() { return currentMp; }
    public int getFullHp() { return fullHp; }
    public int getFullMp() { return fullMp; }
    public int getAtk() { return atk; }
    public int getDef() { return def; }
    public int getLevel() { return level; }
    public int getSpeed() { return speed; }
    public List<Skill> getLearnedSkills() { return learnedSkills; }
    public void setName(String name) { this.name = name; }
    public void setCurrentHp(int currentHp) { this.currentHp = Math.max(0, currentHp); }
    public void setCurrentMp(int currentMp) { this.currentMp = Math.max(0, currentMp); }
    public void setFullHp(int fullHp) { this.fullHp = fullHp; }
    public void setFullMp(int fullMp) { this.fullMp = fullMp; }
    public void setAtk(int atk) { this.atk = atk; }
    public void setDef(int def) { this.def = def; }
    public void setLevel(int level) { this.level = level; }
    public void setSpeed(int speed) { this.speed = speed; }
}
