package rpg.character.base;

import java.util.List;

import rpg.item.Equipment;
import rpg.skill.Skill;
import rpg.skill.enums.SkillType;

public abstract class Job {
    protected String jobName;
    protected String description;
    protected int bonusHp;
    protected int bonusMp;
    protected int bonusAtk;
    protected int bonusDef;
    protected List<SkillType> canUseSkillType;

    protected Job(String jobName, String description, int bonusHp, int bonusMp, int bonusAtk, int bonusDef, List<SkillType> canUseSkillType) {
        this.jobName = jobName;
        this.description = description;
        this.bonusHp = bonusHp;
        this.bonusMp = bonusMp;
        this.bonusAtk = bonusAtk;
        this.bonusDef = bonusDef;
        this.canUseSkillType = canUseSkillType;
    }
    public abstract void levelUp(Character character);
    public abstract boolean canEquip(Equipment equment);

    public boolean canUseSkill(Skill skill) {
        return canUseSkillType.contains(skill.getSkillType());
    }
    
    public String getJobName() { return jobName; }
    public String getDescription() { return description; }
    public int getBonusHp() { return bonusHp; }
    public int getBonusMp() { return bonusMp; }
    public int getBonusAtk() { return bonusAtk; }
    public int getBonusDef() { return bonusDef; }
    public List<SkillType> getCanUseSkillType() { return canUseSkillType; }
}