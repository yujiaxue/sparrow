package frame.tools.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class IniFile implements IFile {
	public HashMap<String, String> getConfig() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(filename));
			System.getProperties().load(new FileInputStream(new File(filename)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		HashMap<String, String> props = new HashMap<String, String>();
		for (String name : prop.stringPropertyNames()) {
			props.put(name, prop.getProperty(name));
		}
		return props;
	}

}
