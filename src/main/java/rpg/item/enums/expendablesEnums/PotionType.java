package rpg.item.enums.expendablesEnums;

public enum PotionType {
    /**
     * 포션의 효과 종류
     */
    HP_RESTORE("체력 회복", "체력을 회복시킵니다."),
    MP_RESTORE("마나 회복", "마나를 회복시킵니다."),
    ALL_RESTORE("전체 회복", "체력과 마나를 모두 회복시킵니다."),
    BUFF("버프", "일시적으로 능력을 상승시킵니다."),
    CURE("치료", "상태이상을 제거합니다.");

    private final String name;
    private final String description;

    PotionType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}