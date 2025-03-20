package rpg.item.enums.equipmentsEnums;

// 모든 장비는 3개 이상의의 타입을 가짐
// 1. 가장 상위 개념
// 2. 상세 개념
// 3. 물리, 마법
// 4. 세부 옵션
public enum EquipmentType {
    WEAPON, ARMOR, ACCESSORY, // 가장 상위 개념
    HEAD, CHEST, LEGS, FEET, HANDS, SHIELD, SUB_WEAPON, NECKLACE, RING, // 상세 개념
}
