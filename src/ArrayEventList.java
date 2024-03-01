public class ArrayEventList implements FutureEventList {
    private Event[] eventArray = new Event[5];
    private int sim_time = 0;


    public Event removeFirst(Event e) {

        int sizeArray = eventArray.length;
        Event removedEvent = eventArray[0];
        Event[] copy_event_array = new Event[sizeArray];

        System.arraycopy(eventArray, 1, copy_event_array, 0, sizeArray);
        System.arraycopy(copy_event_array, 0, eventArray, 0, sizeArray);
        return removedEvent;
    }

    public void doubleCapacity(int arraySize) {
        Event[] copy_event_array = new Event[arraySize];
        System.arraycopy(eventArray, 0, copy_event_array, 0, arraySize);
        eventArray = new Event[arraySize * 2];
        System.arraycopy(copy_event_array, 0, eventArray, 0, arraySize);
    }
    @Override
    public void insert(Event e) {
        // check for if the array is filled (i.e. if the last element is not null)
        // if it's not null, then double the capacitance of the array
        int arraySize = eventArray.length;
        if (eventArray[arraySize - 1] != null) {
            doubleCapacity(arraySize);
        }
        for (int i = 0; i < arraySize; i++) {
            if (eventArray[i] == null && i == 0) {
                eventArray[i] = e;
            }
            if (eventArray[i] != null && e.getArrivalTime() < eventArray[i].getArrivalTime()) {
                for (int j = arraySize - 1; j > i; j--) {
                    if (eventArray[j - 1] == null) {
                        continue;
                    }
                    eventArray[j] = eventArray[j - 1];
                }
                eventArray[i] = e;
            }
            System.out.println(eventArray[i]);
        }
    }

    @Override
    public boolean remove(Event e) {
        // recursive binary search

        return true;
    }

    public int size() {
        return 0;
    }

    public int capacity() {
        return 0;
    }

    @Override
    public int getSimulationTime() {
        return sim_time;
    }
}
