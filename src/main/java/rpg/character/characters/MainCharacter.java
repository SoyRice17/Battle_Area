package rpg.character.characters;

import rpg.character.base.Character;
import rpg.character.jobs.Adventurer;
import rpg.item.Item;

public class MainCharacter extends Character {
    public MainCharacter(String name) {
        super(name);
        this.setJob(new Adventurer());
    }

    @Override
    public void useItem(Item<?> item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'useItem'");
    }


}
