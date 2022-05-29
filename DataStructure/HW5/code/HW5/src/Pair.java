public class Pair<T extends Comparable<T>, S extends Comparable<S>> implements Comparable<Pair<T,S>> {
    public T column;
    public S row;

    public Pair(T column, S row) {
        this.column = column;
        this.row = row;
    }

    @Override
    public int compareTo(Pair<T, S> pair) {
        if(this.column.compareTo(pair.column) == 0) {
            return this.row.compareTo(pair.row);
        } else return this.column.compareTo(pair.column);
    }
}