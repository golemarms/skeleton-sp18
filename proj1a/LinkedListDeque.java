public class LinkedListDeque<Type> {
    public class Node {
        public Type item;
        public Node prev;
        public Node next;

        public Node(Node prev, Type item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    public int size;
    public Node sentinel;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public int size() {
        return size;
    }

    public void addFirst(Type item) {
        Node oldFirstNode = sentinel.next;
        Node newFirstNode = new Node(sentinel, item, oldFirstNode);
        oldFirstNode.prev = newFirstNode;
        sentinel.next = newFirstNode;

        if (size == 0) {
            sentinel.prev = newFirstNode;
        }

        size++;
    }

    public void addLast(Type item) {
        Node oldLastNode = sentinel.prev;
        Node newLastNode = new Node(oldLastNode, item, sentinel);
        sentinel.prev = newLastNode;
        oldLastNode.next = newLastNode;

        if (size == 0) {
            sentinel.next = newLastNode;
        }

        size++;
    }

    public Type removeFirst() {
        Node oldFirstNode = sentinel.next;
        Node newFirstNode = sentinel.next.next;
        sentinel.next = newFirstNode;
        if (size > 0) size--;
        return oldFirstNode.item;
    }

    public Type removeLast() {
        Node oldLastNode = sentinel.prev;
        Node newLastNode = sentinel.prev.prev;
        sentinel.prev = newLastNode;
        if (size > 0) size--;
        return oldLastNode.item;
    }

    private Type getFirst() {
        return sentinel.next.item;
    }

    private Type getLast() {
        return sentinel.prev.item;
    }

    public Type get(int position) {

        if (position == 0) {
            return getFirst();
        }

        Node currentNode = sentinel.next.next;
        while (position > 1 && currentNode.next != sentinel) {
            position -= 1;
            currentNode = currentNode.next;
        }

        return currentNode.item;

    }

    public void printDeque() {
        Node current = sentinel.next;
        for (int i = 0; i < size() && current != sentinel; i++) {
            System.out.print(current.item + " ");
            current = current.next;
        }
    }
}
