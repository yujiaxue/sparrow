package frame.test;

import frame.tools.file.XmlFile;

public class test {

	public static void main(String[] args) {
//		String s = "<a href=\"sale/mZn88_ljjMc/?from=010501&p=2&user=%edf\" class=\"nd-eadf\"> "+
//                    	"<a href=\"sale/zkRNiP4L3kg/?from=010501&p=2\">"+
//	                        "<div class=\"hot-sale-img\">";
//		//System.out.println(s);
//		Pattern p = Pattern.compile("href=\"(.*?)\"+.*?>");
//		Matcher m = p.matcher(s);
//		System.out.println(m.groupCount());
//		List<String> result=new ArrayList<String>();
//		while(m.find()){
//			result.add(m.group(1));
//		}
//		for(String s1:result){
//			System.out.println(s1);
//		}
//		CloseableHttpClient httpclient = HttpClients.createDefault();
//		HttpGet httpGet = new HttpGet("http://m.iwjw.com");
//		try {
//			CloseableHttpResponse response1 = httpclient.execute(httpGet);
//			int statuscode = response1.getStatusLine().getStatusCode();
//			if(statuscode ==200 || statuscode==304){
//				
//			}else{
//				
//			}
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		XmlFile xf = new XmlFile("TestCases.xml");
		xf.parseTestXml();
//		IniFile ini = new IniFile();
//		HashMap<String,String> p = ini.getConfig();
//		for(String key : p.keySet()){
//			System.out.println(p.get(key));
//		}
	}
}
