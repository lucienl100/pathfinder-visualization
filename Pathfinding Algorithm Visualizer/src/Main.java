import javax.swing.JFrame;

public class Main {
	
	public static int width = 640;
	public static int height = 640;
	static Grid grid;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame("Title");
		grid = new Grid(width, height);
		f.add(grid);
		f.setSize(640, 640);
		f.setVisible(true);
		BFS bfs = new BFS(grid);
		bfs.Start();
	}

}
