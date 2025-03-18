package rpg.inventory;

import java.util.ArrayList;
import java.util.List;

import rpg.character.base.Character;
import rpg.item.Item;
import rpg.item.Equipment;
import static rpg.util.IO_Manager.print;

public class Inventory {
    protected List<Item<?>> items;
    protected Character owner;
    protected int itemCount;
    protected int capacity;
    protected int maxCapacity = 100;

    public Inventory(int capacity, Character owner) {
        this.items = new ArrayList<>();
        this.capacity = capacity;
        this.owner = owner;
    }

    public void addItem(Item<?> item) {
        if (itemCount >= capacity) {
            print(owner.getName() + "의 인벤토리가 가득 찼습니다.", true);
            return;
        }
        items.add(item);
        itemCount++;
    }
    
    public void removeItem(Item<?> item) {
        if (items.contains(item)) {
            items.remove(item);
            itemCount--;
        } else {
            print(owner.getName() + "의 인벤토리에 해당 아이템이 없습니다.", true);
        }
    }

    public void expandCapacity(int amount) {
        if (capacity + amount > maxCapacity) {
            print(owner.getName() + "의 인벤토리가 최대 크기에 도달했습니다.", true);
            return;
        }
        capacity += amount;
    }

    public boolean isThereItem(Item<?> item) {
        return items.contains(item);
    }

    public List<Item<?>> getItems() {
        return items;
    }

    public void useItem(Item<?> item) {
        if (!isThereItem(item)) {
            print(owner.getName() + "의 인벤토리에 해당 아이템이 없습니다.", true);
            return;
        }

        if (item instanceof Equipment) {
            owner.equip((Equipment) item);
            removeItem(item);
        } else {
            consumeItem(item);
        }
    }

    private void consumeItem(Item<?> item) {
        // 일회용 아이템 사용 로직
        print(owner.getName() + "이(가) " + item.getName() + "을(를) 사용했습니다.", true);
        removeItem(item);  // 사용 후 인벤토리에서 제거
    }
}
