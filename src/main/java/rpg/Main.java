package rpg;

import rpg.util.GameManager;

public class Main {
    public static void main(String[] args) {
        // 싱글톤 인스턴스 가져오기
        GameManager gameManager = GameManager.getInstance();
        
        // 게임 시작!
        gameManager.startGame();
    }
}
