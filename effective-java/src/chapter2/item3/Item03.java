package Ex01;

public class Item03 {
    // spring은 이미 singleton 패턴,,
    public static void main(String[] args) {
    
        // Field 타입
        Elvis elvis = Elvis.INSTANCE;
        elvis.leaveTheBuilding();

        // 정적 팩토리 방식
        Elvis2 elvis2 = Elvis2.getInstance();
        elvis.leaveTheBuilding();

        // 이넘 방식
        Elvis3 elvis3 = Elvis3.INSTANCE;
        elvis3.leaveTheBuilding();

    }

    public static class Elvis {
        public static final Elvis INSTANCE = new Elvis();

        private Elvis() {
        }

        public void leaveTheBuilding() {
            System.out.println("going!!!");
        }

    }

    public static class Elvis2 {
        private static final Elvis2 INSTANCE = new Elvis2();

        private Elvis2() {
        }

        public static Elvis2 getInstance() {
            return INSTANCE;
        }

        public void leaveTheBuilding() {
            System.out.println("im goinG!!");
        }

        // 이 메서드는 보통 클래스 바깥(다른 클래스)에 작성해야 한다!
    }
    public enum Elvis3 {
        INSTANCE;

        public void leaveTheBuilding() {
            System.out.println("간다!!");
        }

    }
}
