import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class BFS implements Pathfinder {

	ArrayList<Integer> q;
	Grid mGrid;
	HashMap<Integer, ArrayList<Integer>> adjLists = new HashMap<Integer, ArrayList<Integer>>();
	
	HashMap<Integer, Integer> parent = new HashMap<Integer, Integer>();
	HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
	public boolean found;
	public BFS(Grid grid) {
		
		q = new ArrayList<Integer>();
		mGrid = grid;
		GenerateAdjLists();
	}
	public void Start()
	{
		q.add(200);
		mGrid.SetState(0, 10, 1);
		found = false;
		
		while (!found)
		{
			Step();
			mGrid.repaint();
			try {
			    Thread.sleep((long) (0.01 * 1000));
			} catch (InterruptedException ie) {
			    Thread.currentThread().interrupt();
			}
			
			continue;
		}
		Retrace();
		mGrid.repaint();
	}
	@Override
	public void Step() {
		// TODO Auto-generated method stub
		int c = q.get(0);
		int x = GetX(c);
		int y = GetY(c);
		
		if (x == mGrid.gridSize - 1 && y == mGrid.gridSize - 1)
		{
			found = true;
			return;
		}
		
		ArrayList<Integer> candidates = adjLists.get(c);
		for (Integer to : candidates)
		{
			if (!visited.containsKey(to) && !q.contains(to))
			{
				q.add(to);
				int toX = GetX(to);
				int toY = GetY(to);
				mGrid.SetState(toX, toY, 1);
				parent.put(to, c);
				if (toX == mGrid.gridSize - 1 && toY == mGrid.gridSize - 1)
				{
					found = true;
					return;
				}
			}
		}
		visited.put(c, true);
		q.remove(0);
		mGrid.SetState(x, y, 2);
	}
	public void Retrace()
	{
		ArrayList<Integer> path = new ArrayList<Integer>();
		int node = mGrid.gridSize*mGrid.gridSize-1;
		path.add(node);
		while (parent.containsKey(node))
		{
			path.add(parent.get(node));
			node = parent.get(node);
		}
		Collections.reverse(path);
		int x;
		int y;
		for (Integer n : path)
		{
			x = GetX(n);
			y = GetY(n);
			mGrid.grid[x][y] = 3;
		}
	}
	public void GenerateAdjLists()
	{
		
		
		for (int i = 0; i < mGrid.gridSize; i++)
		{
			for (int j = 0; j < mGrid.gridSize; j++)
			{
				ArrayList<Integer> adj = new ArrayList<Integer>();
				int fr = i * mGrid.gridSize + j;
				for (int k = -1; k < 2; k++)
				{
					for (int l = -1; l < 2; l++)
					{
						
						if (((k == 0 && l != 0) || (k != 0 && l == 0)) && InBounds(k+i, l+j))
						{
							int to = (i+k) * mGrid.gridSize + j + l;
							adj.add(to);
						}
					}
				}
				
				adjLists.put(fr, adj);
			}
		}
	}
	private int GetX(int c)
	{
		return c % mGrid.gridSize;
	}
	private int GetY(int c)
	{
		return c/mGrid.gridSize;
	}
	private boolean InBounds(int x, int y)
	{
		return (x >= 0 && y >= 0 && x < mGrid.gridSize && y < mGrid.gridSize);
	}
}
