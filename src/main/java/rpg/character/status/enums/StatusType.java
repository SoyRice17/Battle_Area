package rpg.character.status.enums;


public enum StatusType {
    
    // 디버프 
    STUN ("스턴", "행동 불능", false),
    BLEEDING ("출혈", "매 턴마다 피해를 입히고 출혈 효과를 부여합니다.", false),
    POISON ("독", "매 턴마다 피해를 입히고 독 효과를 부여합니다.", false),
    BURN ("화상", "매 턴마다 피해를 입히고 화상 효과를 부여합니다.", false),
    FROST ("냉기", "매 턴마다 피해를 입히고 냉기 효과를 부여합니다.", false),

    ATK_UP ("공격력 상승", "공격력을 상승시킵니다.", true),
    DEF_UP ("방어력 상승", "방어력을 상승시킵니다.", true),

    ATK_DOWN ("공격력 감소", "공격력을 감소시킵니다.", false),
    DEF_DOWN ("방어력 감소", "방어력을 감소시킵니다.", false);

    private final String name;
    private final String description;
    private final boolean isBuff;

    StatusType(String name, String description, boolean isBuff) {
        this.name = name;
        this.description = description;
        this.isBuff = isBuff;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isBuff() {
        return isBuff;
    }
}
