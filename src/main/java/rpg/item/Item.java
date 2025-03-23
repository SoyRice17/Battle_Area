package rpg.item;

/**
 * 아이템을 나타내는 인터페이스입니다.
 * 
 * <p>
 * 주요 기능:
 * <ul>
 *  <li>아이템 가격 확인</li>
 *  <li>아이템 이름 확인</li>
 *  <li>아이템 설명 확인</li>
 * </ul>
 */
public interface Item {
    int getPrice();
    String getName();
    String getDescription();
}
