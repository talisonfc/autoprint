package br.com.fotonica.autoprint;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class FileHandler {
	
	public static Boolean remove(String uri) {
		File file = new File(uri);
		return file.delete();
	}
	
	public static String read(String uri) {
		String content = "";
		BufferedReader objReader = null;
		try {
			String strCurrentLine;
			objReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(uri), Charset.forName("ISO-8859-1")));
			while ((strCurrentLine = objReader.readLine()) != null) {
				content += strCurrentLine;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (objReader != null)
					objReader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return content;
	}
}
