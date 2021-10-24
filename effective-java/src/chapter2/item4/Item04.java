package Ex01;

public class Item04 {
    // Lombok의 accessLevel 을 사용하자,,
    // @accessLevel로 편하게 살수있다
    public static void main(String[] args) {

        class UtilityClass {
            // 기본 생성자가 만들어지는 것을 막는다(인스턴스화 방지용).
            private UtilityClass() {
                throw new AssertionError();
            }
        }
    }
}
