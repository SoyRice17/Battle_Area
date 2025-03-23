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

/**
 * 캐릭터를 나타내는 추상 클래스입니다.
 * 캐릭터는 전투 참가자이며, 장비, 스킬, 인벤토리 등을 관리합니다.
 * 
 * <p>
 * 주요 기능:
 * <ul>
 *  <li>장비 관리</li>
 *  <li>스킬 관리</li>
 *  <li>인벤토리 관리</li>
 *  <li>경험치 및 레벨업 처리</li>
 * </ul>
 * 
 * <p>
 * 사용 예시:
 * <pre>
 *     Character character = new MainCharacter("캐릭터 이름");
 * </pre>
 */
public abstract class Character extends Combatant {
    protected Job job;
    protected int exp;
    protected int requiredExp;
    protected Inventory inventory;

    /** 최대 레벨 제한 */
    protected final int MAX_LEVEL = 100;

    /** 장비 슬롯별 장착된 장비 */
    protected Map<EquipmentSlot, Equipment> equipments;

    /**
     * 캐릭터의 기본 생성자입니다.
     * 
     * @param name 캐릭터의 이름
     */
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

    /**
     * 각 캐릭터간 차이점을 위해 특수 능력설정하는 추상 메소드입니다.
     */
    public abstract void specialAbility();

    public void levelUp() {
        this.level++;
        if (this.job != null) {
            this.job.levelUp(this);
        }
    }

    /**
     * 장비를 장착하는 메소드입니다.
     * 
     * @param targetEquipment 장착할 장비
     */
    public void equip(Equipment targetEquipment) {
        String errorMessage = canEquip(targetEquipment);
        if (errorMessage != null) {
            print(errorMessage, true);
            return;
        }
        
        this.equipments.put(targetEquipment.getEquipmentSlot(), targetEquipment);
        print(this.name + "은(는) " + targetEquipment.getName() + "을(를) 장착했습니다.", true);
    }

    /**
     * 장비를 장착할 수 있는지 체크하는 메소드입니다.
     * 레벨이 충족하고, 직업이 허용하고, 특수 조건이 충족되면 장착할 수 있습니다.
     * 
     * @param equipment 체크할 장비
     * @return 장비를 장착할 수 있는지 여부
     */
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

    /**
     * 직업을 설정하는 메소드입니다.
     * 직업이 바뀌면 지금까지의 스탯을 초기화하고 새로운 스탯으로 설정합니다.
     * 
     * @param job 설정할 직업
     */
    public void setJob(Job job) {
        this.job = job;
        this.fullHp = this.level * job.getLevelUpBonusHp();
        this.fullMp = this.level * job.getLevelUpBonusMp();
        this.atk = this.level * job.getLevelUpBonusAtk();
        this.def = this.level * job.getLevelUpBonusDef();
        this.speed = job.getSpeed();
    }
} 