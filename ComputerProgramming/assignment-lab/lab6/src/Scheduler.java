import java.util.List;

public class Scheduler<T extends Worker> {
    private static final int waitms = 400;
    private List<T> workers;
    private int call=-1;

    public Scheduler(List<T> workers) {
        this.workers = workers;
    }

    T schedule() {
        return workers.get(0);
    }

    T schedule(int index) {
        // TODO: problem3.1 - Add scheduling logic
        if(index<workers.size()) {
            return workers.get(index);
        } else {
            return schedule();
        }
    }

    T scheduleRandom() {
        // TODO: problem3.1 - Add scheduling logic
        int select = (int) (0 + Math.random() * workers.size());
        return workers.get(select);
    }

    T scheduleFair() {
        // TODO: problem3.2 - Add scheduling logic
        int lastCall=10000000;
        int index = 0;
        for(int i=0; i<workers.size(); i++) {
            if(workers.get(i).getLastCall()<lastCall) {
                lastCall = workers.get(i).getLastCall();
                index = i;
            }
        }
        this.call++;
        workers.get(index).setLastCall(this.call);
        return workers.get(index);
    }

    static void delay() {
        try {
            Thread.sleep(waitms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
