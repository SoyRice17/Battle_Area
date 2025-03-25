package rpg.character.status;

import rpg.battle.Combatant;
import rpg.character.status.enums.StatusType;

public class StunEffect extends StatusEffect {

    public StunEffect() {
        super(StatusType.STUN, 0, 1);
    }

    @Override
    public void applyEffect(Combatant combatant) {
        combatant.setStunned(true);
    }

    @Override
    public void removeEffect(Combatant combatant) {
        combatant.setStunned(false);
    }
}
