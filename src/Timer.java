public class Timer implements Event {
    private static int unique_id = -1;
    private int arrival_time;
    private int insertion_time = 0;
    private int sim_time;

    // arrival time = insertion time + duration
    public Timer (int timer_duration) {
        arrival_time = timer_duration + insertion_time;
        unique_id++;
    }

    public void setInsertionTime(int currentTime) {

    }

    public int getInsertionTime() {
        return 0;
    }



    public void handle () {
        System.out.println("Timer " + unique_id + "handled (arrival time: " + arrival_time + ")");
    }

    public int getArrivalTime() {
        return 0;
    }

    public void cancel (int currentTime) {
        System.out.println("Timer " + unique_id + " canceled at time: " + currentTime);
    }

    public String toString() {
        return "- Timer " + unique_id + "(insertion time: " + insertion_time + ", arrival time: " + arrival_time + ")";
    }

}
