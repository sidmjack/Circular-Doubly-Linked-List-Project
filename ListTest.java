

// import org.junit.*;
// import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.hamcrest.CoreMatchers.containsString;


public class ListTest {

    private List<Integer> myListInt, testingListInt, emptyListInt, singleNodeListInt;
    private List<String> myListStr, testingListStr, emptyListStr, singleNodeListStr;

    private final int sizeOfList = 5;

    private static final int INSERTINT = 2;

    @Before
    public void setupList() throws InvalidLengthException {
        this.myListInt = new CList<Integer>();
        this.singleNodeListInt = new CList<Integer>();
        this.testingListInt = new CList<Integer>();
        for (int i = 0; i < this.sizeOfList; ++i) {
        	this.myListInt.append(i);
        	this.testingListInt.append(i);
        }

        this.emptyListInt = new CList<Integer>();
        this.singleNodeListInt.append(0);

    }

    @Test
    public void newArrayShouldBeEmpty() throws InvalidLengthException {
        assertTrue("List is not empty", this.emptyListInt.isEmpty());
    }

    @Test
    public void newArrayShouldBeSizeZero() throws InvalidLengthException,
    InvalidIndexException {
        assertEquals("size (" + myListInt.length() + ") is non-zero",
        	0, myListInt.length());
    }




    @Test
    public void emptyListShouldHaveNoElements() {
    	assertNull("Empty List returned non-null element from GetValue",
    		this.emptyListInt.getValue());
    }

    @Test
    public void posOfEmptyListIsZero() {
    	assertEquals(0, this.emptyListInt.currPos());
    }




    @Test
    public void emptyListInsertShouldNotChangePosition() {
    	this.emptyListInt.insert(-1);
    	int pos = this.emptyListInt.currPos();
    	assertEquals(0, pos);
    }

    @Test
    public void emptyListAppendShouldNotChangePosition() {
    	this.emptyListInt.append(-1);
    	int pos = this.emptyListInt.currPos();
    	assertEquals(0, pos);
    }

    @Test
    public void emptyListRemoveShouldNotChangePosition() {
    	this.emptyListInt.remove();
    	int pos = this.emptyListInt.currPos();
    	assertEquals(0, pos);
    }


    // We're gonna start testing the inserts here
    // 

    @Test
    public void insertIntoEmptyList() {

        this.emptyListInt.insert(INSERTINT);
        assertEquals("Length(" + this.emptyListInt.length() +
            ") is not 1 after insert into empty list",1,
            this.emptyListInt.length());
        assertEquals("Inserted item not returned by getValue",INSERTINT, this.emptyListInt.getValue());
    }


    @Test
    public void insertIntoFullList() {
        Integer oldGuy = this.myListInt.getValue();
        int oldPos = this.myListInt.currPos();

        int len = this.myListInt.length();
        this.myListInt.insert(INSERTINT);
        assertEquals("Length(" + this.myListInt.length() +
            ") is not " + (len + 1) + " after insert into full list",len + 1,
            this.myListInt.length());
        assertEquals("Inserted item not returned by getValue",INSERTINT, this.myListInt.getValue());
        this.next();
        Integer newGuy = this.myListInt.getValue;
        assertEquals("Insert did not correctly make curr's next the old curr" , oldGuy, newGuy);
        assertEquals("Position should not change after insert",oldPos, this.myListInt.currPos());
    }

    // Now doing append things


    @Test
    public void appendIntoEmptyList() {

        this.emptyListInt.append(INSERTINT);
        assertEquals("Length(" + this.emptyListInt.length() +
            ") is not 1 after append into empty list",1,
            this.emptyListInt.length());
        assertEquals("Appended item not returned by getValue",INSERTINT, this.emptyListInt.getValue());
    }


    @Test
    public void appendIntoFullList() {
        Integer oldGuy = this.myListInt.getValue();
        int oldPos = this.myListInt.currPos();

        int len = this.myListInt.length();
        this.myListInt.append(INSERTINT);
        assertEquals("Length(" + this.myListInt.length() +
            ") is not " + (len + 1) + " after append into full list",len + 1,
            this.myListInt.length());

        this.myListInt.moveToEnd();

        assertEquals("Last item in list not appended item",INSERTINT, this.myListInt.getValue());
        this.next();
        Integer newGuy = this.myListInt.getValue;
        assertEquals("Position should not change after append",oldPos, this.myListInt.currPos());
    }



    //removal time!!
    

    @Test
    public void removeFromEmptyList() {

        assertNull("remove from empty list didn't return null",
            this.emptyListInt.remove());
    }


