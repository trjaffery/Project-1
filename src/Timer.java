public class Timer implements Event {
    private static int unique_id = 0;
    private int arrival_time;
    private int insertion_time;
    private int sim_time;

    // arrival time = insertion time + duration
    public Timer (int timer_duration) {
        unique_id++;
    }

    public void setInsertionTime(int currentTime) {

    }

    public int getInsertionTime() {

    }



    public void handle () {
        System.out.println("Timer " + unique_id + "handled (arrival time: " + arrival_time + ")");
    }

    public int getArrivalTime() {

    }

    public void cancel (int currentTime) {
        System.out.println("Timer " + unique_id + " canceled at time: " + currentTime);
    }

    public String toString() {
        return "- Timer " + unique_id + "(insertion time: " + insertion_time + ", arrival time: " + arrival_time + ")";
    }

}
