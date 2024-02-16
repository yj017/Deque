import java.util.ArrayList;
import java.util.List;


public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] array;
    private int size;
    private int nextFirstIndex;
    private int nextLastIndex;


    public ArrayDeque61B() {
        array = (T[]) new Object[8];
        size = 0;
        nextFirstIndex = 4;
        nextLastIndex = 5;

    }

    private void resizeUp() {
        int biggerSize = array.length * 2;
        T[] newArray = (T[]) new Object[biggerSize];
        nextFirstIndex++;
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[Math.floorMod(nextFirstIndex, array.length)];
            nextFirstIndex++;
        }
        nextFirstIndex = newArray.length - 1;
        nextLastIndex = size;
        array = newArray;
    }

    @Override
    public void addFirst(T x) {
        if (size == array.length) {
            resizeUp();
        }
        array[Math.floorMod(nextFirstIndex, array.length)] = x;
        size++;
        nextFirstIndex--;
    }

    @Override
    public void addLast(T x) {
        if (size == array.length) {
            resizeUp();
        }
        array[Math.floorMod(nextLastIndex, array.length)] = x;
        size++;
        nextLastIndex++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        int index = 0;
        while (this.get(index) != null) {
            returnList.add(this.get(index));
            index++;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size * 1.0 <= array.length * 0.25 && array.length > 8) {
            resizeDown();
        }
        T temp = array[Math.floorMod(nextFirstIndex + 1, array.length)];
        array[Math.floorMod(nextFirstIndex + 1, array.length)] = null;
        if (!this.isEmpty()) {
            size--;
            nextFirstIndex++;
        }
        return temp;
    }

    public void resizeDown() {
        int smallerSize = array.length / 2;
        T[] newArray = (T[]) new Object[smallerSize];
        nextFirstIndex++;
        for (int i = 0; i < smallerSize; i++) {
            newArray[i] = array[Math.floorMod(nextFirstIndex, array.length)];
            nextFirstIndex++;
        }
        nextFirstIndex = newArray.length - 1;
        nextLastIndex = size;
        array = newArray;
    }

    @Override
    public T removeLast() {
        if (size * 1.0 <= array.length * 0.25 && array.length > 8) {
            resizeDown();
        }
        T temp = array[Math.floorMod(nextLastIndex - 1, array.length)];
        array[Math.floorMod(nextLastIndex - 1, array.length)] = null;
        if (!this.isEmpty()) {
            size--;
            nextLastIndex--;
        }
        return temp;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= array.length) {
            return null;
        } else {
            int theCorrectIndex = Math.floorMod(nextFirstIndex + 1 + index, array.length);
            return array[theCorrectIndex];
        }
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
