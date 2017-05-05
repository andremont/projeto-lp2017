import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		try {
			ArrayList<Card> cards = Utils.getAllCards();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
