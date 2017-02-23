package hashcode;

import java.io.FileNotFoundException;
import java.util.PriorityQueue;

/**
 * Created by Patrick on 23.02.17.
 */
public class GreedyStrategy {


	public static void main(String[] args) throws FileNotFoundException {
		if(args.length == 0) {
			System.out.println("Provide an input file for us to solve");
		}
		String filename = args[0];
		Model model = Model.createModelFromFile(filename);
		System.out.println("done");


		boolean videoPlaced = true;
		while (videoPlaced){
			videoPlaced = false;
			for(CacheServer cacheServer: model.cacheServers) {
				Video bestVideo = null;
				double bestVideosScore = Double.MAX_VALUE;
				for (Video video : model.videos) {
					double score = 0;
					if (video.size <= cacheServer.remainingCapacity){
						for(Endpoint endpoint : cacheServer.connectedEndpoints){
							score += endpoint.requests * Math.max(0,model.getCurrentDelay(video,endpoint));
						}
					}
					score/=video.size;
				}
			}
		}






	}



}
