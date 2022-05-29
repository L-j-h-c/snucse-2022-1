// 교재 코드 참고했습니다.
public class AVLList {
    private AVLNode root;
    static final AVLNode NIL = new AVLNode(null, null, null, 0);

    public AVLList() {
        this.root = this.NIL;
    }

    public AVLNode search(Comparable target) {
        return searchItem(this.root, target);
    }
    private AVLNode searchItem(AVLNode current, Comparable target) {
        if (current == NIL) return NIL;
        else if (target.compareTo(current.getFirstItem()) == 0) return current;
        else if (target.compareTo(current.getFirstItem()) < 0) {
            return searchItem(current.left, target);
        } else return searchItem(current.left, target);
    }

    public void insert(Comparable target) {
        this.root = insertItem(this.root, target);
    }

    private AVLNode insertItem(AVLNode current, Comparable target) {
        if(current == NIL) {
            current = new AVLNode(target);
        } else if(target.compareTo(current) < 0) {
            current.left = insertItem(current.left, target);
            current.height = 1 + Math.max(current.left.height, current.right.height);
            BalanceType type = makeBalanceType(current);
            if(type != BalanceType.Balanced) current = makeBalance(current, type);
        } else {
            current.right = insertItem(current.right, target);
            current.height = 1 + Math.max(current.left.height, current.right.height);
            BalanceType type = makeBalanceType(current);
            if(type != BalanceType.Balanced) current = makeBalance(current, type);
        }
        return current;
    }

    private AVLNode makeBalance(AVLNode current, BalanceType type) {
        AVLNode finalNode = this.NIL;
        switch (type) {
            case LL:
                finalNode = rightRotate(current);
                break;
            case LR:
                current.left = leftRotate(current.left);
                finalNode = rightRotate(current);
                break;
            case RR:
                finalNode = leftRotate(current);
                break;
            case RL:
                current.right = rightRotate(current.right);
                finalNode = leftRotate(current);
                break;
        }
        return finalNode;
    }

    private AVLNode leftRotate(AVLNode root) {
        AVLNode rightChild = root.right;
        if(rightChild == NIL) {
            System.out.println("Cannot Be NIL ERROR");
        }
        AVLNode rightLeftChild = rightChild.left;
        rightChild.left = root;
        root.right = rightLeftChild;
        root.height = 1 + Math.max(root.left.height, root.right.height);
        rightChild.height = 1 + Math.max(rightChild.left.height, rightChild.right.height);
        return rightChild;
    }

    private AVLNode rightRotate(AVLNode root) {
        AVLNode leftChild = root.left;
        if(leftChild == NIL) {
            System.out.println("Cannot Be NIL ERROR");
        }
        AVLNode leftRightChild = leftChild.right;
        leftChild.right = root;
        root.left = leftRightChild;
        root.height = 1 + Math.max(root.left.height, root.right.height);
        leftChild.height = 1 + Math.max(leftChild.left.height, leftChild.right.height);
        return leftChild;
    }

    enum BalanceType {
        LL,
        LR,
        RR,
        RL,
        Balanced
    }
    private BalanceType makeBalanceType(AVLNode target) {
        if (target.left.height + 2 <= target.right.height) {
            if(target.right.left.height <= target.right.right.height)
                return BalanceType.RR;
            else return BalanceType.LL;
        } else if(target.left.height >= target.right.height + 2) {
            if(target.left.left.height >= target.left.right.height)
                return BalanceType.LL;
            else return BalanceType.LR;
        } else {
            return BalanceType.Balanced;
        }
    }

}
