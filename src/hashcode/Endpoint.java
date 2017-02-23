package hashcode;

import java.util.HashMap;
import java.util.Map;

public class Endpoint {
	// keep it now public for ease
	public int endpointId;
	public int latencyDataCenter;
	public Map<CacheServer, Integer> cacheLatencies;
	public Map<Video, Integer> requests;
	
	public Map<Video, Integer> videoScores;
	
	public Endpoint(int endpointId, int latencyDataCenter) {
		this.endpointId = endpointId;
		this.latencyDataCenter = latencyDataCenter;
		this.cacheLatencies = new HashMap<CacheServer, Integer>();
		this.requests = new HashMap<Video, Integer>();
		this.videoScores = new HashMap<Video, Integer>();
	}
	
	public void assignCacheServer(CacheServer cacheServer, int latency) {
		this.cacheLatencies.put(cacheServer, latency);
		// add endpoint to cacheserver
		cacheServer.assignEndpoint(this);
	}
	
	public void addVideoRequest(Video video, int numberOfRequests) {
		this.requests.put(video, numberOfRequests);
		this.videoScores.put(video, (int)(numberOfRequests / video.size));
	}
}
