package chapter2.item9.trywithresources;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
* try-with-resources 에도 catch문 사용 가능함.
* */
public class TopLineWithDefault {
    // 코드 9-5 try-with-resources를 catch 절과 함께 쓰는 모습 (49쪽)
    static String firstLineOfFile(String path, String defaultVal) {
        try ( BufferedReader br = new BufferedReader(new FileReader(path)) ) // 1. resources alloc
        {
            return br.readLine(); // 2. behavior
        } catch (IOException e) {
            return defaultVal; // 3. if exception
        }
    }

    public static void main(String[] args) throws IOException {
        String path = args[0];
        System.out.println(firstLineOfFile(path, "Toppy McTopFace"));
    }
}
