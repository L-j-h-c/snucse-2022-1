public class Pair<T extends Comparable<T>, S extends Comparable<S>> implements Comparable<Pair<T,S>> {
    public T row;
    public S column;

    public Pair(T row, S column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public int compareTo(Pair<T, S> pair) {
        if(this.row.compareTo(pair.row) == 0) {
            return this.column.compareTo(pair.column);
        } else return this.row.compareTo(pair.row);
    }

    @Override
    public String toString() {
        return "(" + this.row +", "+ this.column + ")";
    }
}