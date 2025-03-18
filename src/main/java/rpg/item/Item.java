package rpg.item;

public interface Item<Type> {
    Type getType();
    int getPrice();
    String getName();
    String getDescription();
}
