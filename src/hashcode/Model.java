package hashcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Collections;

public class Model {
	List<Video> videos;
	List<CacheServer> cacheServers;
	List<Endpoint> endpoints;

	public Model() {
		this.videos = new ArrayList<Video>();
		this.cacheServers = new ArrayList<CacheServer>();
		this.endpoints = new ArrayList<Endpoint>();
	}

	public int getMinimumDelay(Endpoint endpoint, Video video) {
		List<Integer> allDelays = new ArrayList<Integer>();
		allDelays.add(endpoint.latencyDataCenter);
		
		for(Map.Entry<CacheServer, Integer> entry : endpoint.cacheLatencies.entrySet()) {
			if(entry.getKey().assignment.contains(video)) {
				allDelays.add(entry.getValue());
			}
		}
		return Collections.min(allDelays);
	}

	public void printOutput() {
	    Integer totalCaches = 0;
	    for(CacheServer cache : this.cacheServers) {
            if(!cache.assignment.isEmpty()) {
                ++totalCaches;
            }
        }
        System.out.println(totalCaches);
        for(CacheServer cache : this.cacheServers) {
            if(!cache.assignment.isEmpty()) {
                System.out.printf("%d", cache.cacheServerId);
                for(Video v : cache.assignment) {
                    System.out.printf(" %d", v.videoId);
                }
                System.out.println();
            }
        }
        
    }

    public double getTotalScore() {

	    Integer sum = 0;
	    Integer totalNumRequests = 0;
        for (Endpoint e: this.endpoints) {
            for (Map.Entry<Video, Integer> videoRequest : e.requests.entrySet()) { // each endpoint
                Video currentVideo = videoRequest.getKey();
                Integer currentVideoRequests = videoRequest.getValue();

                totalNumRequests += currentVideoRequests;
                int minLatency = this.getMinimumDelay(e, currentVideo);
                
                Integer requestTimeSaved = currentVideoRequests * (e.latencyDataCenter - minLatency);
                sum += requestTimeSaved;
            }
        }
        double total = Math.floor((sum * 1000) / totalNumRequests);
        return total;
    }	
	
    /*public double getTotalScore() {

	    Integer sum = 0;
	    Integer TotalNumRequests = 0;
        for (Endpoint e: this.endpoints) {
            for (Map.Entry<Video, Integer> videoRequest : e.requests.entrySet()) { // each endpoint
                Video currentVideo = videoRequest.getKey();
                Integer currentVideoRequests = videoRequest.getValue();

                TotalNumRequests += currentVideoRequests;
                List<Integer> latencies = new ArrayList<Integer>();
                latencies.add(e.latencyDataCenter);
                for(Map.Entry<CacheServer, Integer> cacheLatencies: e.cacheLatencies.entrySet()) { // each cache connected to endpoint
                    CacheServer cache = cacheLatencies.getKey();
                    Integer latencyCache = cacheLatencies.getValue();

                    for(Video cacheVideo: cache.assignment) { // videos assigns to that cache
                        if(cacheVideo.videoId == currentVideo.videoId) {// if the cache has the video
                            latencies.add(latencyCache);
                            break;
                        }
                    }
                }
                Integer minLatency = Collections.min(latencies);
                Integer requestTimeSaved = currentVideoRequests * (e.latencyDataCenter - minLatency);
                sum += requestTimeSaved;
            }
        }
        double total = sum * 1000 / TotalNumRequests;
        return total;
    }*/



        //example code of how to read model parameters from files
	public static Model createModelFromFile(String filename) throws FileNotFoundException, NoSuchElementException {
		Scanner scanner = new Scanner(new File(filename));
		
		int videos = scanner.nextInt();
		int endpoints = scanner.nextInt();
		int requests = scanner.nextInt();
		int caches = scanner.nextInt();
		int cacheCapacity = scanner.nextInt();
		
		Model model = new Model();		
		
		// create caches
		for(int i=0; i < caches; i++) {
			model.cacheServers.add(new CacheServer(i, cacheCapacity));
		}
		
		//read videos + sizes, we assume that everything goes right
		int size = 0;
		for(int i=0; i < videos; i++) {
			size = scanner.nextInt();
			model.videos.add(new Video(i, size));
		}
		
		//read all endpoints
		Endpoint currentEndpoint;
		int endpointCacheId;
		int latencyDataCenter;
		int endpointCacheLatency;
		for(int i = 0; i < endpoints; i++) {
			latencyDataCenter = scanner.nextInt();
			currentEndpoint = new Endpoint(i, latencyDataCenter);
			model.endpoints.add(currentEndpoint);
			int endpointCaches = scanner.nextInt();
			// read all caches assigned to endpoints
			for(int j = 0; j < endpointCaches; j++) {
				endpointCacheId = scanner.nextInt();
				endpointCacheLatency = scanner.nextInt();
				currentEndpoint.assignCacheServer(model.cacheServers.get(endpointCacheId), endpointCacheLatency);
			}
		}
		
		//read all requests
		int requestVideoId;
		int requestEndpointId;
		int numberOfRequests;
		for(int i=0; i < requests; i++) {
			requestVideoId = scanner.nextInt();
			requestEndpointId = scanner.nextInt();
			numberOfRequests = scanner.nextInt();
			
			model.endpoints.get(requestEndpointId).addVideoRequest(
					model.videos.get(requestVideoId), numberOfRequests);
		}
		
		scanner.close();
		return model;		
	}

}
