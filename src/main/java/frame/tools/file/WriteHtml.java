package frame.tools.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteHtml {

	private String suffix = "html";

	String[] tr;
	static FileWriter fw;
	public static void writeHtml(String msg) {
		File f = new File("report.html");
		try {
			fw = new FileWriter(f);
			fw.write(msg);
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(null != fw){
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void writeFile(int order, String log) {
		// String a = writeHtml(order,log);
		// try {
		// FileWriter fw = new FileWriter("report.html",true);
		// fw.write(a);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}
}
