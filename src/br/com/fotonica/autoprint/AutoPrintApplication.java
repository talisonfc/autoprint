package br.com.fotonica.autoprint;

import java.io.IOException;

public class AutoPrintApplication {

	public static void main(String[] args) {
//		Print print = new Print();
//		String content = "Talison F. Costa";
//		print.scan();
		
		
//		String uri = "C:\\Users\\Wellyda\\Documents\\docs";
		String uri = args[0];
		WatcherDirectory watcherDirectory = new WatcherDirectory(uri);
		
		try {
			watcherDirectory.exec();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
