package Adt;

/**
 *
 * @author Abraham Hoo Weng Lek
 */

public class ArrayListADT<T> implements ListInterface<T> {

    private T[] array; 
    private int numberOfElements; 
    private static final int DEFAULT_SIZE = 10;
    private static final int NOT_FOUND = 0;

    public ArrayListADT() {
        this(DEFAULT_SIZE); //set default size 10
    }

    public ArrayListADT(int initialSize) {
        numberOfElements = 0; 
        array = (T[]) new Object[initialSize]; //creates a generic type array with size of 10 as initial capacity
    }

    @Override
    public boolean add(T newEntry) {
        if (isFull()) {
            extendArray();
        }
        array[numberOfElements] = newEntry; // Add newEntry to the array at index numberOfEntries which is the end of the array
        numberOfElements++; //after adding the newEntry, increment array size
        
        return true;
    }
    
    private void extendArray(){
        T[] oldArray = array; //elements that stored in the array variable is assigned to the new variable oldArray
                              //doesn't create a new array or copy the elements
        array = (T[]) new Object[oldArray.length * 2]; //Create a new generic array with double the capacity
        for (int i = 0; i < numberOfElements; i++) {
            array[i] = oldArray[i]; //Copy elements from the old array to the new array
        }
        
    }

    @Override
    public boolean add(int newPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((newPosition >= 1) && (newPosition <= numberOfElements + 1)) {
            makeRoom(newPosition); //Shift the elements to makeRoom at newPosition
            array[newPosition - 1] = newEntry; //Add newEntry at the newPosition
            numberOfElements++;
        } else {
            isSuccessful = false;
        }
        return isSuccessful;
    }
    
    private void makeRoom(int newPosition) { //Create a space
        int newIndex = newPosition - 1; //Convert position to index because postion start from 1
        int lastIndex = numberOfElements - 1; //Index of the last element
        
        // move each entry to next higher index,starting at lastIndex 
        // continuing until the entry at newIndex is moved
        for (int i = lastIndex; i >= newIndex; i--) { //the loop iterates in reverse order from lastIndex down to newIndex
            array[i + 1] = array[i]; //Shift element to the right(higher index)by one position
            //replace the element at array[i] to the array[i + 1] 
        }
    }

    @Override
    public T remove(int givenPosition) {
        T result = null; //to store the element being removed
        
        if ((givenPosition >= 1) && (givenPosition <= numberOfElements)) {
            result = array[givenPosition - 1]; //Store element to be removed,position converted to index by subtrading 1
            
            if (givenPosition < numberOfElements) {
                removeSpace(givenPosition);
            }
            numberOfElements--; //After element removed, decrement the array size
        }
        return result; //return the removed element
    }
    
    private void removeSpace(int givenPosition) {
        // move each entry to next lower position starting at entry after the
        // one removed and continuing until end of array
        int removedIndex = givenPosition - 1;
        int lastIndex = numberOfElements - 1;

        for (int i = removedIndex; i < lastIndex; i++) { //start from index of the removed element up to the second and to last element.
            array[i] = array[i + 1]; //replace the element from array[i + 1] to array[i]
                                    //shift element to the left
        }
    }

    // Remove an element at the position index by giving the element the list.
    @Override
    public boolean remove(T element) {
        
        if (isExist(element)) {
            removeSpace(search(element)); //remove an element from a specific position in the list 
            numberOfElements--;
            return true;
        }
        return false;
    }

    @Override
    public T removeFirst() {
        return remove(0);
    }

    @Override
    public T removeLast() {
        return (remove(numberOfElements - 1));
    }

    @Override
    public boolean isExist(T element) {
        boolean found = false;
        if(search(element) != -1){ //the element is not equal to not found
            found = true;
        }
        return found;
    }

    @Override
    public T search(int givenIndex) {
        
        if (givenIndex >= 0 && givenIndex <= numberOfElements){
            return array[givenIndex];
        }
        return null;
            
    }

    @Override
    public int search(T element) {

        if (!isEmpty()) {
            for (int i = 0; i < numberOfElements; i++) {
                if(element.equals(array[i]))
                    return i; //found element and return index
            }
        }
        return -1; //not found
    }
    
    @Override
    public boolean isFirst(T element) {
        if(search(element) == NOT_FOUND) {
            return false;
        }
        return search(element) == 0;
    }

    @Override
    public boolean isLast(T element) {
        if(search(element) == NOT_FOUND) {
            return false;
        }
        return search(element) == (numberOfElements - 1);
    }
    
    @Override
    public boolean isEmpty() {
        return numberOfElements == 0;
    }
    
    @Override
    public boolean isFull() {
        return numberOfElements == array.length;
    }

    @Override
    public int totalSize() {
        return numberOfElements;
    }
    @Override
    public void clear() {
        numberOfElements = 0;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < numberOfElements; i++) {
            str += array[i] + "\n";
        }
        return str;
    }
}
