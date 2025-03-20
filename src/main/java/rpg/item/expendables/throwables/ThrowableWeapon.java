package rpg.item.expendables.throwables;

import rpg.battle.Combatant;
import rpg.item.Expendable;
import rpg.item.enums.expendablesEnums.ThrowableType;
/**
 * 투척 무기 인터페이스
 * 던져서 사용하는 소모성 무기류 아이템
 */
public interface ThrowableWeapon extends Expendable {
    
    /**
     * 투척 무기의 종류를 반환
     * @return 투척 무기 타입
     */
    ThrowableType getThrowableType();
    
    /**
     * 투척 무기가 주는 데미지 값
     * @return 데미지 값
     */
    int getDamage();
    
    /**
     * 투척 무기가 범위 공격인지 여부
     * @return 범위 공격 여부
     */
    boolean isAreaEffect();
    
    /**
     * 투척 무기가 적중할 확률 (0.0 ~ 1.0)
     * @return 적중 확률
     */
    default double getAccuracy() {
        return 0.9; // 기본 90% 명중률
    }
    
    /**
     * 투척 무기 사용 시 출력할 메시지
     * @param user 사용자
     * @return 특수 메시지
     */
    default String getThrowMessage(Combatant user) {
        return user.getName() + "이(가) " + getName() + "을(를) 던졌습니다!";
    }
} 