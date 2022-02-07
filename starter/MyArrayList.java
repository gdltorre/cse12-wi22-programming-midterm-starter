/**
 * Name: Gerardo De La Torre
 * ID: A16033883
 * Email: gdelator@ucsd.edu
 * File description: MyArrayList.java is a reduced version of the
 * MyArrayList.java that we created during PA2. MyArrayList.java contains 
 * implementation of a few methods, in particular reverseRegion
 */

/**
 * MyArrayList contains several methods that are useful in modifying or
 * displaying information about the ArrayList. 
 */
public class MyArrayList<E> implements MyReverseList<E> {
    static final int DEFAULT_CAPACITY = 5;
    
    Object[] data;
    int size;

    //IMPORTANT: DO NOT MODIFY THIS CONSTRUCTOR!
    //IMPORTANT: DO NOT ADD ANY MORE CONSTRUCTORS!

    /**
     * Constructor to create an array list with the given array's elements
     * @param arr - array of elements to be used to construct the ArrayList
     */
    public MyArrayList(E[] arr) {
        if (arr == null) {
            this.data = new Object[DEFAULT_CAPACITY];
        } else {
            this.data = arr.clone();
            this.size = arr.length;
        }
    }

    /**
	 * Method reverseRegion reverses the elements in the list between
     * fromIndex and toIndex
	 */
    public void reverseRegion(int fromIndex, int toIndex){
        /** If fromIndex is greater than toIndex, we should 
         * leave the list unchanged */
        if(fromIndex >= toIndex){
            return;
        }
        /** If either fromIndex or toIndex is out of bounds, then we
         * should throw an IndexOutOfBoundsException 
         * and leave the list unchanged */
        if(fromIndex < 0 || toIndex < 0 || fromIndex > this.size() || 
        toIndex > this.size()){
            throw new IndexOutOfBoundsException();
        }
        /** We create a new MyArrayList to have a reference to 
         * how did the original MyArrayList looked */
        MyArrayList newArrayList = new MyArrayList(this.data);

        /** placeHolder is an int utilized as the index going 
         * from the end of the MyArrayList to fromIndex
         */
        int placeHolder  = toIndex;
        for(int i = fromIndex; i <= toIndex; i++){
            this.data[i] = newArrayList.get(placeHolder);
            placeHolder--;
        }

    }

    @Override
    /**
     * A method that returns the number of valid elements
     * in the ArrayList 
     * @return - number of valid elements in the arraylist
     */
    public int size() {
        return this.size;
    }

    @Override
    /**
     * A method that returns an Element at the specified index
     * @param index - the index of the return Element
     * @return Element at specified index
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) data[index];
    }
}
