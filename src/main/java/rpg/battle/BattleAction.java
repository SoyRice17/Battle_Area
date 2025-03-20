package rpg.battle;

import rpg.skill.Skill;

public class BattleAction {
    private final ActionType type;
    private final Combatant actor;
    private final Combatant target;
    private final Skill skill;
    private final String itemName;

    public BattleAction(ActionType type, Combatant actor, Combatant target) {
        this.type = type;
        this.actor = actor;
        this.target = target;
        this.skill = null;
        this.itemName = null;
    }

    public BattleAction(ActionType type, Combatant actor, Combatant target, Skill skill) {
        this.type = type;
        this.actor = actor;
        this.target = target;
        this.skill = skill;
        this.itemName = null;
    }

    public BattleAction(ActionType type, Combatant actor, Combatant target, String itemName) {
        this.type = type;
        this.actor = actor;
        this.target = target;
        this.itemName = itemName;
        this.skill = null;
    }

    public ActionType getType() { return type; }
    public Combatant getActor() { return actor; }
    public Combatant getTarget() { return target; }
    public Skill getSkill() { return skill; }
    public String getItemName() { return itemName; }
}