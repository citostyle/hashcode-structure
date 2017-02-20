package hashcode;

import java.io.FileNotFoundException;

public class Hashcode {

	public static void main(String[] args) throws FileNotFoundException {
		String filename = args[0];
		Model model = Model.createModelFromFile(filename);
	}

}
