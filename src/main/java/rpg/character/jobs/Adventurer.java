package rpg.character.jobs;

import rpg.character.base.Job;
import rpg.item.Equipment;
import rpg.item.enums.EquipmentAttribute;
import rpg.skill.Skill;
import rpg.skill.enums.SkillType;

public class Adventurer extends Job {
    public Adventurer() {
        super("Adventurer", "Adventurer", 10, 10, 1, 1);
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
