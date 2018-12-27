package code.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileToStringLoader {

	public FileToStringLoader() {
		super();
	}

	public String loadAsString(String file) {
		StringBuilder result = new StringBuilder();
		try {
			InputStreamReader isreader = new InputStreamReader(getClass().getResourceAsStream(file));
			BufferedReader reader = new BufferedReader(isreader);
			String buffer;
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
