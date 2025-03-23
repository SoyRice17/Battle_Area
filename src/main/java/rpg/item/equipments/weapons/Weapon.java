package rpg.item.equipments.weapons;

import rpg.item.Equipment;

/**
 * 무기를 나타내는 인터페이스입니다.
 * 
 * <p>
 * 주요 기능:
 * <ul>
 *  <li>양손 무기 여부 확인</li>
 * </ul>
 */
public interface Weapon extends Equipment {
    boolean isTwoHand();
}
