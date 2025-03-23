package rpg.monster.monsters;

import rpg.monster.Monster;

public class OrangeMushroom extends Monster {

    public OrangeMushroom() {
        super("주황 버섯", "익숙하고 귀여운 버섯이다.", 10, 10, 1, 1, 1, 1, 10, 10);

        learnSkill("몸통박치기");
    }
}
