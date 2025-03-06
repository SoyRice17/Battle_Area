package main.item;

public interface Item<Type> {
    Type getType();
    int getPrice();
    String getName();
    String getDescription();
    void use(Character character);
}
