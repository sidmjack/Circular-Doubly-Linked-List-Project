


import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Before;
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

    private static final Integer INSERTINT = 2;

    @Before
    public void setupList() {
        this.myListInt = new CList<Integer>();
        this.singleNodeListInt = new CList<Integer>();
        this.testingListInt = new CList<Integer>();
        for (int i = 0; i < this.sizeOfList; ++i) {
        	this.myListInt.append(i);
        	this.testingListInt.append(i);
        }

        this.emptyListInt = new CList<Integer>();
        this.singleNodeListInt.append(0);
    // Now with String data instead
        this.myListStr = new CList<String>();
        this.singleNodeListStr = new CList<String>();
        this.testingListStr = new CList<String>();
        for (int i = 0; i < this.sizeOfList; ++i) {
        	this.myListStr.append(((Integer)i).toString());
        	this.testingListStr.append(((Integer)i).toString());
        }

        this.emptyListStr = new CList<String>();
        this.singleNodeListStr.append(((Integer)0).toString());

    }

    @Test
    public void newArrayShouldBeSizeZero() {
        assertEquals("size (" + emptyListInt.length() + ") is non-zero",
        	0, emptyListInt.length());
    // Now with String data instead
        assertEquals("size (" + emptyListStr.length() + ") is non-zero",
        	0, emptyListStr.length());
    }




    @Test
    public void emptyListShouldHaveNoElements() {
    	assertNull("Empty List returned non-null element from GetValue",
    		this.emptyListInt.getValue());    
// Now with String data instead
    	assertNull("Empty List returned non-null element from GetValue",
    		this.emptyListStr.getValue());
    }

    @Test
    public void posOfEmptyListIsZero() {
    	assertEquals(0, this.emptyListInt.currPos());    
// Now with String data instead
    	assertEquals(0, this.emptyListStr.currPos());
    }




    @Test
    public void emptyListInsertShouldNotChangePosition() {
    	this.emptyListInt.insert(-1);
    	int pos = this.emptyListInt.currPos();
    	assertEquals(0, pos);    
// Now with String data instead
    	this.emptyListStr.insert(((Integer)(-1)).toString());
    	pos = this.emptyListStr.currPos();
    	assertEquals(0, pos);
    }

    @Test
    public void emptyListAppendShouldNotChangePosition() {
    	this.emptyListInt.append(-1);
    	int pos = this.emptyListInt.currPos();
    	assertEquals(0, pos);    
// Now with String data instead
    	this.emptyListStr.append(((Integer)(-1)).toString());
    	pos = this.emptyListStr.currPos();
    	assertEquals(0, pos);
    }

    @Test
    public void emptyListRemoveShouldNotChangePosition() {
    	this.emptyListInt.remove();
    	int pos = this.emptyListInt.currPos();
    	assertEquals(0, pos);    
// Now with String data instead
    	this.emptyListStr.remove();
    	pos = this.emptyListStr.currPos();
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
        assertEquals("Inserted item not returned by getValue()",INSERTINT, this.emptyListInt.getValue());    
// Now with String data instead

        this.emptyListStr.insert(INSERTINT.toString());
        assertEquals("Length(" + this.emptyListStr.length() +
            ") is not 1 after insert into empty list",1,
            this.emptyListStr.length());
        assertEquals("Inserted item not returned by getValue()",INSERTINT.toString(), this.emptyListStr.getValue());
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
        assertEquals("Inserted item not returned by getValue()",INSERTINT, this.myListInt.getValue());
        
        int backpos = this.myListInt.currPos();
        
        this.myListInt.next();
        Integer newGuy = this.myListInt.getValue();
        assertEquals("Insert did not correctly make curr's next the old curr" , oldGuy, newGuy);
        assertEquals("Position should not change after insert",oldPos, backpos);    
// Now with String data instead
        String oldGuyStr = this.myListStr.getValue();
        oldPos = this.myListStr.currPos();

        len = this.myListStr.length();
        this.myListStr.insert(INSERTINT.toString());
        assertEquals("Length(" + this.myListStr.length() +
            ") is not " + (len + 1) + " after insert into full list",len + 1,
            this.myListStr.length());
        assertEquals("Inserted item not returned by getValue()",INSERTINT.toString(), this.myListStr.getValue());
        
        backpos = this.myListStr.currPos();
        
        this.myListStr.next();
        String newGuyStr = this.myListStr.getValue();
        assertEquals("Insert did not correctly make curr's next the old curr" , oldGuyStr, newGuyStr);
        assertEquals("Position should not change after insert",oldPos, backpos);
    }

    // Now doing append things


    @Test
    public void appendIntoEmptyList() {

        this.emptyListInt.append(INSERTINT);
        assertEquals("Length(" + this.emptyListInt.length() +
            ") is not 1 after append into empty list",1,
            this.emptyListInt.length());
        assertEquals("Appended item not returned by getValue()",INSERTINT, this.emptyListInt.getValue());    
// Now with String data instead

        this.emptyListStr.append(INSERTINT.toString());
        assertEquals("Length(" + this.emptyListStr.length() +
            ") is not 1 after append into empty list",1,
            this.emptyListStr.length());
        assertEquals("Appended item not returned by getValue()",INSERTINT.toString(), this.emptyListStr.getValue());
    }


    @Test
    public void appendIntoFullList() {
        int oldPos = this.myListInt.currPos();

        int len = this.myListInt.length();
        this.myListInt.append(INSERTINT);
        assertEquals("Length(" + this.myListInt.length() +
            ") is not " + (len + 1) + " after append into full list",len + 1,
            this.myListInt.length());

        int backpos = this.myListInt.currPos();
        this.myListInt.moveToEnd();

        assertEquals("Last item in list not appended item",INSERTINT, this.myListInt.getValue());
        assertEquals("Position should not change after append",oldPos, backpos);    
// Now with String data instead
        oldPos = this.myListStr.currPos();

        len = this.myListStr.length();
        this.myListStr.append(INSERTINT.toString());
        assertEquals("Length(" + this.myListStr.length() +
            ") is not " + (len + 1) + " after append into full list",len + 1,
            this.myListStr.length());

        backpos = this.myListStr.currPos();
        this.myListStr.moveToEnd();

        assertEquals("Last item in list not appended item",INSERTINT.toString(), this.myListStr.getValue());
        assertEquals("Position should not change after append",oldPos, backpos);
    }



    //removal time!!
    

    @Test
    public void removeFromEmptyList() {

        assertNull("remove from empty list didn't return null",
            this.emptyListInt.remove());    
// Now with String data instead

        assertNull("remove from empty list didn't return null",
            this.emptyListStr.remove());
    }


    @Test
    public void removeFromFullList() {

        Integer temp = this.myListInt.getValue();
        int len = this.myListInt.length();
        int pos = this.myListInt.currPos();

        assertEquals( "Did not remove current node" ,temp, this.myListInt.remove());
        assertEquals( "Did not decrement length after remove" ,len - 1, this.myListInt.length());

        assertEquals( "Should not change position after remove()!", pos, this.myListInt.currPos());
    
// Now with String data instead

        String tempStr = this.myListStr.getValue();
        len = this.myListStr.length();
        pos = this.myListStr.currPos();

        assertEquals( "Did not remove current node" ,tempStr, this.myListStr.remove());
        assertEquals( "Did not decrement length after remove" ,len - 1, this.myListStr.length());

        assertEquals( "Should not change position after remove()!", pos, this.myListStr.currPos());

    }

    @Test
    public void removeFromSingleNodeList() {

        Integer temp = this.singleNodeListInt.getValue();
        int pos = this.singleNodeListInt.currPos();

        assertEquals( "Did not remove current node" ,temp, this.singleNodeListInt.remove());
        assertEquals( "Did not decrement length after remove" ,0 , this.singleNodeListInt.length());

        assertEquals( "Should not change position after remove()!", 0, this.singleNodeListInt.currPos());

        assertNull( "Single node list should have null element after remove" , this.singleNodeListInt.getValue());    
// Now with String data instead

        String tempStr = this.singleNodeListStr.getValue();
        pos = this.singleNodeListStr.currPos();

        assertEquals( "Did not remove current node" ,tempStr, this.singleNodeListStr.remove());
        assertEquals( "Did not decrement length after remove" ,0 , this.singleNodeListStr.length());

        assertEquals( "Should not change position after remove()!", 0, this.singleNodeListStr.currPos());

        assertNull( "Single node list should have null element after remove" , this.singleNodeListStr.getValue());
    }


    @Test
    public void getValueFromEmptyList() {
        assertNull( "Empty list should be null" , this.emptyListInt.getValue());
    
// Now with String data instead
        assertNull( "Empty list should be null" , this.emptyListStr.getValue());

    }

    @Test
    public void getValueFromFullList() {
        this.myListInt.moveToStart();
        for (int i = 0; i < this.sizeOfList; ++i) {
            assertEquals("The " + i + "th element should be " + i, (Integer)i, this.myListInt.getValue());
            this.myListInt.next();
        }
    
// Now with String data instead
        this.myListStr.moveToStart();
        for (int i = 0; i < this.sizeOfList; ++i) {
            assertEquals("The " + i + "th element should be " + i, ((Integer)i).toString(), this.myListStr.getValue());
            this.myListStr.next();
        }

    }

    //Length!!
    

    @Test
    public void lengthShouldBeZeroInEmptyList() {

        assertEquals("Length(" + this.emptyListInt.length() + ") of empty list should be zero", 0, this.emptyListInt.length());    
// Now with String data instead

        assertEquals("Length(" + this.emptyListStr.length() + ") of empty list should be zero", 0, this.emptyListStr.length());
    }


    // Move To Start...

    @Test
    public void emptyListMoveToStartDoesNotMove() {
        int pos = this.emptyListInt.currPos();
        this.emptyListInt.moveToStart();
        assertEquals(pos, this.emptyListInt.currPos());
        assertEquals(0, pos);    
// Now with String data instead
        pos = this.emptyListStr.currPos();
        this.emptyListStr.moveToStart();
        assertEquals(pos, this.emptyListStr.currPos());
        assertEquals(0, pos);
    }

    @Test
    public void fullListMoveToStart() {
        this.myListInt.moveToStart();
        assertEquals(0, this.myListInt.currPos());    
// Now with String data instead
        this.myListStr.moveToStart();
        assertEquals(0, this.myListStr.currPos());
    }


    //move to end
    //
    
    @Test
    public void emptyListMoveToEndDoesNotMove() {
        int pos = this.emptyListInt.currPos();
        this.emptyListInt.moveToEnd();
        assertEquals(pos, this.emptyListInt.currPos());
        assertEquals(0, pos);    
// Now with String data instead
        pos = this.emptyListStr.currPos();
        this.emptyListStr.moveToEnd();
        assertEquals(pos, this.emptyListStr.currPos());
        assertEquals(0, pos);
    }

    @Test
    public void fullListMoveToEnd() {
        this.myListInt.moveToEnd();
        assertEquals(this.myListInt.length() - 1, this.myListInt.currPos());    
// Now with String data instead
        this.myListStr.moveToEnd();
        assertEquals(this.myListStr.length() - 1, this.myListStr.currPos());
    }

    // prev..............


    @Test
    public void emptyListPrevDoesNotMove() {
        this.emptyListInt.prev();
        assertEquals(0, this.myListInt.currPos());
    
// Now with String data instead
        this.emptyListStr.prev();
        assertEquals(0, this.myListStr.currPos());

    }

    // prev

    @Test
    public void fullListPrev() {
        this.myListInt.moveToEnd();
        for (int i = this.myListInt.length() - 1; i >= 0; --i) {
            assertEquals("Prev should decrement currPos() when not at head", i, this.myListInt.currPos());
            this.myListInt.prev();
        }
        assertEquals("Prev should not move if at head", 0, this.myListInt.currPos());
    
// Now with String data instead
        this.myListStr.moveToEnd();
        for (int i = this.myListStr.length() - 1; i >= 0; --i) {
            assertEquals("Prev should decrement currPos() when not at head", i, this.myListStr.currPos());
            this.myListStr.prev();
        }
        assertEquals("Prev should not move if at head", 0, this.myListStr.currPos());

    }

    //next
    
    @Test
    public void emptyListNextDoesNotMove() {
        this.emptyListInt.next();
        assertEquals(0, this.myListInt.currPos());
    
// Now with String data instead
        this.emptyListStr.next();
        assertEquals(0, this.myListStr.currPos());

    }


    @Test
    public void fullListNext() {
        this.myListInt.moveToStart();
        for (int i = 0; i < this.myListInt.length(); ++i) {
            assertEquals("Next should increment currPos() when not at tail", i, this.myListInt.currPos());
            this.myListInt.next();
        }
        assertEquals("Next should not move if at tail", myListInt.length() - 1, this.myListInt.currPos());
    
// Now with String data instead
        this.myListStr.moveToStart();
        for (int i = 0; i < this.myListStr.length(); ++i) {
            assertEquals("Next should increment currPos() when not at tail", i, this.myListStr.currPos());
            this.myListStr.next();
        }
        assertEquals("Next should not move if at tail", myListStr.length() - 1, this.myListStr.currPos());

    }


    // currPos()

    @Test
    public void testCurrPos() {
        this.myListInt.moveToStart();
        assertEquals("Position at head of list should be zero",0, this.myListInt.currPos());

        this.myListInt.moveToEnd();
        assertEquals("Position at tail of list should be length - 1",this.myListInt.length() - 1, this.myListInt.currPos());
    
// Now with String data instead
        this.myListStr.moveToStart();
        assertEquals("Position at head of list should be zero",0, this.myListStr.currPos());

        this.myListStr.moveToEnd();
        assertEquals("Position at tail of list should be length - 1",this.myListStr.length() - 1, this.myListStr.currPos());

    }

    // move to pause :p
    // 
    

    @Test
    public void testMovetoPos() {
        for (int i = 0; i < this.myListInt.length(); ++i) {
            this.myListInt.moveToPos(i);
            assertEquals("Current position should be the position (" + i + ")", i, this.myListInt.currPos());
        }
        assertFalse("Cannot move to negative position",this.myListInt.moveToPos(-17));

        int len = this.myListInt.length();
        assertFalse("Cannot move to position greater than length",this.myListInt.moveToPos(len * 2));    
// Now with String data instead
        for (int i = 0; i < this.myListStr.length(); ++i) {
            this.myListStr.moveToPos(i);
            assertEquals("Current position should be the position (" + i + ")", i, this.myListStr.currPos());
        }
        assertFalse("Cannot move to negative position",this.myListStr.moveToPos(-17));

        len = this.myListStr.length();
        assertFalse("Cannot move to position greater than length",this.myListStr.moveToPos(len * 2));
    }


    //  is at end

    @Test
    public void testIsAtEnd() {
        this.myListInt.moveToEnd();
        assertTrue("isAtEnd() should return true at end of list" ,this.myListInt.isAtEnd());    
// Now with String data instead
        this.myListStr.moveToEnd();
        assertTrue("isAtEnd() should return true at end of list" ,this.myListStr.isAtEnd());
    }


    @Test
    public void testToString() {
        this.myListInt.moveToStart();
        for (int i = 0; i < this.myListInt.length(); ++i) {
            assertThat(this.myListInt.toString(), containsString( ( (Integer)i).toString() ) );
        }
    
// Now with String data instead
        this.myListStr.moveToStart();
        for (int i = 0; i < this.myListStr.length(); ++i) {
            assertThat(this.myListStr.toString(), containsString( ( (Integer)i).toString() ) );
        }

    }



}