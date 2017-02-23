package hashcode;

import java.util.List;

public class CacheServer {
	// keep it now public for ease
	public int cacheServerId;
	public int maxCapacity;
	public List<Video> assignment;
	public int remainingCapacity;
	public List<Endpoint> endpoints;

	public CacheServer(int cacheServerId, int maxCapacity) {
		this.cacheServerId = cacheServerId;
		this.maxCapacity = maxCapacity;
		this.remainingCapacity = maxCapacity;
	}
	
	
	// boolean probably not ideal, but he have consensus on that for now
	public boolean addVideo(Video video) {
		if(this.remainingCapacity - video.size < 0) {
			// no capacity for this video anymore
			return false;
		}
		
		this.assignment.add(video);
		this.remainingCapacity -= video.size;
		
		//this is stored in both video entity and 
		video.addAssignment(this);
		
		return true;
	}

	public void assignEndpoint(Endpoint endpoint) {
		this.endpoints.add(endpoint);
	}
}
