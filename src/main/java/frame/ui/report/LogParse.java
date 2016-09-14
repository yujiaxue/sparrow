package frame.ui.report;

import java.io.BufferedReader;
import java.io.FileReader;

import frame.tools.file.WriteHtml;

public class LogParse {

	private static String parseType;
	static BufferedReader in;
	static int lineNumber = 1;
	static int caseNumber = 0;
	private static String header = "<html><head>测试报告</head><body><table><th>序号</th><th>用例</th><th>数据</th><th>描述</th><th>状态</th><th>时间</th>";
	private static String template = "<tr><td>%d</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>";

	public static void parseLog(){
		parseType="html";
		//StringBuffer table= new StringBuffer();
		StringBuilder t = new StringBuilder(header);
		
		try {
			in  = new BufferedReader(new FileReader("A1.log"));
			String line;
			String caseName = null, chinese = null ;
			String[] data= null;
			while((line = in.readLine()) != null){
				String[] log = line.split(" ");
				if(log.length > 6){
					if(log[7].startsWith("onTestStart")){
						data = line.split("\\{");
						String[] acse = log[7].split("----");
						 caseName = acse[1]+"-"+acse[2];
						 chinese = acse[3];
					}else if (log[7].startsWith("onTestEnd")){
						String[] acse = log[7].split("----");
						if(caseName.equals(acse[1]+"-"+acse[2]) && chinese.equals(acse[3])){
							t.append(String.format(template, caseNumber++,caseName,data[1],chinese,acse[4],acse[5]));
						}
					}
					//WriteHtml.writeFile(order++,log[7]);
				}else{
					System.out.println("此行有问题");
					continue;
				}
			}
			t.append("</table></body></html>");
		}catch(Exception e){
			e.printStackTrace();
		}
		WriteHtml.writeHtml(t.toString());
	}

	public static void main(String[] args) {
		parseLog();
	}
}
