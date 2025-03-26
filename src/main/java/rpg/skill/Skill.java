package rpg.skill;

import java.util.List;

import rpg.battle.Combatant;
import rpg.skill.enums.TargetType;
import rpg.skill.enums.SkillType;
import rpg.skill.enums.SkillEffect;

/**
 * 스킬을 나타내는 인터페이스입니다.
 * 
 * <p>
 * 주요 기능:
 * <ul>
 *  <li>스킬 이름 확인</li>
 *  <li>스킬 설명 확인</li>
 *  <li>스킬 위력 확인</li>
 *  <li>스킬 소모 마나 확인</li>
 *  <li>스킬 쿨다운 확인</li>
 *  <li>스킬 사용 가능 레벨 확인</li>
 *  <li>스킬 지속 시간 확인</li>
 *  <li>스킬 타입 확인</li>
 *  <li>스킬 타겟 확인</li>
 *  <li>스킬 사용 가능 여부 확인</li>
 *  <li>스킬 사용</li>
 * </ul>
 */
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
    /*
     *  void use(Combatant user, List<Combatant> targets); 
     *  다중 타겟 게임 시스템 모두 구현후 구현
     */
    void use(Combatant user, Combatant target); 
}
