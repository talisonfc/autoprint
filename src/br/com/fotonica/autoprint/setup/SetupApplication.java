package br.com.fotonica.autoprint.setup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import br.com.fotonica.autoprint.Variables;

public class SetupApplication {

	static JFrame f;

	public static void main(String[] args) {
		crateSettingFolder();
		copyExecutable();
		copyStartup();
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setDialogTitle("Escolher diretorio de arquivos .txt");
		int result = fileChooser.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			saveAbsolutePath(file.getAbsolutePath());
		}
		
		StartupApplication.main(args);
	}

	public static void saveAbsolutePath(String absolutePath) {
		File f = new File(Variables.fileConfigURI + "/" + Variables.fileNameConfig);
		try {
			if (!f.exists())
				f.createNewFile();
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(absolutePath.getBytes());
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void crateSettingFolder() {
		File f = new File(Variables.fileConfigURI);
		if (!f.exists())
			f.mkdir();
	}

	public static void copyExecutable() {
		String root = new File("").getAbsolutePath();
		File origin = new File(root + "/autoprint.jar");
		File target = new File(Variables.fileConfigURI + "/autoprint.jar");

		try {
			Files.copy(origin.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void copyStartup() {
		String root = new File("").getAbsolutePath();
		File origin = new File(root + "/autoprint-startup.exe");
		File target = new File(Variables.startupWindowsFolderURI + "/autoprint-startup.exe");

		try {
			Files.copy(origin.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
