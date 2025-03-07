package rpg.character.jobs;

import java.util.Arrays;

import rpg.character.base.Job;
import rpg.character.base.Character;
import rpg.item.Equipment;
import rpg.item.enums.EquipmentAttribute;
import rpg.skill.Skill;
import rpg.skill.enums.SkillType;
import static rpg.util.IO_Manager.print;

public class Adventurer extends Job {
    public Adventurer() {
        super("Adventurer", "Adventurer", 0, 0, 0, 0, Arrays.asList(SkillType.NORMAL));
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
    public void levelUp(Character character) {
        int bonusHp = 10;
        int bonusMp = 10;
        int bonusAtk = 1;
        int bonusDef = 1;

        character.setHp(character.getHp() + bonusHp);
        character.setMp(character.getMp() + bonusMp);
        character.setAtk(character.getAtk() + bonusAtk);
        character.setDef(character.getDef() + bonusDef);

        print(character.getName() + "의 레벨이 상승했습니다.", true);
        print("HP: " + character.getHp() + " MP: " + character.getMp() + " ATK: " + character.getAtk() + " DEF: " + character.getDef(), true);
    }
}
