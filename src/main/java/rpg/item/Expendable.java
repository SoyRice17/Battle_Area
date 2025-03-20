package rpg.item;

import rpg.battle.Combatant;
import rpg.skill.enums.TargetType;

/**
 * 소모품 아이템 인터페이스
 * 한 번 사용하면 소모되는 모든 아이템의 기본 인터페이스
 */
public interface Expendable extends Item<Expendable> {
    /**
     * 아이템을 사용했을 때의 효과를 적용
     * @param user 아이템을 사용하는 캐릭터
     * @param target 아이템 효과의 대상
     */
    void use(Combatant user, Combatant target);
    
    /**
     * 아이템 사용 가능한 최소 레벨
     * @return 사용 가능한 최소 레벨
     */
    int getCanUseLevel();
    
    /**
     * 아이템 효과의 대상 타입
     * @return 대상 타입 (자신, 아군 한명, 적 한명, 아군 전체, 적 전체 등)
     */
    TargetType getTargetType();
    
    /**
     * 아이템 사용이 가능한지 검사
     * @param user 사용자
     * @return 사용 가능 여부
     */
    default boolean canUse(Combatant user) {
        return user.getLevel() >= getCanUseLevel();
    }
}
