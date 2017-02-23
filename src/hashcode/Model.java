package hashcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Model {
	List<Video> videos;
	List<CacheServer> cacheServers;
	List<Endpoint> endpoints;
	
	
	
	//example code of how to read model parameters from files 
	public static Model createModelFromFile(String filename) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(filename));
		
		int rows = scanner.nextInt();
		int columns = scanner.nextInt();
		
		Model model = new Model();		
		
		int size;
		int capacity;
		while(scanner.hasNextLine() && rows > 0) {
			size = scanner.nextInt();
			capacity = scanner.nextInt();
			//dc.addServer(size, capacity);
			rows--;
		}
		
		scanner.close();
		return model;		
	}

}
