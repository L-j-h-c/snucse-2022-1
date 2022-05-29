import java.util.Collections;
import java.util.LinkedList;

// 교재 코드 참고했습니다.
public class AVLNode<T extends Comparable> {
    public LinkedList<T> data = new LinkedList<>();
    public AVLNode left, right;
    public int height;

    public AVLNode(T item) {
        this.data.add(item);
        this.left = this.right = AVLList.NIL;
        this.height = 1;
    }

    public AVLNode(T item, AVLNode left, AVLNode right) {
        this.data.add(item);
        this.left = left;
        this.right = right;
        this.height = 1;
    }

    public AVLNode(T item, AVLNode left, AVLNode right, int height) {
        this.data.add(item);
        this.left = left;
        this.right = right;
        this.height = height;
    }

    public Comparable getFirstItem() {
        if(data.isEmpty()) return null;
        else return data.getFirst();
    }

    public void put(T item) {
        data.add(item);
        Collections.sort(data);
    }
}
