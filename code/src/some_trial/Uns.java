package some_trial;
import java.util.concurrent.atomic.AtomicInteger;
public class Uns {

    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger(0);
        ai.incrementAndGet();
        System.out.println(ai);

    }
}
