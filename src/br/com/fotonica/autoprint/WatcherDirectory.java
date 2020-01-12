package br.com.fotonica.autoprint;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class WatcherDirectory {

	private String uri;

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
			System.err.println("change");
			for (WatchEvent<?> event : key.pollEvents()) {
				System.out.println("Event kind:" + event.kind() + ". File affected: " + event.context() + ".");

				if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
					print.exec(String.format("%s/%s", uri, event.context()));
				}
			}
			key.reset();
		}

	}

}
