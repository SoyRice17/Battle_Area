package rpg.monster;

import java.util.HashMap;
import java.util.Map;

import rpg.battle.Combatant;
import rpg.item.Equipment;
import rpg.item.enums.EquipmentSlot;
import rpg.skill.Skill;
public abstract class Monster extends Combatant {
    protected String description;
    protected int giveExp;
    protected int giveGold;

    protected final int MAX_LEVEL = 100;

    protected Map<EquipmentSlot, Equipment> equipments;
    
    public Monster(String name, String description, int hp, int mp, int atk, int def, int speed, int level, int giveExp, int giveGold, Equipment... initialEquipments) {
        super();
        this.name = name;
        this.description = description;
        this.hp = hp;
        this.mp = mp;
        this.atk = atk;
        this.def = def;
        this.speed = speed;
        this.level = level;
        this.equipments = new HashMap<>();
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            this.equipments.put(slot, null);
        }
        this.giveExp = giveExp;
        this.giveGold = giveGold;
        
        // 초기 장비 설정
        for (Equipment equipment : initialEquipments) {
            setEquipment(equipment);
        }
    }

    public abstract void useSkill(Skill skill);

    public void setEquipment(Equipment equipment) {
        if (equipment != null) {
            this.equipments.put(equipment.getEquipmentSlot(), equipment);
        }
    }

    public abstract Skill getRandomSkill();

    public String getDescription() { return description; }
    public int getGiveExp() { return giveExp; }
    public int getGiveGold() { return giveGold; }
}
