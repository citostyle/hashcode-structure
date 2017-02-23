package hashcode;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GreedyEndpointStrategy {
	public static void main(String[] args) throws FileNotFoundException {
		if(args.length == 0) {
			System.out.println("Provide an input file for us to solve");
			System.exit(-1);
		}
		String filename = args[0];
		Model model = Model.createModelFromFile(filename);
		assign(model);
		
		System.out.println(model.getTotalScore());
		model.printOutput();
	}
	
	public static void assign(Model model) {
		for(Endpoint endpoint : model.endpoints) {
			Map<Video, Integer> sortedVideos = sortByValue(endpoint.requests, true);
			//Map<Video, Integer> sortedVideos = sortByValue(endpoint.videoScores, true);
			Map<CacheServer, Integer> sortedCaches = sortByValue(endpoint.cacheLatencies);
			for(Map.Entry<Video, Integer> videoEntry: sortedVideos.entrySet()) {
				for(Map.Entry<CacheServer, Integer> cacheEntry : sortedCaches.entrySet()) {
					CacheServer cacheServer = cacheEntry.getKey();
					boolean success = cacheServer.addVideo(videoEntry.getKey());
					if(success) {
						//System.out.println(videoEntry.getKey().size);
						break;
					}
				}
			}
		}
	}
	
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map) {
		return sortByValue(map, false);
	}
	
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map, boolean descended )
	{
	    List<Map.Entry<K, V>> list = new LinkedList<>( map.entrySet() );
	    Collections.sort( list, new Comparator<Map.Entry<K, V>>() {
	        @Override
	        public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 ) {
	        	int multiplicator = 1;
	        	if(descended) {
	        		multiplicator = -1;
	        	}
	            return ( o1.getValue() ).compareTo( o2.getValue() ) * multiplicator;
	        }
	    } );
	
	    Map<K, V> result = new LinkedHashMap<>();
	    for (Map.Entry<K, V> entry : list) {
	        result.put( entry.getKey(), entry.getValue() );
	    }
	    return result;
	}	
}
