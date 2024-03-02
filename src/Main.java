import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        ArrayEventList eventList = new ArrayEventList(); //
        ArrayList<Timer> timerArray = new ArrayList<Timer>(); // this arraylist is used for handling/removing items
        ArrayList<Timer> timerCancelList = new ArrayList<Timer>(); // this arraylist is used for canceling items
        FileInputStream fileByteStream = new FileInputStream("events.txt");
        Scanner fileScanner = new Scanner(fileByteStream);

        int currentTime = 0; // currentTime gets passed into setInsertion time and cancel commands
        while (fileScanner.hasNext()) {
            String command = fileScanner.next();
            if (fileScanner.hasNextInt() && command.equals("I")) {
                int duration = fileScanner.nextInt();
                Timer timer = new Timer(duration);
                timer.setInsertionTime(currentTime);
                timerArray.add(timer);
                timerCancelList.add(timer);
                eventList.insert(timer);

            }
            else if (command.equals("R")) {
                Timer lowestArrivalTime = timerArray.getFirst();
                for (int i = 0; i < timerArray.size(); i++) {
                    if (timerArray.get(i).getArrivalTime() < lowestArrivalTime.getArrivalTime()) {
                        lowestArrivalTime = timerArray.get(i);
                    }
                }
                if (lowestArrivalTime != null) {
                    Event canceledEvent = eventList.removeFirst(lowestArrivalTime);
                    canceledEvent.handle();
                    boolean removed = timerArray.remove(lowestArrivalTime); // gets the bool value if the event was removed
                    if (removed) {
                        currentTime = lowestArrivalTime.getArrivalTime(); // updates sim time
                    }
                }
            }
            else if (command.equals("C")) {
                int timerIndex = fileScanner.nextInt();
                Timer timerCanceled = timerCancelList.get(timerIndex);
                boolean removed = eventList.remove(timerCanceled);
                if (removed) {
                    timerCanceled.cancel(currentTime);
                }

            }
        }
        System.out.println("Future event list size: " + eventList.size());
        System.out.println("Future event list capacity: " + eventList.capacity());
    }
}
