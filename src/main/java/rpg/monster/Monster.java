package rpg.monster;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import rpg.battle.Combatant;
import rpg.item.Equipment;
import rpg.item.enums.equipmentsEnums.EquipmentSlot;
import rpg.skill.Skill;
 
/**
 * 몬스터를 나타내는 추상 클래스입니다.
 * 
 * <p>
 * 주요 기능:
 * <ul>
 *  <li>몬스터 이름 확인</li>
 *  <li>몬스터 설명 확인</li>
 *  <li>몬스터 경험치 확인</li>
 *  <li>몬스터 골드 확인</li>
 *  <li>몬스터 장비 확인</li>
 * </ul>
 *
 * <p>
 * 사용 예시:
 * <pre>
 *     Monster monster = new Monster("몬스터 이름", "몬스터 설명", 100, 100, 10, 10, 1, 1, 10, 10);
 * </pre>
 */

public abstract class Monster extends Combatant {
    protected String description;
    protected int giveExp;
    protected int giveGold;

    protected final int MAX_LEVEL = 100;

    protected Map<EquipmentSlot, Equipment> equipments;
    
    /**
     * 몬스터의 기본 생성자입니다.
     * 
     * @param name 몬스터 이름
     * @param description 몬스터 설명
     * @param hp 몬스터 체력
     * @param mp 몬스터 마나
     * @param atk 몬스터 공격력
     * @param def 몬스터 방어력
     * @param speed 몬스터 속도
     * @param level 몬스터 레벨
     * @param giveExp 몬스터 지급 경험치
     * @param giveGold 몬스터 지급 골드
     * @param initialEquipments 몬스터 초기 장비
     */
    public Monster(String name, String description, int hp, int mp, int atk, int def, int speed, int level, int giveExp, int giveGold, Equipment... initialEquipments) {
        super();
        this.name = name;
        this.description = description;
        this.currentHp = hp;
        this.fullHp = hp;
        this.currentMp = mp;
        this.fullMp = mp;
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

    public Skill getRandomSkill() {
        return learnedSkills.get(new Random().nextInt(learnedSkills.size()));
    }

    public void setEquipment(Equipment equipment) {
        if (equipment != null) {
            this.equipments.put(equipment.getEquipmentSlot(), equipment);
        }
    }

    public String getDescription() { return description; }
    public int getGiveExp() { return giveExp; }
    public int getGiveGold() { return giveGold; }
}
