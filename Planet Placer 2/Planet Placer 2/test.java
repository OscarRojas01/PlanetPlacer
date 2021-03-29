

public class test {
	
	public static final long AU = 149597870700l;
	public static final double G  = 667.430;
	
	public static IntroductionMenu intro;
	public static GameView newGame;

	public static void main(String[] args) {
		intro = new IntroductionMenu();
	}
	public static void runNewGame() {
		newGame = new GameView();
	}
}
