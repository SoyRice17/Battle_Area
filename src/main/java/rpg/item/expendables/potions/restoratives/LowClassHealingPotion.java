package rpg.item.expendables.potions.restoratives;

import rpg.item.enums.expendablesEnums.PotionType;
import rpg.item.expendables.potions.Potion;
import rpg.battle.Combatant;
import rpg.skill.enums.TargetType;

public class LowClassHealingPotion implements Potion {



    @Override
    public int getPrice() {
        return 10;
    }

    @Override
    public String getName() {
        return "하급 빨간 물약";
    }

    @Override
    public String getDescription() {
        return "체력을 회복시키는 물약입니다.";
    }

    @Override
    public void use(Combatant user, Combatant target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'use'");
    }

    @Override
    public int getCanUseLevel() {
        return 1;
    }

    @Override
    public TargetType getTargetType() {
        return TargetType.SELF;
    }

    @Override
    public PotionType getPotionType() {
        return PotionType.HP_RESTORE;
    }

    @Override
    public int getEffectPower() {
        return 10;
    }

    @Override
    public int getDuration() {
        return 0;
    }

    @Override
    public boolean isPercentageBased() {
        return false;
    }
}
