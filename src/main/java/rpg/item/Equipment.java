package rpg.item;

import rpg.item.enums.equipmentsEnums.*;

/**
 * 장비를 나타내는 인터페이스입니다.
 * 
 * <p>
 * 주요 기능:
 * <ul>
 *  <li>장비 사용 가능 레벨 확인</li>
 *  <li>장비 스탯 보너스 확인</li>
 *  <li>장비 최대 내구도 확인</li>
 *  <li>장비 내구도 확인</li>
 *  <li>장비 타입 확인</li>
 *  <li>장비 슬롯 확인</li>
 *  <li>장비 속성 확인</li>
 *  <li>장비 희귀도 확인</li>
 * </ul>
 */
public interface Equipment extends Item {

    int getCanUseLevel();
    int getStatBonus();
    int getMaxDurability();
    int getDurability();

    EquipmentType getEquipmentType();
    EquipmentSlot getEquipmentSlot();
    EquipmentAttribute getEquipmentAttribute();
    Rarity getRarity();

}