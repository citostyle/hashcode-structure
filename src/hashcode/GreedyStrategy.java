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
	//	System.out.println("done");


		boolean videoPlaced = true;
		int runs = 0;
		while (videoPlaced){
			runs+=1;
			if(runs%10==0) {
				//System.out.println(runs);
			}
			videoPlaced = false;
			for(CacheServer cacheServer: model.cacheServers) {
				//System.out.println("cacheServer");
				Video bestVideo = null;
				double bestVideosScore = Double.MIN_VALUE;
				for (Video video : model.videos) {
					if(!cacheServer.assignment.contains(video)) {
						//System.out.println("videos");
						double score = 0;
						if (video.size <= cacheServer.remainingCapacity) {
							for (Endpoint endpoint : cacheServer.endpoints) {
								if (endpoint.requests.get(video) != null) {
									score += endpoint.requests.get(video) * Math.max(0, (model.getMinimumDelay(endpoint, video) - endpoint.cacheLatencies.get(cacheServer)));



/*
									System.out.println("Server " + cacheServer.cacheServerId);
									System.out.println("Video " + video.videoId);
									System.out.println("Improvement " + Math.max(0, model.getMinimumDelay(endpoint, video)));
									System.out.println("Score " + score);
									System.out.println();*/


								}
							}
						}
						score /= video.size;
						if (score > bestVideosScore) {
							bestVideosScore = score;
							bestVideo = video;
							videoPlaced = true;
						}
					}
				}
				if(bestVideo!=null) {
				//	System.out.println("Adding Video " + bestVideo.videoId + " to Server " + cacheServer.cacheServerId);
				//	System.out.println("This server has " + cacheServer.remainingCapacity + " capacity left before");
					if(!cacheServer.addVideo(bestVideo)){
						System.out.println("Problem");
					}
				//	System.out.println("This server has " + cacheServer.remainingCapacity + " capacity left after");
				}
			}
		}
		model.printOutput();
	}
}
