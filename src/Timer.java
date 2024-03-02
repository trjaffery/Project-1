public class Timer implements Event {
    private final int uniqueId;
    private static int num_timers = -1;
    private int arrival_time;
    private final int duration;
    private int insertion_time = -1;

    // arrival time = insertion time + duration
    public Timer (int timer_duration) {
        duration = timer_duration;
        num_timers++;
        uniqueId = num_timers;
    }

    public void setInsertionTime(int currentTime) {
        insertion_time = currentTime;
        arrival_time = duration + currentTime;
    }

    public int getInsertionTime() {
        return insertion_time;
    }

    public void handle () {
        System.out.println("Timer " + uniqueId + " handled (arrival time: " + arrival_time + ")");
    }

    public int getArrivalTime() {
        return arrival_time;
    }

    public void cancel (int currentTime) {
        System.out.println("Timer " + uniqueId + " canceled at time: " + currentTime);
    }

    public String toString() {
        return "- Timer " + uniqueId + " (insertion time: " + insertion_time + ", arrival time: " + arrival_time + ")";
    }

}
