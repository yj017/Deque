package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    @Override
    public Iterator<T> iterator() {
        return new LldIterator();
    }

    private class LldIterator implements Iterator<T> {
        private int index;

        public LldIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            if (index < size()) {
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            T temp = get(index);
            index++;
            return temp;
        }
    }

    public class Node {
        private T item;
        private Node prev;
        private Node next;

        public Node(T item) {
            this.item = item;
            prev = null;
            next = null;
        }
    }

    private Node sentinel;
    private int size;


    public LinkedListDeque61B() {
        sentinel = new Node(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deque61B list) {
            if (list.size() != this.size()) {
                return false;
            }
            for (int i = 0; i < this.size(); i++) {
                if (!list.get(i).equals(this.get(i))) {
                    return false;
                }
            }

            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.toList().toString();
    }


    @Override
    public void addFirst(T x) {
        Node temp = new Node(x);
        if (sentinel.next == sentinel) {
            sentinel.next = temp;
            temp.prev = sentinel;
            temp.next = sentinel;
            sentinel.prev = temp;
        } else {
            temp.next = sentinel.next;
            sentinel.next.prev = temp;
            sentinel.next = temp;
            temp.prev = sentinel;
        }
        size++;
    }

    @Override
    public void addLast(T x) {
        Node temp = new Node(x);
        if (sentinel.next == sentinel) {
            sentinel.next = temp;
            temp.prev = sentinel;
            temp.next = sentinel;
            sentinel.prev = temp;
        } else {
            temp.prev = sentinel.prev;
            sentinel.prev.next = temp;
            temp.next = sentinel;
            sentinel.prev = temp;
        }
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node flag = sentinel.next;
        while (flag != sentinel) {
            returnList.add(flag.item);
            flag = flag.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return item;
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        T item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return item;
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            return null;
        } else if (index > size - 1) {
            return null;
        } else {
            Node flag = sentinel.next;
            while (index != 0) {
                flag = flag.next;
                index--;
            }
            return flag.item;
        }
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0) {
            return null;
        } else if (index > size - 1) {
            return null;
        } else {
            Node flag = sentinel.next;
            return helper(index, flag);
        }

    }

    private T helper(int index, Node flag) {
        if (index == 0) {
            return flag.item;
        } else {
            index--;
            return helper(index, flag.next);
        }
    }
}
