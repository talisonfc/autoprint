package br.com.fotonica.autoprint;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

public class Print {

	public void exec(String uri) {
		System.out.println(String.format("print %s", uri));
		String content = FileHandler.read(uri);
		System.err.println(content);
		
		try {
			this.simpleDoc(content);
		} catch (PrintException e) {
			e.printStackTrace();
		}
		
		FileHandler.remove(uri);
	}

	public void simpleDoc(String content) throws PrintException {
		DocFlavor flavor = DocFlavor.STRING.TEXT_PLAIN;
		PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
		DocPrintJob job = printService.createPrintJob();
		Doc doc = new SimpleDoc(content, flavor, null);
		job.print(doc, null);
	}

	public void openHandler(String handlerKey) {

	}

}
