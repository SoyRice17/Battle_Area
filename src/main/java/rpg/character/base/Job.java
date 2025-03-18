package rpg.character.base;

import rpg.item.Equipment;
import rpg.skill.Skill;
import static rpg.util.IO_Manager.print;
public abstract class Job {
    protected String jobName;
    protected String description;
    protected int levelUpBonusHp;
    protected int levelUpBonusMp;
    protected int levelUpBonusAtk;
    protected int levelUpBonusDef;
    protected int speed;
    
    protected Job(String jobName, String description, int speed, int levelUpBonusHp, int levelUpBonusMp, int levelUpBonusAtk, int levelUpBonusDef) {
        this.jobName = jobName;
        this.description = description;
        this.speed = speed;
        this.levelUpBonusHp = levelUpBonusHp;
        this.levelUpBonusMp = levelUpBonusMp;
        this.levelUpBonusAtk = levelUpBonusAtk;
        this.levelUpBonusDef = levelUpBonusDef;
    }
    public void levelUp(Character character){
        character.setHp(character.getHp() + levelUpBonusHp);
        character.setMp(character.getMp() + levelUpBonusMp);
        character.setAtk(character.getAtk() + levelUpBonusAtk);
        character.setDef(character.getDef() + levelUpBonusDef);

        print(character.getName() + "의 레벨이 상승했습니다.", true);
        print(
        "HP: " + character.getHp() + 
        " MP: " + character.getMp() + 
        " ATK: " + character.getAtk() + 
        " DEF: " + character.getDef(), true);
    }

    //장비 사용 가능 여부
    //ex 무기제한, 방어구제한, 특별한 장신구 제한, 레벨/스탯제한 , 양손 이중등 특수조건
    public abstract boolean canEquip(Equipment equment);
    
    //스킬 사용 가능 여부   
    //ex 특수 자원 체크 , 특수 무기 착용, 속성 , 연계기, 레벨
    public abstract boolean canUseSkill(Skill skill);
    
    public String getJobName() { return jobName; }
    public String getDescription() { return description; }
    public int getSpeed() { return speed; }
    public int getLevelUpBonusHp() { return levelUpBonusHp; }
    public int getLevelUpBonusMp() { return levelUpBonusMp; }
    public int getLevelUpBonusAtk() { return levelUpBonusAtk; }
    public int getLevelUpBonusDef() { return levelUpBonusDef; }
}