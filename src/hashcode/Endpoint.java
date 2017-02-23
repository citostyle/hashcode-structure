package hashcode;

import java.util.Map;

public class Endpoint {
	// keep it now public for ease
	public int endpointId;
	public int latencyDataCenter;
	public Map<CacheServer, Integer> cacheLatencies;
	public Map<Video, Integer> requests;
	
	public Endpoint(int endpointId, int latencyDataCenter) {
		this.endpointId = endpointId;
		this.latencyDataCenter = latencyDataCenter;
	}
	
	public void assignCacheServer(CacheServer cacheServer, int latency) {
		this.cacheLatencies.put(cacheServer, latency);
	}
	
	public void addVideoRequest(Video video, int numberOfReqeusts) {
		this.requests.put(video, numberOfReqeusts);
	}
}
