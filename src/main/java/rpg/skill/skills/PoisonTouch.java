package rpg.skill.skills;

import rpg.battle.Combatant;
import rpg.skill.Skill;
import rpg.skill.enums.SkillEffect;
import rpg.skill.enums.SkillType;
import rpg.skill.enums.TargetType;
import java.util.Arrays;
import java.util.List;

public class PoisonTouch implements Skill {

    @Override
    public void use(Combatant user, Combatant target) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getName() {
        return "포이즌_터치";
    }

    @Override
    public String getDescription() {
        return "맹독을 사용해 적을 공격합니다.";
    }

    @Override
    public int getPower() {
        return 3;
    }

    @Override
    public int getCost() {
        return 3;
    }

    @Override
    public int getCooldown() {
        return 1;
    }

    @Override
    public int getCanUseLevel() {
        return 1;
    }

    @Override
    public int getDuration() {
        return 1;
    }

    @Override
    public List<SkillEffect> getEffects() {
        return Arrays.asList(SkillEffect.POISON);
    }

    @Override
    public SkillType getSkillType() {
        return SkillType.NORMAL;
    }

    @Override
    public TargetType getTargetType() {
        return TargetType.SINGLE_ENEMY;
    }

    @Override
    public boolean canUse(Combatant combatant) {
        return combatant.getLevel() >= getCanUseLevel();
    }
    
}
