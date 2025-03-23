package rpg.character.player;

import rpg.character.Character;
import rpg.character.job.Adventurer;

public class MainCharacter extends Character {
    public MainCharacter(String name) {
        super(name);
        this.setJob(new Adventurer());
        this.learnSkill("강공");
    }

    @Override
    public void specialAbility() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'specialAbility'");
    }
} 