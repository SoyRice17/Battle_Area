package rpg.character.job;

import rpg.item.Equipment;
import rpg.item.enums.equipmentsEnums.EquipmentAttribute;
import rpg.skill.Skill;
import rpg.skill.enums.SkillType;

/**
 * 가장 기본적인 모험가 직업 클래스입니다.
 * 
 * <p>
 * 주요 기능:
 * <ul>
 *  <li>일반 무기와 경갑 사용 가능</li>
 *  <li>보조무기와 장신구 사용 불가</li>
 *  <li>노말 타입 스킬만 사용 가능</li>
 * </ul>
 * 
 * <p>
 * 사용 예시:
 * <pre>
 *     Adventurer adventurer = new Adventurer();
 * </pre>
 */
public class Adventurer extends Job {
    public Adventurer() {
        super("Adventurer", "Adventurer", 2, 10, 10, 1, 1);
    }

    @Override
    public boolean canEquip(Equipment equment) {
        if (equment.getEquipmentAttribute() == EquipmentAttribute.NORMAL_WEAPON
        || equment.getEquipmentAttribute() == EquipmentAttribute.LIGHT_ARMOR) { // 일반 무기, 경갑 사용 가능
            return switch (equment.getEquipmentType()) {
                case SUB_WEAPON, ACCESSORY -> false; // 보조무기, 장신구 사용 불가
                case WEAPON, ARMOR -> true; // 무기, 방어구 사용 가능
                default -> false;
            };
        }
        return false;
    }

    @Override
    public boolean canUseSkill(Skill skill) {
        return skill.getSkillType() == SkillType.NORMAL;
    }
} 