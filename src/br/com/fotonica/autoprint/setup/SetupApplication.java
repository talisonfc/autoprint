package br.com.fotonica.autoprint.setup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import br.com.fotonica.autoprint.Variables;

public class SetupApplication {

	public static void main(String[] args) {
		Object[] options = {"Iniciar Instalação"};
		
		JOptionPane jop = new JOptionPane();

		int dialog = JOptionPane.showOptionDialog(jop, "AutoPrint Setup - Aplicativo de impressão automática "
				+ "\nFotonica TI LDTA [Engenharia de Computação] "
				+ "\nTalison F. Costa - E-mail: tfccomputation@gmail.com - GitHub: github.com/talisonfc"
				+ "\nhttps://github.com/talisonfc/autoprint/blob/master/AutoPrint.rar", 
				"AutoPrintSetup", 
				JOptionPane.PLAIN_MESSAGE, 2, null, options, null);

		if(dialog != -1) {
			
			JFileChooser fileChooser = new JFileChooser();
			jop.add(fileChooser);
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fileChooser.setDialogTitle("Escolher diretorio de arquivos .txt");
			int result = fileChooser.showOpenDialog(null);
	
			if (result == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				crateSettingFolder();
				copyExecutable();
				copyStartup();
				saveAbsolutePath(file.getAbsolutePath());
				
				JOptionPane.showMessageDialog(jop, "AutoPrint instalado com sucesso"
						+ "\nFotonica TI LDTA [Engenharia de Computação] "
						+ "\nTalison F. Costa - E-mail: tfccomputation@gmail.com - GitHub: github.com/talisonfc"
						+ "\nhttps://github.com/talisonfc/autoprint/blob/master/AutoPrint.rar");
			}
			
			StartupApplication.main(args);	
			
			jop.repaint();
		}
		else {
			JOptionPane.showMessageDialog(jop, "Instalação interrompida - AutoPrint", "AutoPrintSetup", JOptionPane.ERROR_MESSAGE);
		}
		
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
