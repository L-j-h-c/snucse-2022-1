public class Work {
    static int idCount=-1;
    private int id = 0;

    Work() {
        idCount++;
        this.id = idCount;
    }
    // TODO: problem1
    int getId() {
        return this.id;
    }
}
