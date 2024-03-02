import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) throws IOException {

        ArrayEventList eventList = new ArrayEventList();
        ArrayList<Timer> timerArray = new ArrayList<Timer>();
        FileInputStream fileByteStream = new FileInputStream("events.txt");
        Scanner fileScanner = new Scanner(fileByteStream);

        int currentTime = 0;
        while (fileScanner.hasNext()) {
            String command = fileScanner.next();
            if (fileScanner.hasNextInt() && command.equals("I")) {
                int duration = fileScanner.nextInt();
                Timer timer = new Timer(duration);
                timer.setInsertionTime(currentTime);
                timerArray.add(timer);
                eventList.insert(timer);

            }
            else if (command.equals("R")) {
                Timer removed_timer = timerArray.getFirst();
                System.out.println(removed_timer);
                if (removed_timer != null) {
                    eventList.removeFirst(removed_timer);
                    removed_timer.handle();
                    timerArray.remove(removed_timer);
                    currentTime = removed_timer.getArrivalTime(); // updates sim time
                }
            }
            else if (command.equals("C")) {
                int timerIndex = fileScanner.nextInt();
                Timer timerCanceled = timerArray.get(timerIndex);
                if (timerCanceled != null) {
                    eventList.remove(timerCanceled);
                    timerCanceled.cancel(currentTime);
                    for (int i = timerIndex + 1; i < timerArray.size(); i++) {
                        Timer nextTimer = timerArray.get(i);
                        nextTimer.setInsertionTime(currentTime);
                    }
                }
            }
        }
        System.out.println("Future event list size: " + eventList.size());
        System.out.println("Future event list capacity: " + eventList.capacity());
    }
}
