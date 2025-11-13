package practice;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WorkingWithAssertions {
@Test
public void test()
{
	String s = "Savitha";
	System.out.println("start");
	SoftAssert soft = new SoftAssert();
	//soft.assertEquals("hdfc", "hdfc");
	//soft.assertTrue("hdfc".equals("hdfc"));
	soft.assertNotNull(s);
	System.out.println("end");
	soft.assertAll();
}
}

//same for hard assert
//System.out.println("start");
//Assert.assertEquals("hdfc","hdfc");
//System.out.println("end");