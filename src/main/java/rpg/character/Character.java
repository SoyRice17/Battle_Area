package rpg.character;

import java.util.HashMap;
import java.util.Map;

import rpg.battle.Combatant;
import rpg.character.job.Job;
import rpg.inventory.Inventory;
import rpg.item.Equipment;
import rpg.item.enums.equipmentsEnums.EquipmentSlot;
import rpg.item.equipments.weapons.Weapon;
import static rpg.util.IO_Manager.print;

public abstract class Character extends Combatant {
    protected Job job;
    protected int exp;
    protected int requiredExp;
    protected Inventory inventory;

    protected final int MAX_LEVEL = 100;

    protected Map<EquipmentSlot, Equipment> equipments;

    protected Character(String name) {
        super();
        this.name = name;
        this.level = 1;
        this.exp = 0;
        this.requiredExp = 100;
        this.currentHp = 100;
        this.fullHp = 100;
        this.currentMp = 50;
        this.fullMp = 50;
        this.atk = 10;
        this.def = 5;

        this.equipments = new HashMap<>();
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            this.equipments.put(slot, null);
        }

        this.inventory = new Inventory(30, this);
    }
    
    public abstract void specialAbility();

    public void levelUp() {
        this.level++;
        if (this.job != null) {
            this.job.levelUp(this);
        }
    }

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

    public Job getJob() { return job; }
    public Inventory getInventory() {
        return this.inventory;
    }

    public void setJob(Job job) {
        this.job = job;
        this.fullHp = this.level * job.getLevelUpBonusHp();
        this.fullMp = this.level * job.getLevelUpBonusMp();
        this.atk = this.level * job.getLevelUpBonusAtk();
        this.def = this.level * job.getLevelUpBonusDef();
        this.speed = job.getSpeed();
    }
} 