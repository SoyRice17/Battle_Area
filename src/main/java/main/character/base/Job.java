package main.character.base;

import main.item.Equipment;
import main.skill.Skill;

public abstract class Job {
    protected String jobName;
    protected String description;
    protected int bonusHp;
    protected int bonusMp;
    protected int bonusAtk;
    protected int bonusDef;

    protected Job(String jobName, String description, int bonusHp, int bonusMp, int bonusAtk, int bonusDef) {
        this.jobName = jobName;
        this.description = description;
        this.bonusHp = bonusHp;
        this.bonusMp = bonusMp;
        this.bonusAtk = bonusAtk;
        this.bonusDef = bonusDef;
    }
    public abstract void levelUp();
    public abstract void useSkill(Skill skill);
    public abstract void canEquip(Equipment equment);
    
    public String getJobName() { return jobName; }
    public String getDescription() { return description; }
    public int getBonusHp() { return bonusHp; }
    public int getBonusMp() { return bonusMp; }
    public int getBonusAtk() { return bonusAtk; }
    public int getBonusDef() { return bonusDef; }
}
