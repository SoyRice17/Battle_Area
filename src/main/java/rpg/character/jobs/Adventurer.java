package rpg.character.jobs;

import rpg.character.base.Job;
import rpg.item.Equipment;
import rpg.item.enums.EquipmentType;
import rpg.skill.Skill;

public class Adventurer extends Job {
    public Adventurer() {
        super("Adventurer", "Adventurer", 0, 0, 0, 0);
    }

    @Override
    public boolean canEquip(Equipment equment) {
        if (equment.getEquipmentType() == EquipmentType.WEAPON) {
            return true;
        }
        return false;
    }

    @Override
    public void levelUp() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'levelUp'");
    }
    @Override
    public void useSkill(Skill skill) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'useSkill'");
    }
    
}
