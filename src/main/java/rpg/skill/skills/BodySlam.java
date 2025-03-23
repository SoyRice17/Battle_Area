package rpg.skill.skills;

import rpg.skill.Skill;
import rpg.skill.enums.SkillEffect;
import rpg.skill.enums.TargetType;
import rpg.skill.enums.SkillType;
import rpg.battle.Combatant;
import java.util.List;

public class BodySlam implements Skill {
    @Override
    public void use(Combatant user, Combatant target) {
        System.out.println(user.getName() + "이(가) 몸통박치기를 사용했습니다.");
        target.takeDamage(getPower());
        user.takeDamage(getPower() / 2);
        user.setCurrentMp(user.getCurrentMp() - getCost());
    }

    @Override
    public String getName() {
        return "몸통박치기";
    }

    @Override
    public String getDescription() {
        return "몸을 던져 상대를 타격한다";
    }

    @Override
    public int getPower() {
        return 5;
    }

    @Override
    public int getCost() {
        return 2;
    }

    @Override
    public int getCooldown() {
        return 3;
    }

    @Override
    public int getCanUseLevel() {
        return 1;
    }

    @Override
    public int getDuration() {
        return 0;
    }
    
    // 몸통박치기는 효과가 없음
    @Override
    public List<SkillEffect> getEffects() {
        return null;
    }

    @Override
    public TargetType getTargetType() {
        return TargetType.SINGLE_ENEMY;
    }

    @Override
    public boolean canUse(Combatant combatant) {
        return true;
    }
    
    @Override
    public SkillType getSkillType() {
        return SkillType.NORMAL;
    }
}