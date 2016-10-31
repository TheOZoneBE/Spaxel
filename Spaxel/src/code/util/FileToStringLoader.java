package code.util;

import code.Game;

import java.io.*;

public class FileToStringLoader {

	public FileToStringLoader() {
	}
	
	public String loadAsString(String file) {
		StringBuilder result = new StringBuilder();
		try {
			InputStreamReader isreader = new InputStreamReader(getClass().getResourceAsStream(file));
			BufferedReader reader = new BufferedReader(isreader);
			String buffer = "";
			while ((buffer = reader.readLine()) != null) {
				result.append(buffer + '\n');
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}

}
