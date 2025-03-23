package rpg.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

/**
 * 입출력 관리 클래스입니다.
 * 
 * <p>
 * 주요 기능:
 * <ul>
 *  <li>메시지 출력</li>
 *  <li>메시지 입력</li>
 * </ul>
 * 
 * <p>
 * 사용 예시:
 * <pre>
 *     IO_Manager.print("메시지");
 *     String input = IO_Manager.input();
 * </pre>
 */
public class IO_Manager {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    /**
     * 메시지를 줄바꿈 없이 출력합니다.
     * 
     * @param message 출력할 메시지
     */
    public static void print(String message) {
        try {
            writer.write(message);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 메시지를 줄바꿈 여부에 따라 출력합니다.
     * 
     * @param message 출력할 메시지
     * @param newLine 줄바꿈 여부
     */
    public static void print(String message, boolean newLine) {
        if (newLine) {
            print(message + "\n");
        } else {
            print(message);
        }
    }

    /**
     * 메시지를 입력받습니다.
     * 
     * @return 입력받은 메시지
     */
    public static String input() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 메시지를 안내 문자열로 안내하고 입력받습니다.
     * 
     * @param message 출력할 메시지
     * @return 입력받은 메시지
     */
    public static String input(String message) {
        print(message, false);
        return input();
    }
}
