
public class Utilities {
    static void waiter(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {

        }
    }
}

