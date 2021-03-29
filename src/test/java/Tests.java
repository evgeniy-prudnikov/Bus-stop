import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.util.Random;


public class Tests {

    String[] company = {"Posh","Grotty"};

    @Test
    public void test() throws Exception {

        FileOutputStream fileOutputStream = new FileOutputStream("input.txt");
        Random random = new Random();
        while (random.nextInt() % 10 != 7) {

            long a = Math.abs(random.nextLong() % 86400000);
            long b = Math.abs(random.nextLong() % 60 * 60 * 1000);
            b = a + b;

            String str = company[Math.abs(random.nextInt() % 2)] + " " + Service.showTime(a) +
                    " " + Service.showTime(b) + "\n";

            fileOutputStream.write(str.getBytes());
        }
        fileOutputStream.flush();
        fileOutputStream.close();


        Main.main(new String[]{"input.txt"});


    }


}
