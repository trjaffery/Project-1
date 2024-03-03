import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        ArrayEventList eventList = new ArrayEventList(); // this is where all the timers will be added and all the operations will be performed on
        ArrayList<Timer> timerArray = new ArrayList<Timer>(); // this arraylist is used for handling/removing items
        ArrayList<Timer> timerCancelList = new ArrayList<Timer>(); // this arraylist is used for canceling items
        FileInputStream fileByteStream = new FileInputStream("events.txt");
        Scanner fileScanner = new Scanner(fileByteStream);

        int currentTime = 0; // currentTime gets passed into setInsertion time and cancel commands
        // reads through the whole file
        while (fileScanner.hasNext()) {
            String command = fileScanner.next(); // command is either I, R or C
            if (fileScanner.hasNextInt() && command.equals("I")) {
                int duration = fileScanner.nextInt(); // duration for inserting the timer
                Timer timer = new Timer(duration);
                timer.setInsertionTime(currentTime); // making sure the current time is being set as the insertime time
                // adding the timer to each list to keep track
                timerArray.add(timer);
                timerCancelList.add(timer);
                eventList.insert(timer);

            }
            else if (command.equals("R")) {
                // remove the first timer and handle it, updates sim time
                Timer lowestArrivalTime = timerArray.getFirst(); // getting the first timer inserted
                // for loop to get the one with the lowest arrival time because that's the one being removed
                for (int i = 0; i < timerArray.size(); i++) {
                    if (timerArray.get(i).getArrivalTime() < lowestArrivalTime.getArrivalTime()) {
                        lowestArrivalTime = timerArray.get(i);
                    }
                }
                if (lowestArrivalTime != null) {
                    Event canceledEvent = eventList.removeFirst(lowestArrivalTime); // remove the event, updates size of future event list
                    canceledEvent.handle(); // handle the event
                    boolean removed = timerArray.remove(lowestArrivalTime); // gets the bool value if the event was removed
                    if (removed) {
                        currentTime = lowestArrivalTime.getArrivalTime(); // updates sim time
                    }
                }
            }
            else if (command.equals("C")) {
                // cancel event, update size of list, doesn't update sim time
                int timerIndex = fileScanner.nextInt(); // gets the index of timer that needs to be removed
                // checks the cancel list(the one that keeps all the timers to maintain the correct index)
                Timer timerCanceled = timerCancelList.get(timerIndex);
                boolean removed = eventList.remove(timerCanceled); // removes the event and returns true if removed
                if (removed) {
                    timerCanceled.cancel(currentTime); // cancel event and print out to screen
                }

            }
        }
        System.out.println("Future event list size: " + eventList.size()); // outputs size of list at the end
        System.out.println("Future event list capacity: " + eventList.capacity()); // outputs capacity of list at the end
    }
}
