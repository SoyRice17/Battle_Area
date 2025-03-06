package main.character.base;

import main.monster.Monster;
import main.item.Item;  

public abstract class Character {
    protected String name;
    protected Job job;
    protected int level;
    protected int exp;
    protected int hp;
    protected int mp;
    protected int atk;
    protected int def;

    protected final int MAX_LEVEL = 100;

    protected Character(String name) {
        this.name = name;
        this.level = 1;
        this.exp = 100;
        this.hp = 100;
        this.mp = 50;
        this.atk = 10;
        this.def = 5;
    }

    public abstract void attack(Monster target);
    public abstract void defend(int damage);
    public abstract void useItem(Item<?> item);

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMp() {
        return mp;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public Job getJob() {
        return job;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHp(int hp) {
        this.hp = Math.max(0, hp);
    }

    public void setMp(int mp) {
        this.mp = Math.max(0, mp);
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public void setJob(Job job) {
        this.job = job;
        this.hp += job.getBonusHp();
        this.mp += job.getBonusMp();
        this.atk += job.getBonusAtk();
        this.def += job.getBonusDef();
    }
}
