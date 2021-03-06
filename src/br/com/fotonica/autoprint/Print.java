package br.com.fotonica.autoprint;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;

public class Print {

	public void exec(String uri) {
		System.out.println("print " + uri);
		//String content = FileHandler.read(uri);
		
		try {
			//Thread.sleep(5000);
			fromComandLine(uri);
			FileHandler.remove(uri);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void fromComandLine(String uri) throws IOException, InterruptedException {
		Process process = Runtime.getRuntime().exec("notepad /p " + uri);
		process.waitFor();
	}

	public void simpleDoc(String content) throws PrintException {
		DocFlavor flavor = this.getDocFlavor();
		PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
		DocPrintJob job = printService.createPrintJob();
		Doc doc = new SimpleDoc(content, flavor, null);
		job.print(doc, null);
	}
	
	public void printFromStream(FileInputStream is) throws PrintException {
		DocFlavor flavor = this.getDocFlavor();
		PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
		DocPrintJob job = printService.createPrintJob();
		Doc doc = new SimpleDoc(is, flavor, null);
		PrintRequestAttributeSet attrib=new HashPrintRequestAttributeSet();
	    attrib.add(new Copies(1));
		job.print(doc, attrib);
	}
	
	public void scan() {
		PrintService service = PrintServiceLookup.lookupDefaultPrintService();
		DocFlavor [] supportedFlavors = service.getSupportedDocFlavors();
		Arrays.asList(supportedFlavors).forEach( flavor -> {
			System.out.println(flavor.getMimeType());
		});
	}
	
	public DocFlavor getDocFlavor() {
//		return DocFlavor.STRING.TEXT_PLAIN;
		return new DocFlavor.INPUT_STREAM("application/octet-stream");
	}

	public void openHandler(String handlerKey) {

	}

}
