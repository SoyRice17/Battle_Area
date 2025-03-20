package rpg.item.enums.expendablesEnums;

public enum ThrowableType {
    /**
     * 투척 무기의 종류
     */
    DAMAGE("데미지형", "직접적인 데미지를 입힙니다."),
    EXPLOSIVE("폭발형", "범위 데미지를 입힙니다."),
    UTILITY("유틸리티형", "특수 효과를 발동합니다.");
        
    private final String name;
    private final String description;
        
    ThrowableType(String name, String description) {
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
