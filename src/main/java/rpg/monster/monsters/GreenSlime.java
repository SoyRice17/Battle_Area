package rpg.monster.monsters;

import rpg.monster.Monster;

public class GreenSlime extends Monster {

    public GreenSlime() {
        super("초록 슬라임", "끈적끈적한 슬라임이다", 10, 10, 1, 1, 1, 1, 10, 10);

        learnSkill("포이즌_터치");
    }
}
