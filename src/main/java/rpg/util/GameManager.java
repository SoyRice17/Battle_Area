package rpg.util;

import rpg.character.base.Character;
import static rpg.util.IO_Manager.*;

public class GameManager {
    private Character player;
    private boolean isGameRunning;

    private GameManager() {
        this.isGameRunning = false;
    }

    private static class GameManagerHolder {
        private static final GameManager INSTANCE = new GameManager();
    }

    public static GameManager getInstance() {
        return GameManagerHolder.INSTANCE;
    }

    public void startGame() {
        if (isGameRunning) {
            print("게임이 이미 실행중입니다.", true);
            return;
        }

        isGameRunning = true;
        initializeGame();
        gameLoop();
    }

    private void initializeGame() {
        print("=== RPG 게임 시작 ===", true);
        // 캐릭터 생성 로직
        createCharacter();
    }

    private void createCharacter() {
        String name = input("캐릭터의 이름을 입력하세요: ");
        // 캐릭터 생성 로직은 나중에 구현
        print(name + " 캐릭터가 생성되었습니다!", true);
    }

    private void gameLoop() {
        while (isGameRunning) {
            showMainMenu();
            int choice = Integer.parseInt(input("선택: "));
            processMainMenuChoice(choice);
        }
    }

    private void showMainMenu() {
        print("\n=== 메인 메뉴 ===", true);
        print("1. 전투", true);
        print("2. 상태 확인", true);
        print("3. 인벤토리", true);
        print("4. 저장", true);
        print("5. 종료", true);
    }

    private void processMainMenuChoice(int choice) {
        switch (choice) {
            case 1:
                startBattle();
                break;
            case 2:
                showStatus();
                break;
            case 3:
                openInventory();
                break;
            case 4:
                saveGame();
                break;
            case 5:
                endGame();
                break;
            default:
                print("잘못된 선택입니다.", true);
        }
    }

    private void startBattle() {
        print("전투를 시작합니다!", true);
        // 전투 시스템 구현 예정
    }

    private void showStatus() {
        print("캐릭터 상태를 표시합니다.", true);
        // 상태 표시 시스템 구현 예정
    }

    private void openInventory() {
        print("인벤토리를 엽니다.", true);
        // 인벤토리 시스템 구현 예정
    }

    private void saveGame() {
        print("게임을 저장합니다.", true);
        // 저장 시스템 구현 예정
    }

    private void endGame() {
        print("게임을 종료합니다.", true);
        isGameRunning = false;
    }

    // Getter & Setter
    public Character getPlayer() { return player; }
    public void setPlayer(Character player) { this.player = player; }
    public boolean isGameRunning() { return isGameRunning; }
}
