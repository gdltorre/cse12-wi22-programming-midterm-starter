/**
 * Name: Gerardo De La Torre
 * ID: A16033883
 * Email: gdelator@ucsd.edu
 * File description: MyLinkedList.java is a reduced version of the
 * MyLinkedList.java that we created during PA3. MyLinkedList.java contains 
 * implementation of a few methods, in particular reverseRegion
 */

/**
 * The class MyLinkedList contains implementation of several methods
 * that are useful in modifying or accessing the node's data of the LinkedList
 */
public class MyLinkedList<E> implements MyReverseList<E>{
    int size;
    Node head;
    Node tail;

    /**
     * A Node class that holds data and references to previous and next Nodes
     * This class is used for both MyLinkedList and MyListIterator.
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /** 
         * Constructor to create singleton Node 
         * @param element Element to add, can be null	
         */
        public Node(E element) {
            //Initialise the elements
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /** 
         * Set the previous node in the list
         * @param p new previous node
         */
        public void setPrev(Node p) {
            //Set the node p on the previous position
            prev = p;
        }

        /** 
         * Set the next node in the list
         * @param n new next node
         */
        public void setNext(Node n) {
            //Set the node n on the next position
            next = n;
        }

        /** 
         * Set the element 
         * @param e new element 
         */
        public void setElement(E e) {
            this.data = e;
        }

        /** 
         * Accessor to get the next Node in the list 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }
        /** 
         * Accessor to get the prev Node in the list
         * @return the previous node  
         */
        public Node getPrev() {
            return this.prev;
        } 
        /** 
         * Accessor to get the Nodes Element 
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        } 
    }

    //IMPORTANT: DO NOT MODIFY THIS CONSTRUCTOR!
    //IMPORTANT: DO NOT ADD ANY MORE CONSTRUCTORS!
    /**
     * Constructor to create a doubly linked list 
     * with the argument array's elements
     * @param arr - array of elements to be used to construct the LinkedList
     */
    public MyLinkedList(E[] arr) {

        //Create dummy nodes
        head = new Node(null);
        tail = new Node(null);
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;	

        if(arr != null){
            //create list by inserting each element
            Node currNode = head;
            for(int i=0; i<arr.length; i++){
                Node newNode = new Node(arr[i]);
                currNode.next.prev = newNode;
                newNode.next = currNode.next;
                newNode.prev = currNode;
                currNode.next = newNode;

                //move pointer to the next node
                currNode = currNode.next;
                //increase size of list
                this.size++;
            }
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
        // Creating a new empty MyLinkedList
        MyLinkedList newLinkedList = new MyLinkedList(null);
        
        // If statement to check if the original MyLinkedList is not empty
        if(this.head.getNext() != null){
            /** currNode is a reference to the current node we are dealing with
             * reference starts with the head */
            Node currNode = this.head;

            /**
             * newCurrNode is a reference to the current node we are working
             * on in newLinkedList. Reference starts with the head.*/
            Node newCurrNode = newLinkedList.head;

            // If the next node of currNode is null, then we have reached the
            // end of the list, otherwise we keep going.
            while(currNode.getNext() != null){ // If the while does not work we can try using .size and for loop
                Node newNode = new Node(currNode.getNext().getElement());

                //currNode.getNext().setPrev(newNode);

                newNode.setPrev(newCurrNode);
                //newNode.setPrev(currNode);
                newNode.setNext(newCurrNode.getNext()); // .getNext().getNext()
                //newNode.setNext(currNode.getNext().getNext()); // fault
                newCurrNode.next.setPrev(newNode);
                newCurrNode.setNext(newNode);
                //currNode.setNext(newNode);

                // move currNode to the next node
                currNode = currNode.getNext();
                newCurrNode = newCurrNode.getNext();

                // increase the size of the list
                newLinkedList.size++;
            }
            int placeHolder = toIndex;
            currNode = this.head;
            /** For loop to move currNode to the node at fromIndex
             *  We do fromIndex + 1 because in theory head is not located on
             *  index 0, but rather the hidden index before that
             */
            for(int i = 0 ; i < fromIndex + 1; i++){
                currNode = currNode.getNext();
            }
            /**  For loop to reverse the elements of the LinkedList*/
            for(int i = fromIndex; i <= toIndex; i++){
                E elementHolder = (E) newLinkedList.get(placeHolder);
                currNode.setElement(elementHolder);
                placeHolder--;
                currNode = currNode.getNext();
            }
        }
    }

    @Override
    /** 
     * Returns the number of elements stored
     * @return - number of elements in the linkedlist
    */
    public int size() {
        //Return the number of nodes in the linkedlist
        return this.size;
    }

    @Override
    /** 
     * Get contents at position i
     * @param index - The index position to obtain the data
     * @return the Element at the specified index
     */
    public E get(int index)	{

        Node currNode = this.getNth(index);

        //Get the value of data at the position
        E element = currNode.getElement();

        return element;	
    }


    /** A method that returns the node at a specified index,
     *  not the content
     *  @param index - position to return the node
     * @return - Node at 'index'
     */
    // Helper method to get the Node at the Nth index
    protected Node getNth(int index) {
        if (index >= this.size || index < 0)
            throw new IndexOutOfBoundsException();

        Node currNode = this.head;

        //Loop through the linked list and stop at the position
        for (int i = 0; i <= index; i++) {
            currNode = currNode.getNext();
        }

        //return the node	
        return currNode; 
    }

}
