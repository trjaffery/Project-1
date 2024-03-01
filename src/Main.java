import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        ArrayEventList event_array = new ArrayEventList();

        FileInputStream fileByteStream = new FileInputStream("src/events.txt");
        Scanner fileScanner = new Scanner(fileByteStream);

        while (fileScanner.hasNext()) {
            String command = fileScanner.next();
            if (fileScanner.hasNextInt() && command.equals("I")) {
                int duration = fileScanner.nextInt();
                Timer timer = new Timer(duration);
                event_array.insert(timer);
                System.out.println(timer);
            }
            else if (command.equals("R")) {

            }
        }
    }
}
