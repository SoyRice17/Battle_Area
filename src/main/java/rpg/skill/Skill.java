package rpg.skill;

import java.util.List;

import rpg.battle.Combatant;
import rpg.skill.enums.TargetType;
import rpg.skill.enums.SkillType;
import rpg.skill.enums.SkillEffect;

public interface Skill {
    String getName();
    String getDescription();

    int getPower();
    int getCost();
    int getCooldown();
    int getCanUseLevel();
    int getDuration();
    List<SkillEffect> getEffects();

    SkillType getSkillType();
    TargetType getTargetType();
    boolean canUse(Combatant combatant);
    void use(Combatant user, Combatant target);
}
