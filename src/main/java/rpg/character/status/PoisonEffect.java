package rpg.character.status;

import rpg.battle.Combatant;
import rpg.character.status.enums.StatusType;
import static rpg.util.IO_Manager.*;
public class PoisonEffect extends StatusEffect {

    public PoisonEffect() {
        super(StatusType.POISON, 2, 3);
    }

    @Override
    public void applyEffect(Combatant combatant) {
        combatant.takeDamage(value);
        print(combatant.getName() + "에게 " + value + "의 독 피해를 입혔습니다.", true);
    }

    @Override
    public void removeEffect(Combatant combatant) {
        print(combatant.getName() + "의 독 효과가 사라졌습니다.", true);
    }
    
}
