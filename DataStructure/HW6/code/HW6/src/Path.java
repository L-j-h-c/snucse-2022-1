import java.util.ArrayList;

public class Path {
    ArrayList<Station> savedPath = new ArrayList<>();
    Long totalWeight = 0L;

    public Path() {

    }

    public void replacePath(ArrayList<Station> path, Long weight) {
        this.savedPath = path;
        totalWeight = weight;
    }
}
