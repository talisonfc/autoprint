package br.com.fotonica.autoprint;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import br.com.fotonica.autoprint.setup.SetupApplication;

public class AutoPrintApplication {

	public static void main(String[] args) {
//		Print print = new Print();
//		String content = "Talison F. Costa";
//		print.scan();
		
		String uri = null;
//		String defaultUri = new File("").getAbsolutePath();
		String defaultUri = getAbsolutePath();
		
		if(args != null && args.length > 0) uri = args[0];
		else uri = defaultUri;
		
		WatcherDirectory watcherDirectory = new WatcherDirectory(uri);
		
		try {
			watcherDirectory.exec();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	public static String getAbsolutePath() {
		return FileHandler.read(Variables.fileConfigURI + "/" + Variables.fileNameConfig);
	}

}
