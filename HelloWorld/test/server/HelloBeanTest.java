/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import javax.ejb.embeddable.EJBContainer;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Rohit
 */
public class HelloBeanTest {
    
    public HelloBeanTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of sayHello method, of class HelloBean.
     */
    @Test
    public void testSayHello() throws Exception {
        System.out.println("sayHello");
        String name = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        HelloBean instance = (HelloBean)container.getContext().lookup("java:global/classes/HelloBean");
        String expResult = "Hello";
        String result = instance.sayHello(name);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
