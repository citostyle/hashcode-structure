package hashcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Model {
	List<Video> videos;
	List<CacheServer> cacheServers;
	List<Endpoint> endpoints;
	
	
	public int getTotalScore() {

        for (Endpoint e: endpoints) {
            for (Map.Entry<Video, Integer> videoRequest : e.requests.entrySet()) { // each endpoint
                Video currentVideo = videoRequest.getKey();
                Integer currentVideoRequests = videoRequest.get
                List<Integer> latencies;
                latencies.add(e.latencyDataCenter);
                for(Map.Entry<CacheServer, Integer> cacheLatencies: e.cacheLatencies.entrySet()) { // each cache connected to endpoint
                    CacheServer cache = cache.getKey();
                    Integer latencyCache = cache.getValue();
                    for(Video cacheVideo: cache.assignment) { // videos assignes to that cache
                        if(cacheVideo.videoId == videoRequest.getKey()) {// if the cache has the video
                            latencies.add(latencyCache);
                            break;
                        }
                    }
                }
                System.out.println(entry.getKey() + "/" + entry.getValue());
            }
        }
		// for each endpoint
            // for each video that has this video
                //for each cache that has the video to that endpoint
                // L = min(LD, latencies caches)
                // Requests x (LD-L)

            //Total is the (sum(time saved individual request descriptions * 1000) / total number of requests in all requests //rounding down

	}
	
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
