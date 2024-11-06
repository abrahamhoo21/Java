package Adt;

/**
 *
 * @author Abraham Hoo Weng Lek
 */

public interface ListInterface<T> {

    public boolean add(T newEntry);

    public boolean add(int newPosition, T newEntry);

    public T remove(int index);

    public boolean remove(T element);

    public T removeFirst();

    public T removeLast();

    public void clear();

    public boolean isEmpty();

    public boolean isExist(T element);

    public T search(int index);

    public int search(T element);

    public int totalSize();

    public boolean isFirst(T element);

    public boolean isLast(T element);

    public boolean isFull();
}
