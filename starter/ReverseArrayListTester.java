/**
 * Tests to check the implementation of reverseRegion method in MyArrayList.java
*/

//other import statements

import static org.junit.Assert.*;
import org.junit.*;

//IMPORTANT: DO NOT MODIFY THE TEST HEADERS!
/**
 * This class contains various test cases to test the reverseRegion method
 */
public class ReverseArrayListTester {
    private MyArrayList baseList, baseListUnchanged, emptyList;

    // Created an array that contains the data that will be 
    // passed to the ArrayList used for testing
    private Integer[] dataForList = {1, 2, 3, 4, 5};

    /**
     * Run before every test
     */
    @Before
    public void setUp(){
        // Setting up the ArrayList that will be used for testing
        baseList = new MyArrayList(dataForList);
        baseListUnchanged = new MyArrayList(dataForList);
        emptyList = new MyArrayList(null);
    }


    /**
     * Tests reverseRegion method when either fromIndex or toIndex
     * or both are out of bounds.
     */
    @Test
    public void testReverseIndexOutOfBounds(){
        boolean EXCEPTION_THROWN = false;
        try{
            baseList.reverseRegion(0, 6);
        }
        catch(IndexOutOfBoundsException e){
            EXCEPTION_THROWN = true;
        }
        assertTrue("An IndexOutOfBounds exception should be " +
                    "thrown", EXCEPTION_THROWN);
    }

    /**
     * Tests reverseRegion method when
     * fromIndex > toIndex
     */
    @Test
    public void testReverseFromIndexGreater(){
        baseList.reverseRegion(3, 2);
        assertArrayEquals(baseList.data, baseListUnchanged.data);
    }

    /**
     * Tests reverseRegion method when
     * fromIndex and toIndex are within bounds
    */
    @Test
    public void testReverseIndexWithinBounds(){
        baseList.reverseRegion(1, 3);
        assertEquals(baseList.data[1], baseListUnchanged.data[3]);
        assertEquals(baseList.data[1], 4);
        assertEquals(baseList.data[2], baseListUnchanged.data[2]);
        assertEquals(baseList.data[2], 3);
        assertEquals(baseList.data[3], baseListUnchanged.data[1]);
        assertEquals(baseList.data[3], 2);
    }

    /**
     * Tests reverseRegion method when calling it on an empty MyArrayList.
     * Theoretically, the test should return an IndexOutOfBounds exception
     * because even though the index are within the MyArrayList capacity
     * the list is empty
    */
    @Test
    public void testReverseCustom(){
        boolean EXCEPTION_THROWN = false;
        try{
            emptyList.reverseRegion(2, 4);
        }
        catch(IndexOutOfBoundsException e){
            EXCEPTION_THROWN = true;
        }
        assertTrue(EXCEPTION_THROWN);
    }

    /**
     * Tests reverseRegion method when both fromIndex and toIndex are OOB 
     */
    @Test
    public void testReverseIndexOutOfBounds2(){
        boolean EXCEPTION_THROWN = false;
        try{
            baseList.reverseRegion(-4, 10);
        }
        catch(IndexOutOfBoundsException e){
            EXCEPTION_THROWN = true;
        }
        assertTrue("An IndexOutOfBounds exception should be " +
                    "thrown", EXCEPTION_THROWN);
    }

    /**
     * Tests reverseRegion method when both fromIndex is OOB 
     */
    @Test
    public void testReverseIndexOutOfBounds3(){
        boolean EXCEPTION_THROWN = false;
        try{
            baseList.reverseRegion(-1, 3);
        }
        catch(IndexOutOfBoundsException e){
            EXCEPTION_THROWN = true;
        }
        assertTrue("An IndexOutOfBounds exception should be " +
                    "thrown", EXCEPTION_THROWN);
    }

    /**
     * Tests reverseRegion method when fromIndex and toIndex 
     * are the list's bounds.
     */
    @Test
    public void testReverseRegionIndexAreBounds(){
        baseList.reverseRegion(0, 4);
        assertEquals(baseList.data[0], baseListUnchanged.data[4]);
        assertEquals(baseList.data[1], baseListUnchanged.data[3]);
        assertEquals(baseList.data[2], baseListUnchanged.data[2]);
        assertEquals(baseList.data[3], baseListUnchanged.data[1]);
        assertEquals(baseList.data[4], baseListUnchanged.data[0]);
    }

    /**
     * Tests the reverseRegion method when called twice.
     * Ideally, it should return the initial MyArrayList
     */
    @Test
    public void testReverseRegionCalledTwice(){
        baseList.reverseRegion(0, 4);
        baseList.reverseRegion(0, 4);
        assertArrayEquals(baseList.data, baseListUnchanged.data);
    }

}
