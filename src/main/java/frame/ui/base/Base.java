package frame.ui.base;

import java.lang.reflect.Method;
import java.util.HashMap;

public class Base {

	public HashMap<String, HashMap<String, Object>> getAllMethods(Object browser) {
		Method mm = null;
		// Object[] parameters = null;

		HashMap<String, HashMap<String, Object>> allMethods = new HashMap<String, HashMap<String, Object>>();

		Method[] m1 = browser.getClass().getDeclaredMethods();
		for (Method m : m1) {
			// if(m.equals("sendKeys")){
			mm = m;
			String mname = mm.getName();
			// Class[] cl = m.getParameterTypes();
			// parameters = new Object[cl.length];
			// for(int i = 0;i<cl.length;i++)
			// {
			// parameters[i]=cl[i];
			// }
			HashMap<String, Object> temp = new HashMap<String, Object>();
			temp.put("method", mm);
			// temp.put("parameters", parameters);
			allMethods.put(mname, temp);
			// }
		}
		return allMethods;
	}
}
