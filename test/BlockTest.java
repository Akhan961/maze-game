package com.mycompany.app;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;


public class BlockTest {

    private
    Block blockTest;

    // Ran all tests and they all passed.
    //  Might go into further detail on the tests if I can figure out how to deeply test getters and setters

    @Before
    public void createBlock(){
        blockTest = new Block();
    }

    @Test
    public void setX(){
        blockTest.setX(2);
        int result = blockTest.getX();
        assertEquals(2, result);
    }

    @Test
    public void setY(){
        blockTest.setY(2);
        int result = blockTest.getY();
        assertEquals(2, result);
    }

    @Test
    public void getX(){
        blockTest.setX(2);
        int result = blockTest.getX();
        assertEquals(2, result);
    }

    @Test
    public void getY(){
        blockTest.setY(4);
        int result = blockTest.getY();
        assertEquals(4, result);
    }

    @Test
    public void setElement(){
        blockTest.setElement("c");
        String result = blockTest.getElement();
        assertEquals("c", result);
    }

    @Test
    public void getElement(){
        blockTest.setElement("*");
        String result = blockTest.getElement();
        assertEquals("*", result);
    }

    @Test
    public void setTemp(){
        blockTest.setElement("p");
        blockTest.setTemp("c");
        String result = blockTest.getTemp();
        assertEquals("p", result);
        result = blockTest.getElement();
        assertEquals("c", result);
    }

    @Test
    public void getTemp(){
        blockTest.setElement(" ");
        blockTest.setTemp("*");
        String result = blockTest.getTemp();
        assertEquals(" ", result);
        result = blockTest.getElement();
        assertEquals("*", result);
    }

    @Test
    public void removeTemp(){
        blockTest.setElement(" ");
        blockTest.setTemp("*");
        blockTest.removeTemp();
        String result = blockTest.getElement();
        assertEquals(" ", result);
    }
}