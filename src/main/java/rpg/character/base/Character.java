package rpg.character.base;

import java.util.HashMap;
import java.util.Map;

import rpg.item.Equipment;
import rpg.item.equipments.weapons.Weapon;
import rpg.item.enums.EquipmentSlot;
import rpg.monster.Monster;
import rpg.item.Item;  
import static rpg.util.IO_Manager.print;

public abstract class Character {
    protected String name;
    protected Job job;
    protected int level;
    protected int exp;
    protected int hp;
    protected int mp;
    protected int atk;
    protected int def;

    protected final int MAX_LEVEL = 100;

    protected Map<EquipmentSlot, Equipment> equipments;

    protected Character(String name) {
        this.name = name;
        this.level = 1;
        this.exp = 100;
        this.hp = 100;
        this.mp = 50;
        this.atk = 10;
        this.def = 5;

        this.equipments = new HashMap<>();
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            this.equipments.put(slot, null);
        }
    }

    public abstract void attack(Monster target);
    public abstract void defend(int damage);
    public abstract void useItem(Item<?> item);

    public void equip(Equipment targetEquipment) {
        String errorMessage = canEquip(targetEquipment);
        if (errorMessage != null) {
            print(errorMessage, true);
            return;
        }
        
        this.equipments.put(targetEquipment.getEquipmentSlot(), targetEquipment);
        print(this.name + "은(는) " + targetEquipment.getName() + "을(를) 장착했습니다.", true);
    }

    private String canEquip(Equipment equipment) {
        // 1. 레벨 체크
        if (this.level < equipment.getCanUseLevel()) {
            return this.name + "은(는) " + equipment.getName() + "을(를) 사용할 수 없습니다.";
        }
        
        // 2. 직업 제한 체크
        if (!this.job.canEquip(equipment)) {
            return this.name + "은(는) " + equipment.getName() + "을(를) 사용할 수 없습니다.";
        }

        // 3. 특수 조건(보조무기) 체크
        EquipmentSlot slot = equipment.getEquipmentSlot();
        if (slot == EquipmentSlot.SUB_WEAPON) {
            if (this.equipments.get(EquipmentSlot.WEAPON) == null) {
                return this.name + "은(는) 메인무기를 장착하지 않았습니다.";
            }
            Weapon mainWeapon = (Weapon) this.equipments.get(EquipmentSlot.WEAPON);
            if (mainWeapon.isTwoHand()) {
                return this.name + "은(는) 양손무기를 착용중입니다";
            }
        }

        // 4. 슬롯 중복 체크
        if (this.equipments.get(slot) != null) {
            return this.name + "은(는) 이미 장착중인 장비가 있습니다.";
        }

        return null;  // 검증 통과
    }

    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMp() { return mp; }
    public int getAtk() { return atk; }
    public int getDef() { return def; }
    public Job getJob() { return job; }

    public void setName(String name) { this.name = name; }
    public void setHp(int hp) { this.hp = Math.max(0, hp); }
    public void setMp(int mp) { this.mp = Math.max(0, mp); }
    public void setAtk(int atk) { this.atk = atk; }
    public void setDef(int def) { this.def = def; }
    
    public void setJob(Job job) {
        this.job = job;
        this.hp += job.getBonusHp();
        this.mp += job.getBonusMp();
        this.atk += job.getBonusAtk();
        this.def += job.getBonusDef();
    }
}
