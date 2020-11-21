import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

public class Grid extends JPanel {
	public int width;
	public int height;
	
	public int gridSize = 20;
	public int boxSize;
	
	public int endX;
	public int startX = 0;
	public int[][] grid;
	public Grid(int w, int h)
	{
		width = w;
		height = h;
		boxSize = (width*3/4-40)/gridSize;
		grid = new int[gridSize][gridSize];
		endX = gridSize - 1;
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				grid[i][j] = 0;
			}
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				if (grid[i][j] == 1)
				{
					g.setColor(Color.GREEN);
					g.fillRect(i*boxSize+width*1/4, j*boxSize+10, boxSize, boxSize);
				}
				else if (grid[i][j] == 2)
				{
					g.setColor(Color.GRAY);
					g.fillRect(i*boxSize+width*1/4, j*boxSize+10, boxSize, boxSize);
				}
				else if (grid[i][j] == 3)
				{
					g.setColor(Color.YELLOW);
					g.fillRect(i*boxSize+width*1/4, j*boxSize+10, boxSize, boxSize);
					
				}
				g.setColor(Color.BLACK);
				g.drawRect(i*boxSize+width*1/4, j*boxSize+10, boxSize, boxSize);
			}
		}
	}
	public void SetState(int y, int x, int state) {
		grid[y][x] = state;
	}
	public int[][] GetGrid()
	{
		return grid;
	}
}
