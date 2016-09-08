package frame.tools.file;

import java.util.HashMap;

public interface IFile {
	String filename="global.ini";
	HashMap<String,String> getConfig();
}
