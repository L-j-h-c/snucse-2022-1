import java.util.*;

public class Station {
    String id;
    String name;
    String lineNum;
//    String idWithLine;

    boolean isTransferStation = false;

    List<Edge> edges = new ArrayList<>();

    public Station(String id, String name, String lineNum) {
        this.id = id;
        this.name = name;
        this.lineNum = lineNum;
//        this.idWithLine = idWithLine;
    }

    @Override
    public String toString() {
        return name + " " + lineNum;
    }
}
