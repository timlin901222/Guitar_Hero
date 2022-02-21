package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] items;
    private int size;
    private int start;

    private class ADIter implements Iterator {
        private int pos;
        ADIter() {
            pos = 0;
        }
        public boolean hasNext() {
            return pos < size();
        }

        public T next() {
            T ret = get(pos);
            pos++;
            return ret;
        }

    }
    public Iterator<T> iterator() {
        return new ADIter();
    }

    public ArrayDeque() { //starting size is 8
        items = (T[]) new Object[8];
        size = 0;
        start = 4;
    }


    @Override
    public void printDeque() {
        for (int i = start; i < start + size; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println("");

    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < size(); i++) {
            a[a.length / 2 + i] = get(i);
        }
        items = a;
        start = a.length / 2;
    }
    // resizes the array to target capacity

    @Override
    public void addLast(T x) { //constant time

        if (size == items.length) {
            resize(size * 2);
        }

        items[getPos(size)] = x;
        size = size + 1;
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        if (start == 0) {
            start = items.length - 1;
        } else {
            start--;
        }

        items[start] = x;
        size++;
    }

    @Override
    public T removeLast() { //constant time

        if (size > 0) {
            T tmp = items[getPos(size - 1)];
            size--;

            if (items.length > 16 && size < (0.25 * items.length)) {

                resize(items.length / 2);
            }


            return tmp;
        }
        return null;
    }

    @Override
    public T removeFirst() {
        if (size > 0) {
            T tmp = items[start];
            start = getPos(1);
            size--;

            if (items.length > 16 && size < (0.25 * items.length)) {
                resize(items.length / 2);
            }
            return tmp;
        }
        return null;
    }

    @Override
    public T get(int i) {
        return items[getPos(i)];
    }

    @Override
    public int size() {
        return size;
    }

    private int getPos(int i) { //return the pos index
        if (start + i >= items.length) {
            return (start + i) % items.length;
        } else if (start + i < 0) {
            return items.length - (start + i);
        }
        return start + i;
    }


    public boolean equals(Object o) {
        if (o instanceof Deque<?>) {
            Deque other = (Deque) o;
            if (size() != other.size()) {
                return false;
            }
            for (int i = 0; i < size(); i++) {
                if (!get(i).equals(other.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }








}