    @Test
    public void removeFromFullList() {

        Integer temp = this.myListInt.getValue();
        int len = this.myListInt.length();
        int pos = this.myListInt.currPos();

        assertEquals( "Did not remove current node" ,temp, this.myListInt.remove());
        assertEquals( "Did not decrement length after remove" ,len - 1, this.myListInt.length());

        assertEquals( "Should not change position after remove()!", pos, this.myListInt.currPos());

    }

    @Test
    public void removeFromSingleNodeList() {

        Integer temp = this.singleNodeListInt.getValue();
        int pos = this.singleNodeListInt.currPos();

        assertEquals( "Did not remove current node" ,temp, this.singleNodeListInt.remove());
        assertEquals( "Did not decrement length after remove" ,0 , this.singleNodeListInt.length());

        assertEquals( "Should not change position after remove()!", 0, this.singleNodeListInt.currPos());

        assertNull( "Single node list should have null element after remove" , this.singleNodeListInt.getValue());
    }


    @Test
    public void getValueFromEmptyList() {
        assertNull( "Empty list should be null" , this.emptyListInt.getValue());

    }

    @Test
    public void getValueFromFullList() {
        this.myListInt.moveToStart();
        for (int i = 0; i < this.sizeOfList; ++i) {
            assertEquals("The " + i + "th element should be " + i, i, this.myListInt.getValue());
            this.myListInt.next();
        }

    }

    //Length!!
    

    @Test
    public void lengthShouldBeZeroInEmptyList() {

        assertEquals("Length(" + this.emptyListInt.length() + ") of empty list should be zero", 0, this.emptyListInt.length());
    }


    // Move To Start...

    @Test
    public void emptyListMoveToStartDoesNotMove() {
        int pos = this.emptyListInt.currPos();
        this.emptyListInt.moveToStart();
        assertEquals(pos, this.emptyListInt.currPos);
        assertEquals(0, pos);
    }

    @Test
    public void fullListMoveToStart() {
        this.myListInt.moveToStart();
        assertEquals(0, this.myListInt.currPos);
    }


    //move to end
    //
    
    @Test
    public void emptyListMoveToEndDoesNotMove() {
        int pos = this.emptyListInt.currPos();
        this.emptyListInt.moveToEnd();
        assertEquals(pos, this.emptyListInt.currPos);
        assertEquals(0, pos);
    }

    @Test
    public void fullListMoveToEnd() {
        this.myListInt.moveToEnd();
        assertEquals(this.myListInt.length() - 1, this.myListInt.currPos);
    }

    // prev..............


    @Test
    public void emptyListPrevDoesNotMove() {
        this.emptyListInt.prev();
        assertEquals(0, this.myListInt.currPos());

    }

    // prev

    @Test
    public void fullListPrev() {
        this.myListInt.moveToEnd();
        for (int i = this.myListInt.length() - 1; i >= 0; --i) {
            assertEquals("Prev should decrement currPos when not at head", i, this.myListInt.currPos());
            this.myListInt.prev();
        }
        assertEquals("Prev should not move if at head", 0, this.myListInt.currPos());

    }

    //next
    
    @Test
    public void emptyListNextDoesNotMove() {
        this.emptyListInt.next();
        assertEquals(0, this.myListInt.currPos());

    }


    @Test
    public void fullListNext() {
        this.myListInt.moveToStart();
        for (int i = 0; i < this.myListInt.length(); ++i) {
            assertEquals("Next should increment currPos when not at tail", i, this.myListInt.currPos());
            this.myListInt.next();
        }
        assertEquals("Next should not move if at tail", my.myListInt.length() - 1, this.myListInt.currPos());

    }


    // currPos()

    @Test
    public void testCurrPos() {
        this.myListInt.moveToStart();
        assertEquals("Position at head of list should be zero",0, this.myListInt.currPos());

        this.myListInt.moveToEnd();
        assertEquals("Position at tail of list should be length - 1",this.myListInt.length() - 1, this.myListInt.currPos());

    }

    // move to pause :p
    // 
    

    @Test
    public void testMovetoPos() {
        for (int i = 0; i < this.myListInt.length(); ++i) {
            assertEquals("Current position should be the position (" + i + ")", i, this.myListInt.currPos());
        }
        assertFalse("Cannot move to negative position",this.myListInt.moveToPos(-17));

        int len = this.myListInt.length();
        assertFalse("Cannot move to position greater than length",this.myListInt.moveToPos(len * 2));
    }


    //  is at end

    @Test
    public void testIsAtEnd() {
        this.myListInt.moveToEnd();
        assertTrue("isAtEnd() should return true at end of list" ,this.myListInt.isAtEnd());
    }


    @Test
    public void testToString() {
        this.myListInt.moveToStart();
        for (int i = 0; i < this.myListInt.length(); ++i) {
            assertThat(this.myListInt.toString(), containsString( ( (Integer)i).toString() ) );
        }

    }



}