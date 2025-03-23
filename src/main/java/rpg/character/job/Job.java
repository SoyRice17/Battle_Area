package rpg.character.job;

import rpg.character.Character;
import rpg.item.Equipment;
import rpg.skill.Skill;
import static rpg.util.IO_Manager.print;

/**
 * 직업을 나타내는 추상 클래스입니다.
 * 캐릭터의 직업을 나타내며, 직업에 따라 스킬, 장비, 스탯 등이 달라집니다.
 * 
 * <p>
 * 주요 기능:
 * <ul>
 *  <li>직업 이름</li>
 *  <li>직업 설명</li>
 *  <li>레벨 업 시 체력, 마나, 공격력, 방어력 증가</li>
 *  <li>장비 사용 가능 여부</li>
 *  <li>스킬 사용 가능 여부</li>
 *  <li>턴 순서에 영향이 가는 속도 결정</li>
 * </ul>
 * 
 * <p>
 * 사용 예시:
 * <pre>
 *     Job job = new Adventurer();
 * </pre>
 */
public abstract class Job {
    protected String jobName;
    protected String description;
    protected int levelUpBonusHp;
    protected int levelUpBonusMp;
    protected int levelUpBonusAtk;
    protected int levelUpBonusDef;
    protected int speed;
    
    /**
     * 직업의 기본 생성자입니다.
     * 
     * @param jobName 직업 이름
     * @param description 직업 설명
     * @param speed 턴 순서에 영향이 가는 속도
     * @param levelUpBonusHp 레벨 업 시 체력 증가
     * @param levelUpBonusMp 레벨 업 시 마나 증가
     * @param levelUpBonusAtk 레벨 업 시 공격력 증가
     * @param levelUpBonusDef 레벨 업 시 방어력 증가
     */
    protected Job(String jobName, String description, int speed, int levelUpBonusHp, int levelUpBonusMp, int levelUpBonusAtk, int levelUpBonusDef) {
        this.jobName = jobName;
        this.description = description;
        this.speed = speed;
        this.levelUpBonusHp = levelUpBonusHp;
        this.levelUpBonusMp = levelUpBonusMp;
        this.levelUpBonusAtk = levelUpBonusAtk;
        this.levelUpBonusDef = levelUpBonusDef;
    }

    /**
     * 레벨 업 시 체력, 마나, 공격력, 방어력 증가
     * 
     * @param character 캐릭터
     */
    public void levelUp(Character character){
        character.setFullHp(character.getFullHp() + levelUpBonusHp);
        character.setFullMp(character.getFullMp() + levelUpBonusMp);
        character.setAtk(character.getAtk() + levelUpBonusAtk);
        character.setDef(character.getDef() + levelUpBonusDef);

        print(character.getName() + "의 레벨이 상승했습니다.", true);
        print(
        "HP: " + character.getFullHp() + 
        " MP: " + character.getFullMp() + 
        " ATK: " + character.getAtk() + 
        " DEF: " + character.getDef(), true);
    }

    /**
     * 장비 사용 가능 여부
     * ex 무기제한, 방어구제한, 특별한 장신구 제한, 레벨/스탯제한 , 양손 이중등 특수조건
     * 
     * @param equment 장비
     * @return 장비 사용 가능 여부
     */
    public abstract boolean canEquip(Equipment equment);
    
    /**
     * 스킬 사용 가능 여부
     * ex 특수 자원 체크 , 특수 무기 착용, 속성 , 연계기, 레벨
     * 
     * @param skill 스킬
     * @return 스킬 사용 가능 여부
     */
    public abstract boolean canUseSkill(Skill skill);
    
    public String getJobName() { return jobName; }
    public String getDescription() { return description; }
    public int getSpeed() { return speed; }
    public int getLevelUpBonusHp() { return levelUpBonusHp; }
    public int getLevelUpBonusMp() { return levelUpBonusMp; }
    public int getLevelUpBonusAtk() { return levelUpBonusAtk; }
    public int getLevelUpBonusDef() { return levelUpBonusDef; }
} 