import java.io.FileNotFoundException;

public class Hashcode {

	//comment added by patrick to test git syncing
	public static void main(String[] args) throws FileNotFoundException {
		String filename = args[0];
		Model model = Model.createModelFromFile(filename);
	}

}
