package deque;

import java.util.Iterator;

public class ArrayDeque<Type> implements Deque<Type>{
    private Type[] items;
    private int size;
    private int SCALE_FACTOR = 4;
    private int USAGE_FACTOR = 4;
    private int INITIAL_SIZE = 8;
    private int front;

    public ArrayDeque() {
        size = 0;
        items = (Type[]) new Object[INITIAL_SIZE];
        front = 0;
    }

    public int size() {
        return size;
    }

    private Type getLast() {
        return get(size-1);
    }

    /** Inserts X into the back of the list. */
    public void addLast(Type x) {
        if (size == items.length) {
            resize(size * SCALE_FACTOR, 0, 0);
        }

        set(size, x);
        size = size + 1;
    }

    /** Inserts X into the front of the list. */
    public void addFirst(Type x) {
        if (size == items.length) {
            resize(size * SCALE_FACTOR, 1, 0);
        }

        else {
            moveFront(-1);
        }

        set(0, x);
        size = size + 1;
    }

    public void moveFront(int by) {
        front = Math.floorMod((front + by) , items.length);
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public Type removeLast() {
        if (size == 0) {
            return null;
        }

        if ((size < items.length / USAGE_FACTOR) && (size > USAGE_FACTOR) && (size > INITIAL_SIZE)) {
            resize(items.length / USAGE_FACTOR, 0, 0);
        }
        Type x = getLast();
        set(size - 1, null);
        size = size - 1;
        return x;
    }

    /** Deletes item from front of the list and
     * returns deleted item. */
    public Type removeFirst() {
        if (size == 0) {
            return null;
        }

        Type x = getFirst();

        if ((size < items.length / USAGE_FACTOR) && (size > USAGE_FACTOR) && (size > INITIAL_SIZE)) {
            resize(items.length / USAGE_FACTOR, 0, 1);
        }

        else {
            moveFront(1);
        }

        size = size - 1;
        return x;
    }

    private Type getFirst() {
        return get(0);
    }

    /** Resizes the underlying array to the target capacity. (with offset) */
    private void resize(int capacity, int a_offset, int items_offset) {
        Type[] a = (Type[]) new Object[capacity];
        for (int i = 0; i < Math.min(capacity - a_offset, size - items_offset); i += 1) {
            a[i + a_offset] = get(i + items_offset);
        }
        items = a;
        front = 0;
    }

    public Type get(int position) {
        return items[Math.floorMod((front + position), items.length)];
    }

    public void set(int position, Type value) {
        items[Math.floorMod((front + position), items.length)] = value;
    }

    public void printDeque() {
        for (int i = 0; i < size(); i = i + 1) {
            System.out.print(get(i) + " ");
        }
    }


    @Override
    public Iterator<Type> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<Type> {
        private int iterPos;

        public ArrayDequeIterator() {
            iterPos = 0;
        }

        @Override
        public boolean hasNext() {
            return iterPos < size;
        }

        @Override
        public Type next() {
            Type returnItem = get(iterPos);
            iterPos++;
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

        ArrayDeque<Type> o = (ArrayDeque<Type>) other;

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
