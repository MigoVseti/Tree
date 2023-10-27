import java.util.*;

class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class BinarySearchTree {
    Node root;

    public BinarySearchTree() {
        root = null;
    }

    public Node insert(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }

        if (data < node.data) {
            node.left = insert(node.left, data);
        } else if (data > node.data) {
            node.right = insert(node.right, data);
        }

        return node;
    }

    public void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.data + " ");
            inOrderTraversal(node.right);
        }
    }

    public void visualize() {
        _visualize_helper(root, "", true);
    }

    private void _visualize_helper(Node node, String prefix, boolean isLeft) {
        if (node != null) {
            String nodeStr = Integer.toString(node.data);
            String line = prefix + (isLeft ? "├── " : "└── ");
            System.out.println(line + nodeStr);
            String childPrefix = prefix + (isLeft ? "│   " : "    ");
            _visualize_helper(node.left, childPrefix, true);
            _visualize_helper(node.right, childPrefix, false);
        }
    }
}

class AVLNode {
    int key;
    int height;
    AVLNode left;
    AVLNode right;

    public AVLNode(int key) {
        this.key = key;
        height = 1;
        left = null;
        right = null;
    }
}

class AVLTree {
    AVLNode root;

    public AVLTree() {
        root = null;
    }

    private int getHeight(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalance(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    private AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    public AVLNode insert(AVLNode node, int key) {
        if (node == null) {
            return new AVLNode(key);
        }
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            return node;
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        int balance = getBalance(node);

        if (balance > 1 && key < node.left.key) {
            return rotateRight(node);
        }

        if (balance < -1 && key > node.right.key) {
            return rotateLeft(node);
        }

        if (balance > 1 && key > node.left.key) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && key < node.right.key) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    public void inOrderTraversal(AVLNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.key + " ");
            inOrderTraversal(node.right);
        }
    }

    public void visualize() {
        _visualize_helper(root, "", true);
    }

    private void _visualize_helper(AVLNode node, String prefix, boolean isLeft) {
        if (node != null) {
            String nodeStr = Integer.toString(node.key);
            String line = prefix + (isLeft ? "├── " : "└── ");
            System.out.println(line + nodeStr);
            String childPrefix = prefix + (isLeft ? "│   " : "    ");
            _visualize_helper(node.left, childPrefix, true);
            _visualize_helper(node.right, childPrefix, false);
        }
    }
}


class BinaryHeap {
    List<Integer> heap;

    public BinaryHeap() {
        heap = new ArrayList<>();
    }

    public void buildHeap(List<Integer> list) {
        heap = list;
        for (int i = (heap.size() - 1) / 2; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    public void heapSort() {
        List<Integer> sortedList = new ArrayList<>();
        while (!heap.isEmpty()) {
            sortedList.add(heap.remove(0));
            heapifyDown(0);
        }
        heap = sortedList;
    }

    private void heapifyDown(int index) {
        int leftChildIndex = index * 2 + 1;
        int rightChildIndex = index * 2 + 2;
        int largest = index;

        if (leftChildIndex < heap.size() && heap.get(leftChildIndex) > heap.get(largest)) {
            largest = leftChildIndex;
        }

        if (rightChildIndex < heap.size() && heap.get(rightChildIndex) > heap.get(largest)) {
            largest = rightChildIndex;
        }

        if (largest != index) {
            swap(index, largest);
            heapifyDown(largest);
        }
    }

    private void swap(int index1, int index2) {
        int temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    public void visualize() {
        _visualize_helper(0, "", true);
    }

    private void _visualize_helper(int index, String prefix, boolean isLeft) {
        if (index < heap.size()) {
            String nodeStr = Integer.toString(heap.get(index));
            String line = prefix + (isLeft ? "├── " : "└── ");
            System.out.println(line + nodeStr);
            String childPrefix = prefix + (isLeft ? "│   " : "    ");
            _visualize_helper((index * 2) + 1, childPrefix, true);
            _visualize_helper((index * 2) + 2, childPrefix, false);
        }
    }

    public void printList() {
        System.out.println(heap);
    }
}

public class Main {
    public static List<Integer> generateRandomList(int length) {
        List<Integer> list = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            int num = rand.nextInt(101);
            if (!list.contains(num)) {
                list.add(num);
            } else {
                i--;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<Integer> randomList = generateRandomList(30);
        System.out.println("Generated List: " + randomList);

        BinarySearchTree bst = new BinarySearchTree();
        for (int num : randomList) {
            bst.root = bst.insert(bst.root, num);
        }

        System.out.print("Binary Search Tree: ");
        bst.inOrderTraversal(bst.root);
        System.out.println();

        System.out.println("Binary Search Tree Visualization:");
        bst.visualize();
        System.out.println();

        AVLTree avlTree = new AVLTree();
        for (int num : randomList) {
            avlTree.root = avlTree.insert(avlTree.root, num);
        }

        System.out.print("AVL Tree: ");
        avlTree.inOrderTraversal(avlTree.root);
        System.out.println();

        System.out.println("AVL Tree Visualization:");
        avlTree.visualize();
        System.out.println();

        BinaryHeap binaryHeap = new BinaryHeap();
        binaryHeap.buildHeap(randomList);

        System.out.print("Binary Heap Tree: ");
        binaryHeap.printList();
        System.out.println();

        System.out.println("Binary Heap Tree Visualization:");
        binaryHeap.visualize();
        System.out.println();

        binaryHeap.heapSort();

        System.out.println("Sorted List (Heap Sort): " + binaryHeap.heap);
    }
}



