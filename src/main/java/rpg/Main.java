package rpg;

import rpg.character.characters.MainCharacter;
import rpg.character.base.Character;
import static rpg.util.IO_Manager.*;  // print와 input 메소드 사용

public class Main {
    public static void main(String[] args) {
        print("=== 로그라이크 턴제 RPG ===", true);
        print("새로운 모험을 시작합니다!", true);
        
        // 캐릭터 이름 입력 받기
        print("\n당신의 이름을 입력해주세요: ", false);
        String heroName = input();
        
        while (heroName == null || heroName.trim().isEmpty()) {
            print("올바른 이름을 입력해주세요: ", false);
            heroName = input();
        }

        // 주인공 캐릭터 생성
        Character hero = new MainCharacter(heroName);
        print("\n[캐릭터 생성 완료]", true);
        print("이름: " + hero.getName(), true);
        print("직업: " + hero.getJob().getJobName(), true);
        print("레벨: " + hero.getLevel(), true);
        
        print("\n기본 스탯:", true);
        print("HP: " + hero.getHp(), true);
        print("MP: " + hero.getMp(), true);
        print("공격력: " + hero.getAtk(), true);
        print("방어력: " + hero.getDef(), true);

        print("\n" + heroName + "님의 모험이 시작됩니다...", true);
    }
}
