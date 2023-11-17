package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.MyDeathNote;

public class TestDeathNote {
    MyDeathNote kelyDeathNote;

    @BeforeEach
    public void initialisation(){
        kelyDeathNote= new MyDeathNote();
    }   

    
    @Test
    void testGetRule(){
        String rule5=kelyDeathNote.getRule(5);
        System.out.println(rule5);
        assertNotNull(rule5);
        assertTrue(rule5.length()>0);
        try {
            kelyDeathNote.getRule(-5);
            fail();
        } catch (IllegalArgumentException e) {
            assertNotEquals("",e.getMessage());
            assertNotNull(e.getMessage());
            assertTrue(e.getMessage().length()>0);
        }
        try {
            kelyDeathNote.getRule(0);
            fail();
        } catch (IllegalArgumentException e) {
            assertNotEquals("",e.getMessage());
            assertNotNull(e.getMessage());
            assertTrue(e.getMessage().length()>0);
        }
        try {
            kelyDeathNote.getRule(30);
            fail();
        } catch (IllegalArgumentException e) {
            assertNotEquals("", e.getMessage());
            assertNotNull(e.getMessage());
            assertTrue(e.getMessage().length()>0);
        }
    }

    @Test
    public void testWriteName(){
        assertTrue(kelyDeathNote.getNameCount()==0);
        String actualName ="krystal";
        kelyDeathNote.writeName(actualName);
        ArrayList<ArrayList<String>> listName=kelyDeathNote.getNameList();
        String expectedName=listName.get(0).get(0);
        assertEquals(expectedName, actualName);
        assertTrue(kelyDeathNote.getNameCount()==1);
        assertNotEquals("", listName.get(0).get(0));
        try {
            kelyDeathNote.writeName("");
            fail();
        } catch (NullPointerException e) {
            assertNotEquals("", e.getMessage());
            assertNotNull(e.getMessage());
            assertTrue(e.getMessage().length()>0);
        }
    }

    @Test
    public void testWriteDeathCause() throws InterruptedException{
        try {
            kelyDeathNote.writeDeathCause("");
            fail();
        } catch (IllegalStateException e) {
            assertNotEquals("", e.getMessage());
            assertNotNull(e.getMessage());
            assertTrue(e.getMessage().length()>0);
        }
        kelyDeathNote.writeName("Pamella");
        assertEquals("heart attack", kelyDeathNote.getNameList().get(0).get(1));
        kelyDeathNote.writeName("Aubin");
        boolean causeHasBeenSeted=kelyDeathNote.writeDeathCause("karting accident");
        assertTrue(causeHasBeenSeted);
        assertEquals("karting accident", kelyDeathNote.getNameList().get(1).get(1));
        Thread.sleep(100);
        causeHasBeenSeted=kelyDeathNote.writeDeathCause("AVC");
        assertFalse(causeHasBeenSeted);
        assertNotEquals("AVC", kelyDeathNote.getNameList().get(1).get(1));
        try {
            kelyDeathNote.writeDeathCause("");
            fail();
        } catch (IllegalStateException e) {
            assertNotEquals("", e.getMessage());
            assertNotNull(e.getMessage());
            assertTrue(e.getMessage().length()>0);
        }
    }

    @Test
    public void testWriteDetails() throws InterruptedException{
        try {
            kelyDeathNote.writeDetails("");
            fail();
        } catch (IllegalStateException e) {
            assertNotEquals("", e.getMessage());
            assertNotNull(e.getMessage());
            assertTrue(e.getMessage().length()>0);
        }
        kelyDeathNote.writeName("Pamella");
        assertEquals("", kelyDeathNote.getNameList().get(0).get(2));
        kelyDeathNote.writeDetails("ran for too long");
        assertEquals("ran for too long", kelyDeathNote.getNameList().get(0).get(2));
        kelyDeathNote.writeName("Aubin");
        Thread.sleep(6100);
        boolean causeHasBeenSeted=kelyDeathNote.writeDetails("AVC");
        assertFalse(causeHasBeenSeted);
        assertEquals("", kelyDeathNote.getNameList().get(1).get(2));
        try {
            kelyDeathNote.writeDeathCause("");
            fail();
        } catch (IllegalStateException e) {
            assertNotEquals("", e.getMessage());
            assertNotNull(e.getMessage());
            assertTrue(e.getMessage().length()>0);
        }
    }

    @Test
    public void testGetDeathCause(){
        kelyDeathNote.writeName("Aubin");
        assertEquals("heart attack",kelyDeathNote.getDeathCause("Aubin"));
        kelyDeathNote.writeName("Carla");
        kelyDeathNote.writeDeathCause("accident");
        assertEquals("accident",kelyDeathNote.getDeathCause("Carla"));
        try {
            kelyDeathNote.getDeathCause("alfa");
            fail();
        } catch (IllegalArgumentException e) {
            assertNotEquals("", e.getMessage());
            assertNotNull(e.getMessage());
            assertTrue(e.getMessage().length()>0);
        }
    }

    @Test
    public void testGetDeathDetails(){
        kelyDeathNote.writeName("Aubin");
        assertEquals("heart attack",kelyDeathNote.getDeathCause("Aubin"));
        kelyDeathNote.writeName("Carla");
        kelyDeathNote.writeDeathCause("accident");
        assertEquals("accident",kelyDeathNote.getDeathCause("Carla"));
        try {
            kelyDeathNote.getDeathCause("alfa");
            fail();
        } catch (IllegalArgumentException e) {
            assertNotEquals("", e.getMessage());
            assertNotNull(e.getMessage());
            assertTrue(e.getMessage().length()>0);
        }
    }

    @Test
    public void testIsNameWritten(){
        kelyDeathNote.writeName("Carla");
        assertTrue(kelyDeathNote.isNameWritten("Carla"));
    }
}