package main.skill;

public interface Skill {
    String getName();
    String getDescription();

    int getPower();
    int getCost();
    int getCooldown();
    int getRange();
    int getCanUseLevel();

    SkillType getSkillType();
    boolean canUse(Character character);
}
