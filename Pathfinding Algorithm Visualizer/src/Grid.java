import javax.swing.JPanel;
import java.math.*;
import java.awt.Color;
import java.awt.Graphics;

public class Grid extends JPanel {
	private int width = 1080;
	private int height = 720;
	
	public int gridSize = 20;
	public int boxSize;
	
	public int startX = 0;
	public int startY = 19;
	
	public int endX = 19;
	public int endY = 0;
	
	public int[][] grid;
	
	public Grid()
	{
		boxSize = (width*3/5-40)/gridSize;
		grid = new int[gridSize][gridSize];
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
					g.fillRect(i*boxSize+width*2/5, j*boxSize+10, boxSize, boxSize);
				}
				else if (grid[i][j] == 2)
				{
					g.setColor(Color.GRAY);
					g.fillRect(i*boxSize+width*2/5, j*boxSize+10, boxSize, boxSize);
				}
				else if (grid[i][j] == 3)
				{
					g.setColor(Color.YELLOW);
					g.fillRect(i*boxSize+width*2/5, j*boxSize+10, boxSize, boxSize);
				}
				else if (grid[i][j] == 4)
				{
					g.setColor(Color.BLACK);
					g.fillRect(i*boxSize+width*2/5, j*boxSize+10, boxSize, boxSize);
				}
				g.setColor(Color.BLACK);
				g.drawRect(i*boxSize+width*2/5, j*boxSize+10, boxSize, boxSize);
			}
		}
		g.setColor(Color.CYAN);
		g.fillRect(startX*boxSize+width*2/5, startY*boxSize+10, (int)(boxSize), (int)(boxSize));
		
		g.setColor(Color.BLUE);
		g.fillOval(endX*boxSize+width*2/5, endY*boxSize+10, (int)(boxSize), (int)(boxSize));
	}
	public void SetState(int x, int y, int state) {
		grid[x][y] = state;
	}
	public int GetState(int x, int y)
	{
		return grid[x][y];
	}
	public int[][] GetGrid()
	{
		return grid;
	}
	public void ClearGrid()
	{
		boxSize = (width*3/5-40)/gridSize;
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				if (grid[i][j] != 4)
				{
					grid[i][j] = 0;
				}
			}
		}
	}
	private boolean InBounds(int x, int y)
	{
		return (x >= 0 && y >= 0 && x < gridSize && y < gridSize);
	}
	public void SearchBox(int x, int y)
	{
		int adjustedX = (int)Math.round(((double)x - width*2/5 - 23) / boxSize) ;
		int adjustedY = (int)Math.round(((double)y - 55)/ boxSize);
		if (InBounds(adjustedX, adjustedY))
		{
			SetState(adjustedX, adjustedY, 4);
		}
	}
}
