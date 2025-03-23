package rpg.inventory;

import java.util.ArrayList;
import java.util.List;

import rpg.character.Character;
import rpg.item.Item;
import rpg.item.Equipment;
import static rpg.util.IO_Manager.print;

public class Inventory {
    protected List<Item> items;
    protected Character owner;
    protected int itemCount;
    protected int capacity;
    protected int maxCapacity = 100;

    public Inventory(int capacity, Character owner) {
        this.items = new ArrayList<>();
        this.capacity = capacity;
        this.owner = owner;
    }

    public void addItem(Item item) {
        if (itemCount >= capacity) {
            print(owner.getName() + "의 인벤토리가 가득 찼습니다.", true);
            return;
        }
        items.add(item);
        itemCount++;
    }
    
    public void removeItem(Item item) {
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

    public boolean isThereItem(Item item) {
        return items.contains(item);
    }

    public String getItems() { // 아이템 리스트를 번호를 매겨 문자열 여러줄로 반환
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            sb.append((i + 1) + ". " + items.get(i).toString());
            if (i < items.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public void useItem(Item item) {
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
    public void useItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                useItem(item);
                return;
            }
        }
        print(owner.getName() + "의 인벤토리에 해당 아이템이 없습니다.", true);
    }

    private void consumeItem(Item item) {
        // 일회용 아이템 사용 로직, 적에게 사용하는 경우 적의 상태 변화 추가
        print(owner.getName() + "이(가) " + item.getName() + "을(를) 사용했습니다.", true);
        removeItem(item);  // 사용 후 인벤토리에서 제거
    }

    public int getCapacity() {
        return capacity;
    }
    public int getItemCount() {
        return itemCount;
    }
    public String getItemName(int index) {
        return items.get(index).getName();
    }
}
