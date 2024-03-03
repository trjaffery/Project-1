public class Timer implements Event {
    private final int uniqueId; // unique id of each timer
    private static int num_timers = -1; // keeps track of how many timers have been created
    private int arrival_time; // holds arrival time which is insertion time + duration
    private final int duration; // used to hold the value of duration which is passed in as an argument for the timer being created
    private int insertion_time; // gets set everytime a timer gets inserted into arraylist

    // arrival time = insertion time + duration
    public Timer (int timer_duration) {
        duration = timer_duration;
        num_timers++;
        uniqueId = num_timers;
    }

    // sets insertion time when the timer is created
    public void setInsertionTime(int currentTime) {
        insertion_time = currentTime;
        arrival_time = duration + currentTime;
    }

    // gets called when insertion time is needed
    public int getInsertionTime() {
        return insertion_time;
    }

    // gets used when an event gets handled
    public void handle () {
        System.out.println("Timer " + uniqueId + " handled (arrival time: " + arrival_time + ")");
    }

    // will be used in main and in ArrayEventList to get the arrival time of each timer and compare them
    public int getArrivalTime() {
        return arrival_time;
    }

    // gets called to print out that the timer was canceled
    public void cancel (int currentTime) {
        System.out.println("Timer " + uniqueId + " canceled at time: " + currentTime);
    }

    // gets called everytime the timer is printed
    public String toString() {
        return "- Timer " + uniqueId + " (insertion time: " + insertion_time + ", arrival time: " + arrival_time + ")";
    }

}
