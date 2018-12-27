package code.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import java.util.logging.Level;

public class FileToStringLoader {
	private static final Logger LOGGER = Logger.getLogger(FileToStringLoader.class.getName());

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
			LOGGER.log(Level.SEVERE, e.toString(), e);
		}
		return result.toString();
	}

}
