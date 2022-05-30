public class HashAVLTable<Key extends Comparable<Key>, Value extends Comparable<Value>> {

    static int tableSize = 100;

    private AVLList[] table;
    int numItems = 0;

    public HashAVLTable() {
        table = new AVLList[HashAVLTable.tableSize];
    }

    public void put(Key key, Value value) {
        int index = key.hashCode();
        if(table[index] == null) {
            table[index] = new AVLList();
        }
        table[index].insert(value);
    }
}
