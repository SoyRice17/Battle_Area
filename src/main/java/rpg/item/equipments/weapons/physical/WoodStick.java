package rpg.item.equipments.weapons.physical;

import rpg.item.Equipment;
import rpg.item.equipments.weapons.Weapon;
import rpg.item.enums.equipmentsEnums.*;

public class WoodStick implements Weapon {
    private int durability = 100;
    private final int maxDurability = 100;
    private final int statBonus = 1;
    private final int price = 100;
    private final int canUseLevel = 1;
    private final EquipmentSlot equipmentSlot = EquipmentSlot.WEAPON;
    private final EquipmentType equipmentType = EquipmentType.WEAPON;
    private final EquipmentAttribute equipmentAttribute = EquipmentAttribute.NORMAL_WEAPON;
    private final Rarity rarity = Rarity.COMMON;

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return "나무 막대기";
    }

    @Override
    public String getDescription() {
        return "바닥에서 주운 나무 막대기다.";
    }
    @Override
    public int getCanUseLevel() {
        return canUseLevel;
    }
    @Override
    public int getStatBonus() {
        return statBonus;
    }
    @Override
    public int getMaxDurability() {
        return maxDurability;
    }
    @Override
    public int getDurability() {
        return durability;
    }
    @Override
    public EquipmentSlot getEquipmentSlot() {
        return equipmentSlot;
    }
    @Override
    public EquipmentType getEquipmentType() {
        return equipmentType;
    }
    @Override
    public EquipmentAttribute getEquipmentAttribute() {
        return equipmentAttribute;
    }
    @Override
    public Rarity getRarity() {
        return rarity;
    }
    @Override
    public boolean isTwoHand() {
        return false;
    }
}
