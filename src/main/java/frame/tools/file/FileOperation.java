package frame.tools.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class FileOperation {
	protected String filePath;
	
	public FileOperation(String filePath){
		this.filePath= filePath;
	}

	public HashMap<String, Integer> getCaseId() {
		InputStreamReader in;
		BufferedReader br;
		HashMap<String, Integer> cc = new HashMap<String, Integer>();
		try {
			in = new InputStreamReader(new FileInputStream(new File(this.filePath)));
			br = new BufferedReader(in);
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] temp = line.split("=");
				cc.put(temp[0].trim(), Integer.parseInt(temp[1].trim()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cc;
	}

	public void clearFile() {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(this.filePath, false);
			out.write(new String("").getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
