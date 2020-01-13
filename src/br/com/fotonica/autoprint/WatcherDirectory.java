package br.com.fotonica.autoprint;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Map;

public class WatcherDirectory {

	private String uri;
	
	private static final Map<String, Integer> countPlot = new HashMap<String, Integer>();
	
	public void addPlotRegister(String key) {
		if(countPlot.get(key) != null) {
			countPlot.put(key, countPlot.get(key) + 1);
		}
		else {
			countPlot.put(key, 1);
		}
	}

	public WatcherDirectory(String uri) {
		this.uri = uri;
	}

	public void exec() throws IOException, InterruptedException {
		Print print = new Print();

		WatchService watchService = FileSystems.getDefault().newWatchService();
		Path path = Paths.get(uri);

		WatchKey watchKey = path.register(watchService, new WatchEvent.Kind[] { StandardWatchEventKinds.ENTRY_CREATE });

		WatchKey key = null;

		System.out.println("AutoPrint watching " + uri);
		while ((key = watchService.take()) != null) {
			for (WatchEvent<?> event : key.pollEvents()) {
				if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
					System.out.println(String.format("%s/%s", uri, event.context()));
					String filename = String.format("%s/%s", uri, event.context());
					addPlotRegister(filename);
					
					for(Map.Entry<String, Integer> entry: countPlot.entrySet()) {
						System.out.println(String.format("%s %d", entry.getKey(), entry.getValue()));
					}
					
					if(countPlot.get(filename)==1) {
						print.exec(String.format("%s/%s", uri, event.context()));
					}
					else {
						countPlot.remove(filename);
					}
					
				}
			}
			key.reset();
		}

	}

}
