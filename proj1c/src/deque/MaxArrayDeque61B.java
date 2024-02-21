package deque;

import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {
    private Comparator<T> com;

    public MaxArrayDeque61B(Comparator<T> c) {
        super();
        com = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        T max = this.get(0);
        for (T item : this) {
            if (com.compare(item, max) > 0) {
                max = item;
            }
        }
        return max;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T max = this.get(0);
        for (T item : this) {
            if (c.compare(item, max) > 0) {
                max = item;
            }
        }
        return max;
    }
}
