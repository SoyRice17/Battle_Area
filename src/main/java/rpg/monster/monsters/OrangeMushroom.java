package rpg.monster.monsters;

import rpg.monster.Monster;
import rpg.skill.Skill;
import rpg.skill.skills.BodySlam;
import static rpg.util.IO_Manager.*;
import java.util.Random;
import java.util.List;
import java.util.Arrays;

public class OrangeMushroom extends Monster {
    private final List<Skill> skillPool;

    public OrangeMushroom() {
        super("주황 버섯", "익숙하고 귀여운 버섯이다.", 10, 10, 1, 1, 1, 1, 10, 10);
        this.skillPool = Arrays.asList(
            new BodySlam()
            // 여기에 더 많은 스킬 추가 가능
        );
    }

    @Override
    public Skill getRandomSkill() {
        return skillPool.get(new Random().nextInt(skillPool.size()));
    }

    @Override
    public void useSkill(Skill skill) {
        Random random = new Random();
        int randomValue = random.nextInt(100);
        if (randomValue < 50) {
            skill.use(this, this);
        } else {
            print(this.getName() + "이(가) 스킬사용을 실패했습니다.", true);
        }
    }
}
