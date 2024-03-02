public class ArrayEventList implements FutureEventList {
    private Event[] eventArray = new Event[5];
    int eventArraySize = eventArray.length;
    private int size = 0;
    private int simTime = 0;


    public Event removeFirst(Event e) {
        if (e != eventArray[0]) {
            return null;
        }
        Event removedEvent = eventArray[0];
        for (int i = 0; i < eventArraySize; i++) {
            if (i == eventArraySize - 1) {
                eventArray[i] = null;
                break;
            }
            eventArray[i] = eventArray[i + 1];
        }
        size--;
        simTime = removedEvent.getArrivalTime();
        return removedEvent;
    }

    private void doubleCapacity() {
        Event[] copy_event_array = new Event[eventArraySize];
        System.arraycopy(eventArray, 0, copy_event_array, 0, eventArraySize);
        eventArray = new Event[eventArraySize * 2];
        System.arraycopy(copy_event_array, 0, eventArray, 0, eventArraySize);
    }
    @Override
    public void insert(Event e) {
        // check for if the array is filled (i.e. if the last element is not null)
        // if size = capacity, then double the capacitance of the array
        if (size == capacity()) {
            doubleCapacity();
            eventArraySize *= 2;
        }
        for (int i = 0; i < eventArraySize; i++) {
            // if no timers in array or timer is largest
            if (eventArray[i] == null) {
                eventArray[i] = e;
                break;
            }
            // if timer is larger than the timer in the ith position, continue
            if (eventArray[i].getArrivalTime() < e.getArrivalTime()) {
                continue;
            }
            else {
                // shift every item in the array by 1 to the right and insert e into the ith position
                for (int j = eventArraySize - 2; j >= i; j--) {

                    if (eventArray[j] != null) {
                        eventArray[j + 1] = eventArray[j];
                    }
                }
                eventArray[i] = e;
                break;
            }
        }
        e.setInsertionTime(simTime);
        System.out.println(e);
        size++;
    }

    @Override
    public boolean remove(Event e) {
        // calls private binary search method passing in bounds of array
        int index = binarySearch(e, 0, eventArraySize - 1);
        // if it returns as -1 then the Event was not found
        if (index == -1) {
            return false;
        }
        // closes the gap up in the array after removal
        for (int i = index; i < eventArraySize - 1; i++) {
            eventArray[i] = eventArray[i + 1];
        }
        size--; // lowers the size field
        return true;
    }

    private int binarySearch(Event e, int left, int right) {
        // Timer was not found
        if (right < left) {
            return -1;
        }
        // new mid value
        int mid = left + (right - left) / 2;
        // found
        if (eventArray[mid] == e) {
            return mid;
        }
        // search upper half
        else if (eventArray[mid].getArrivalTime() < e.getArrivalTime()) {
            return binarySearch(e, mid + 1, right);
        }
        // search lower half
        else {
            return binarySearch(e, left, mid - 1);
        }
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return eventArraySize;
    }

    @Override
    public int getSimulationTime() {
        return simTime;
    }
}
