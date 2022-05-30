import java.util.Collections;
import java.util.LinkedList;

// 교재 코드 참고했습니다.
public class AVLNode<Key extends Comparable<Key>, Item extends Comparable> {
    public LinkedList<Item> data = new LinkedList<>();
    public Comparable key;
    public AVLNode left, right;
    public int height;

    public AVLNode(Key key, Item item) {
        this.key = key;
        this.data.add(item);
        this.left = this.right = AVLList.NIL;
        this.height = 1;
    }

    public AVLNode(Key key, Item item, AVLNode left, AVLNode right) {
        this.key = key;
        this.data.add(item);
        this.left = left;
        this.right = right;
        this.height = 1;
    }

    public AVLNode(Key key, Item item, AVLNode left, AVLNode right, int height) {
        this.key = key;
        this.data.add(item);
        this.left = left;
        this.right = right;
        this.height = height;
    }

    public Comparable getFirstItem() {
        if(data.isEmpty()) return null;
        else return data.getFirst();
    }

    public void put(Item item) {
        data.add(item);
        Collections.sort(data);
    }

    public void printKey() {
        if(this.key!=null) {
            System.out.print(this.key);
        }
        if(this.left != null) {
            System.out.print(" ");
            this.left.printKey();
        }
        if(this.right != null) {
            System.out.print(" ");
            this.right.printKey();
        }
    }

    public Key getKey() {
        return (Key) this.key;
    }
}
