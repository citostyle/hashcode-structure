package hashcode;

import java.util.List;

public class Video {
	public int videoId;
	public int size;
	public List<CacheServer> assignment;
	
	public Video(int videoId, int size) {
		super();
		this.videoId = videoId;
		this.size = size;
	}	
	
	public void addAssignment(CacheServer cacheServer) {
		this.assignment.add(cacheServer);
	}
}
