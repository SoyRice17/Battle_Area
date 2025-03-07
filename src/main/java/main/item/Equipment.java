package main.item;

public interface Equipment {
    String getName();
    String getDescription();

    int getCanUseLevel();
    int getStatBonus();
    int getPrice();

    EquipmentType getEquipmentType();
    EquipmentSlot getEquipmentSlot();
    Rarity getRarity();
    int getDurability();
}