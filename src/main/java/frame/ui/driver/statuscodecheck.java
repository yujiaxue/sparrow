package frame.ui.driver;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class statuscodecheck {

	
	
	static FileWriter f = null;
	static LogEntries logEntries;
	static List<String> allurl = new ArrayList<String>();
	static List<String> source;
	static String baseUrl = "http://m.iwjwbeta.com";
	static Pattern p = Pattern.compile("href=\"(.*?)\"+.*?>");
	static CloseableHttpClient httpclient = HttpClients.createDefault();
	public static void main(String[] args) {

		try {
			f = new FileWriter("statuscode.log", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.setProperty("webdriver.chrome.driver", "tools/chromedriver");
		// ChromeOptions options = new ChromeOptions();
		DesiredCapabilities desired = new DesiredCapabilities();
		desired.setCapability("User-Agent",
				"Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.76 Mobile Safari/537.36");
		// options.addExtensions(new File("./tools/"));
		LoggingPreferences logpref = new LoggingPreferences();
		
		logpref.enable(LogType.BROWSER, Level.ALL);
		desired.setCapability(CapabilityType.LOGGING_PREFS, logpref);
		
		ChromeDriver driver = new ChromeDriver(desired);
		
		driver.get(baseUrl);
		//log(driver);
		logstatus(baseUrl);
		source = getsource(driver);
		String url;
		for (String s : source) {
			if (s.startsWith("/")) {
				url = baseUrl + s;
			} else {
				url = baseUrl + "/" + s;
			}

			System.out.println(url);
			driver.get(url);
			addsource(driver);
			logstatus(url);
			System.out.println("allurl length now is ..." + allurl.size());
			//log(driver);
		}
//		List<String> urls = new ArrayList<String>();
//		urls.addAll(allurl);
//		allurl.clear();
//		System.out.println("allurl after clear size is ..."+ allurl.size());
//		System.out.println("urls length now is " + urls.size());
//		for (String s : urls) {
//			if (s.startsWith("/")) {
//				url = baseUrl + s;
//			} else {
//				url = baseUrl + "/" + s;
//			}
//			System.out.println(url);
//			driver.get(url);
//			addsource(driver);
//			logstatus(url);
//			System.out.println("url length now is ..." + allurl.size());
//			//log(driver);
//		}
//		urls.clear();
//		urls.addAll(allurl);
//		for (String s : urls) {
//			System.out.println("urls length now is " + urls.size());
//			if (s.startsWith("/")) {
//				url = baseUrl + s;
//			} else {
//				url = baseUrl + "/" + s;
//			}
//			System.out.println(url);
//			driver.get(url);
//			//addsource(driver);
//			logstatus(url);
//			System.out.println("url length now is ..." + allurl.size());
//			//log(driver);
//		}
	}

	public static List<String> getsource(RemoteWebDriver driver) {
		String pagesource = driver.getPageSource();
		Matcher m = p.matcher(pagesource);
		List<String> result = new ArrayList<String>();
		while (m.find()) {
			result.add(m.group(1));
			//System.out.println(m.group(1));
		}
		return result;
	}

	public static void addsource(RemoteWebDriver driver) {
		String pagesource = driver.getPageSource();
		// Pattern p = Pattern.compile("href=\"(.*?)\">");
		Matcher m = p.matcher(pagesource);
		while (m.find()) {
			allurl.add(m.group(1));
		}
	}

	public static void log(RemoteWebDriver driver) {
		logEntries = driver.manage().logs().get(LogType.BROWSER);
		for (LogEntry entry : logEntries) {
			// System.out.println(new Date(entry.getTimestamp()) + " " +
			// entry.getLevel() + " " + entry.getMessage());
			// System.out.println(" " + entry.getLevel() + " " +
			// entry.getMessage());
			String message = entry.getMessage();
			Level level = entry.getLevel();
			//System.out.println(level+"  "+ message + "\n");
			try {
				f.write(level + "  " + message + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void logstatus(String url){
		if(url.contains("aliyun")){
			return;
		}
		HttpGet httpGet = new HttpGet(url);
		try {
			CloseableHttpResponse response1 = httpclient.execute(httpGet);
			int statuscode = response1.getStatusLine().getStatusCode();
			System.out.println(statuscode);
			if(statuscode ==200 || statuscode==304){
				System.out.println(".");
			}else{
				f.write(url + "   "+ statuscode);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
