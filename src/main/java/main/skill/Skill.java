package main.skill;

public interface Skill {
    String getName();
    String getDescription();
    int getPower();
    int getCost();
    void use(Character character);
}
