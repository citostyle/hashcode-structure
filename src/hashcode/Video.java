package hashcode;

import java.util.ArrayList;
import java.util.List;

public class Video {
	public int videoId;
	public int size;
	public List<CacheServer> assignment;
	
	public Video(int videoId, int size) {
		this.videoId = videoId;
		this.size = size;
		this.assignment = new ArrayList<CacheServer>();
	}	
	
	public void addAssignment(CacheServer cacheServer) {
		this.assignment.add(cacheServer);
	}
}
