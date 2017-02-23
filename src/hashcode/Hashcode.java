package hashcode;

import java.io.FileNotFoundException;

public class Hashcode {

	public static void main(String[] args) throws FileNotFoundException {
		if(args.length == 0) {
			System.out.println("Provide an input file for us to solve");
		}
		String filename = args[0];
		Model model = Model.createModelFromFile(filename);
		System.out.println("done");
	}

}
