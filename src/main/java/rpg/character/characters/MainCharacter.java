package rpg.character.characters;

import rpg.character.base.Character;
import rpg.character.jobs.Adventurer;

public class MainCharacter extends Character {
    public MainCharacter(String name) {
        super(name);
        this.setJob(new Adventurer());
    }

    @Override
    public void specialAbility() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'specialAbility'");
    }
}
