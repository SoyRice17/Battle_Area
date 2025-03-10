package rpg.character.status;

import rpg.battle.Combatant;
import rpg.character.status.enums.StatusType;

public abstract class StatusEffect {
    protected final StatusType statusType;
    protected final int value;
    protected int duration;

    public StatusEffect(StatusType statusType, int value, int duration) {
        this.statusType = statusType;
        this.value = value;
        this.duration = duration;
    }

    public abstract void applyEffect(Combatant combatant);
    public abstract void removeEffect(Combatant combatant);

    public boolean updateDuration() {
        duration--;
        return duration > 0;
    }

    public StatusType getStatusType() { return statusType; } 
    public int getValue() { return value; } 
    public int getDuration() { return duration; }
}
