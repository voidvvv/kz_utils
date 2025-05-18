import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Test01 {

    @Test
    public void test () {
        String aliam = Character.toString(0x1f47d);
        System.out.println(aliam);
        System.out.println(Arrays.toString(aliam.getBytes()));
    }
}
