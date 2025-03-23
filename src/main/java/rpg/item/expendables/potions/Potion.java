package rpg.item.expendables.potions;

import rpg.battle.Combatant;
import rpg.character.status.enums.StatusType;
import rpg.item.Expendable;
import rpg.item.enums.expendablesEnums.PotionType;
/**
 * 포션 아이템 인터페이스
 * 회복 효과나 버프 효과를 주는 물약류 아이템
 */
public interface Potion extends Expendable {
    /**
     * 포션의 효과 종류를 반환
     * @return 포션 타입
     */
    PotionType getPotionType();
    
    /**
     * 아이템의 효과 강도를 반환
     * @return 아이템 효과의 강도 (회복량, 데미지량 등)
     */
    int getEffectPower();
    
    /**
     * 아이템의 지속 시간을 반환 (즉시 효과는 0)
     * @return 효과 지속 시간 (턴 단위)
     */
    int getDuration();
    
    /**
     * 포션의 효과가 비율(%)로 적용되는지 여부
     * true인 경우 최대 HP/MP의 백분율로 회복, false인 경우 고정값으로 회복
     * @return 비율 적용 여부
     */
    boolean isPercentageBased();
    
    /**
     * 아이템이 적용하는 상태 효과 타입 (없으면 null)
     * @return 상태 효과 타입
     */
    default StatusType getStatusType() {
        return null;
    }
    
    /**
     * 포션 사용 시 즉시 효과와 함께 콘솔에 출력할 특수 메시지
     * @param user 사용자
     * @return 특수 메시지
     */
    default String getSpecialMessage(Combatant user) {
        return user.getName() + "이(가) " + getName() + "을(를) 마셨습니다!";
    }
    
    /**
     * 포션의 중첩 사용 가능 여부
     * true인 경우 같은 포션을 여러번 사용해도 효과가 누적됨
     * @return 중첩 사용 가능 여부
     */
    default boolean isStackable() {
        return false;
    }
}
