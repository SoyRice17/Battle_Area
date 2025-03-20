package rpg.item;

import rpg.item.enums.equipmentsEnums.*;

public interface Equipment extends Item<Equipment> {

    int getCanUseLevel();
    int getStatBonus();
    int getMaxDurability();
    int getDurability();

    EquipmentType getEquipmentType();
    EquipmentSlot getEquipmentSlot();
    EquipmentAttribute getEquipmentAttribute();
    Rarity getRarity();

}