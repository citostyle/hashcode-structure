package hashcode;

public class CacheServer {
	// keep it now public for ease
	public int cacheServerId;
	public int maxCapacity;
	
	public CacheServer(int cacheServerId, int maxCapacity) {
		super();
		this.cacheServerId = cacheServerId;
		this.maxCapacity = maxCapacity;
	}
}
