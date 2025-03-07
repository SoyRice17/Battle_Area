package rpg.skill.skills;

import java.util.Arrays;
import java.util.List;

import rpg.character.base.Character;
import rpg.skill.Skill;
import rpg.skill.enums.SkillEffect;
import rpg.skill.enums.SkillType;
import rpg.skill.enums.TargetType;

public class Power_Attack implements Skill {
    @Override
    public String getName() {
        return "Power Attack";
    }

    @Override
    public String getDescription() {
        return "강하게 상대를 공격합니다.";
    }

    @Override
    public int getPower() {
        return 10;
    }
    
    @Override
    public int getCost() {
        return 5;
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
        return 1;
    }

    @Override
    public List<SkillEffect> getEffects() {
        return Arrays.asList(SkillEffect.STUN);
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
    public boolean canUse(Character character) {
        if (character.getLevel() < getCanUseLevel()) {
            System.out.println("해당 스킬을 사용할 수 없습니다.");
            return false;
        }
        if (character.getMp() < getCost()) {
            System.out.println("마나가 부족합니다.");
            return false;
        }
        //턴수를 체크하여 쿨타임 검사 추후 추가 예정
        return true;
    }
    
    
}
