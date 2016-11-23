package org.frame.ui.sparrow;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.framework.data.XmlDataDriven;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
   
{
	@DataProvider(name="xmldriven")
	public Iterator<Object[]> data3(){
		XmlDataDriven xdd = new XmlDataDriven("http://10.7.243.110:8080/getcasexml/8/8");
		return xdd;
	}
   @Test(dataProvider="xmldriven")
   public void test01(List<HashMap<String,String>> data){
	   System.out.println(data);
   }
   
	   
}
