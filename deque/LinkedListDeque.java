package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private class Node {
        private T item;
        private Node prev;
        private Node next;

        Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private class DLLIter implements Iterator {
        private int pos;

        DLLIter() {
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
        return new DLLIter();
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

    }

    @Override
    public void printDeque() {
        Node c = sentinel.next;
        while (c != sentinel) {
            System.out.print(c.item + " ");
            c = c.next;
        }
        System.out.println("");
    }

    @Override
    public void addFirst(T x) { //constant time\
        sentinel.next = new Node(x, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    @Override
    public void addLast(T x) {
        sentinel.prev = new Node(x, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    @Override
    public T removeFirst() { //constant time
        if (size > 0) {
            Node newFirst = sentinel.next.next;
            Node oldFirst = sentinel.next;
            sentinel.next = newFirst;
            newFirst.prev = sentinel;
            oldFirst.prev = null;
            oldFirst.next = null;
            size--;
            return oldFirst.item;
        }
        return null;
    }

    @Override
    public T removeLast() {
        if (size > 0) {
            Node newLast = sentinel.prev.prev;
            Node oldLast = sentinel.prev;
            sentinel.prev = newLast;
            newLast.next = sentinel;
            oldLast.prev = null;
            oldLast.next = null;
            size--;
            return oldLast.item;
        }
        return null;
    }

    @Override
    public T get(int n) { //linear time iteration not recursion
        Node c = sentinel.next;
        for (int i = 0; i < n; i++) {
            c = c.next;
        }
        return c.item;
    }

    public T getRecursive(int n) { //recursive get
        Node c = sentinel.next;
        return recursiveHelper(c, n);
    }

    private T recursiveHelper(Node p, int n) { //helper function for getRecursive()
        if (n == 0) {
            return p.item;
        } else {
            return recursiveHelper(p.next, n - 1);
        }
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

    @Override
    public int size() { //constant time
        return size;
    }
}
