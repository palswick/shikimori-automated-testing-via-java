public class Utilities {
    static void waiter() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {

        }
    }
}
