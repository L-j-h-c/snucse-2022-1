import java.util.ArrayList;

public class Path implements Cloneable {
    ArrayList<Station> savedPath = new ArrayList<>();
    Long totalWeight = 0L;

    public Path() {

    }

    @Override
    public Path clone() {
        try {
            Path clone = (Path) super.clone();
            clone.savedPath = (ArrayList<Station>) savedPath.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
