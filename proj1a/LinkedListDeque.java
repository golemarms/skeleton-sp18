package deque;

import java.util.Iterator;

public class LinkedListDeque<Type> implements Deque<Type> {
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

    @Override
    public Iterator<Type> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<Type> {
        private Node iterNode;

        public LinkedListDequeIterator() {
            iterNode = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return iterNode != sentinel;
        }

        @Override
        public Type next() {
            Type returnItem = iterNode.item;
            iterNode = iterNode.next;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }

        LinkedListDeque<Type> o = (LinkedListDeque<Type>) other;

        if (o.size() != this.size()) {
            return false;
        }

        Iterator<Type> thisIter = iterator();
        Iterator<Type> otherIter = o.iterator();

        while (thisIter.hasNext() && otherIter.hasNext()) {
            Type thisValue = thisIter.next();
            Type otherValue = otherIter.next();
            if ( !thisValue.equals(otherValue) ) {
                return false;
            }
        }

        return true;
    }
}
