package test.code; 

import junit.framework.Test; 
import junit.framework.TestSuite; 
import junit.framework.TestCase; 

/** 
* MyTestClass Tester. 
* 
* @author <Authors name> 
* @since <pre>08/07/2019</pre> 
* @version 1.0 
*/ 
public class MyTestClassTest extends TestCase { 
public MyTestClassTest(String name) { 
super(name); 
} 

public void setUp() throws Exception { 
super.setUp(); 
} 

public void tearDown() throws Exception { 
super.tearDown(); 
} 

/** 
* 
* Method: test1() 
* 
*/ 
public void testTest1() throws Exception { 
//TODO: Test goes here... 
} 



public static Test suite() { 
return new TestSuite(MyTestClassTest.class); 
} 
} 
