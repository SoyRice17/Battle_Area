package rpg.battle;

import rpg.skill.Skill;

public class BattleAction {
    private final ActionType type;
    private final Combatant actor;
    private final Combatant target;
    private final Skill skill;

    public BattleAction(ActionType type, Combatant actor, Combatant target) {
        this(type, actor, target, null);
    }

    public BattleAction(ActionType type, Combatant actor, Combatant target, Skill skill) {
        this.type = type;
        this.actor = actor;
        this.target = target;
        this.skill = skill;
    }

    public ActionType getType() { return type; }
    public Combatant getActor() { return actor; }
    public Combatant getTarget() { return target; }
    public Skill getSkill() { return skill; }
} 