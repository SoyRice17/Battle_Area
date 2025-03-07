package main.character.jobs;

import main.character.base.Job;
import main.item.Equipment;
import main.skill.Skill;

public class Adventurer extends Job {
    public Adventurer() {
        super("Adventurer", "Adventurer", 0, 0, 0, 0);
    }

    @Override
    public void canEquip(Equipment equment) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canUseEqument'");
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
