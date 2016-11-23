package frame.tools.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;


public class XmlFile {

	private StringBuffer sb = new StringBuffer();
	private BufferedReader br ;
	public XmlFile(String file) {
		try {
			InputStreamReader in = new InputStreamReader(new FileInputStream(new File(file)));
			br = new BufferedReader(in);
			String line = null;

			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void parseTestXml() {
		try {
			Document document = DocumentHelper.parseText(sb.toString());
			Element root = document.getRootElement();
			
			Iterator<Element> a =  root.element("testSuites").elementIterator();
			
			int testCount = root.element("testSuites").elements("test").size();
			
//			Element ele = root.element("testSuites").elements("test").get(0);
//			
//			String caseName=ele.attributeValue("name");
//			String desc = ele.attributeValue("description");
//			System.out.println(caseName + " "+ desc);
//			
//			ele.elementIterator();
//			
//			
			
			
			
			while(a.hasNext()){
				Element temp = a.next();
				if("test".equals(temp.getName())){
					String caseName=temp.attributeValue("name");
					String desc = temp.attributeValue("description");
					System.out.println(caseName + " "+ desc);
				}
				
				Iterator<Element> child = temp.elementIterator();
				while(child.hasNext())
				{
					Element t = child.next();
					if("step".equals(t.getName())){
						String stepName = t.attributeValue("description");
						List<Attribute> attrs = t.attributes();
						for(Attribute attr : attrs){
							System.out.println(attr.getName() + "  "+ attr.getValue());
							
						}
					}
				}
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
