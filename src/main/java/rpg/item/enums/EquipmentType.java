package rpg.item.enums;

// 모든 장비는 3개의 타입을 가짐
// 1. 가장 상위 개념
// 2. 상세 개념
// 3. 물리, 마법
public enum EquipmentType {
    WEAPON, ARMOR, ACCESSORY, // 가장 상위 개념
    HEAD, CHEST, LEGS, FEET, HANDS, SHIELD, SUB_WEAPON, NECKLACE, RING, // 상세 개념
    NORMAL, PHYSICAL, MAGICAL, // 물리, 마법
}
