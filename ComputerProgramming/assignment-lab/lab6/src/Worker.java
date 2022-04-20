import java.util.Queue;

public abstract class Worker {
    Queue<Work> workQueue;
    static int idCount=-1;
    protected int id;
    private int lastCall=-1;

    public abstract void run();

    Worker(Queue<Work> workQueue) {
        this.workQueue = workQueue;
        idCount++;
        this.id=idCount;
        // TODO: problem1
    }

    void report(String msg){
        System.out.println(msg);
        // TODO: problem2
    }

    int getLastCall() {
        return this.lastCall;
    }

    void setLastCall(int last) {
        this.lastCall = last;
    }

}

class Producer extends Worker {
    Producer(Queue<Work> workQueue) {
        super(workQueue);
    }

    @Override
    public void run() {
        if(workQueue.size()<20) {
            Work work = new Work();
            workQueue.add(work);
            report("worker "+this.id+" produced work"+work.getId());
        } else {
            report("worker "+this.id+" fails to produce a work (workQueue is full)");
        }
        // TODO: problem2
    }
}

class Consumer extends Worker {
    Consumer(Queue<Work> workQueue) {
        super(workQueue);
    }

    @Override
    public void run() {
        int probabillity = (int) (1 + Math.random() * 1);
        if(probabillity<=1) {
            int workID = workQueue.peek() != null ? workQueue.peek().getId() : -1;
            workQueue.poll();
            report("worker "+this.id+" consumed work"+workID);
        } else {
            report("worker "+this.id+" failed to consume a work");
        }
    }
}
