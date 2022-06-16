public class Edge {
    Station begin, destination;

    long weight;

    public Edge(Station begin, Station destination, Long weight) {
        this.begin = begin;
        this.destination = destination;
        this.weight = weight;
    }
}