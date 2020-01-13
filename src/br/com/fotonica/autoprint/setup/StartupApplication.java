package br.com.fotonica.autoprint.setup;

import java.io.IOException;

public class StartupApplication {
	
	public static void main(String[] args) {
		try {
			Runtime.getRuntime().exec("java -jar C:/autoprint/autoprint.jar");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
