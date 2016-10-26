package org.framework.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XmlDataDriven implements Iterator<Object[]> {

	private StringBuffer sb = new StringBuffer();
	private BufferedReader br;
	private InputStreamReader in;
	private String xml;
	private Element root;
	private int caseCount = 0;
	private int loopindex = 0;

	public XmlDataDriven(Object o, String method) {
		String dataroot = System.getProperty("xml.dataroot");
		if (!dataroot.endsWith("/")) {
			dataroot = dataroot.concat("/");
		}
		xml = new StringBuilder(dataroot).append(o.getClass().getName().replaceAll("\\.", File.separator))
				.append(".xml").toString();
		readFile(xml);
		Document document = null;

		try {
			document = DocumentHelper.parseText(sb.toString());

		} catch (DocumentException e) {
			e.printStackTrace();
		}
		root = document.getRootElement();

		//caseCount = root.element("testSuites").elements("test").size();
		caseCount = root.elements("test").size();
	}

	public StringBuffer readFile(String file) {
		try {
			in = new InputStreamReader(new FileInputStream(new File(file)));
			br = new BufferedReader(in);
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb;
	}

	@Override
	public boolean hasNext() {
		if (loopindex < caseCount) {
			return true;
		} else {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	@Override
	public Object[] next() {
		ArrayList<Map<String, String>> onecase = new ArrayList<Map<String,String>>();
		
		Element ele = root.elements("test").get(loopindex);
		
		Iterator<Element> child = ele.elementIterator();
		int i =0;
		while(child.hasNext())
		{
			Map<String,String> steps = new HashMap<String,String>();
			Element t = child.next();
			steps.put("caseName", ele.attributeValue("name"));
			if("step".equals(t.getName())){
				List<Attribute> attrs = t.attributes();
				for(Attribute attr : attrs){
					steps.put(attr.getName(), attr.getValue());
				}
			}
			onecase.add(i, steps);
			i++;
		}
		
		Object[] o  = new Object[1];
		o[0] = onecase;
		loopindex++;
		return o;
	}

}
