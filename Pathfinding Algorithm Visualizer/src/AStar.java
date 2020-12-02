import java.util.ArrayList;
import java.util.*;
import java.lang.Math;
public class AStar{
	
	int i = 0;
	Grid mGrid;
	HashMap<Integer, ArrayList<Integer>> adjLists = new HashMap<Integer, ArrayList<Integer>>();
	
	HashMap<Integer, Integer> parent = new HashMap<Integer, Integer>();
	HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
	HashMap<Integer, AStarNode> map = new HashMap<Integer, AStarNode>();
	
	ArrayList<Integer> inQueue = new ArrayList<Integer>();
	PriorityQueue<AStarNode> pQueue = new PriorityQueue<>(new Comparator<AStarNode>() {
	    @Override
	    public int compare(AStarNode o1, AStarNode o2) {
	    	if (o1.fScore < o2.fScore)
	    	{
	    		return -1;
	    	}
	    	else if (o1.fScore > o2.fScore)
	    	{
	    		return 1;
	    	}
	    	return 0;
	    }
	});
	
	public boolean found;
	public AStar(Grid grid) {
		mGrid = grid;
	}
	public double DistanceHeuristic(int x, int y)
	{
		return 2*Math.sqrt(Math.pow(mGrid.endX - x, 2) + Math.pow(mGrid.endY - y, 2));
	}
	public void Start()
	{
		GenerateAdjLists();
		AStarNode node = new AStarNode(mGrid.startY * mGrid.gridSize + mGrid.startX);
		node.SetGScore(0);
		node.SetFScore(DistanceHeuristic(mGrid.startX, mGrid.startY));
		pQueue.add(node);
		
		mGrid.SetState(mGrid.startX, mGrid.startY, 1);
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
	public void Step() {
		// TODO Auto-generated method stub
		AStarNode c = pQueue.remove();
		map.put(c.id, c);
		visited.put(c.id, true);
		int x = GetX(c.id);
		int y = GetY(c.id);
		i++;
		if (x == mGrid.endX && y == mGrid.endY)
		{
			found = true;
			return;
		}
		ArrayList<Integer> candidates = adjLists.get(c.id);
		for (Integer to : candidates)
		{
			int toX = GetX(to);
			int toY = GetY(to);
			
			
			if (!visited.containsKey(to))
			{
				System.out.println(to);
				double newGScore = c.gScore + 1;
				
				AStarNode node;
				if (map.containsKey(to))
				{
					node = map.get(to);
					if (newGScore < node.gScore)
					{
						node.SetGScore(newGScore);
						node.SetFScore(newGScore + DistanceHeuristic(toX, toY));
						parent.put(to, c.id);
						if (!inQueue.contains(to))
						{
							inQueue.add(to);
							pQueue.add(node);
							parent.put(to, c.id);
							mGrid.SetState(toX, toY, 1);
						}
					}
				}
				else
				{
					node = new AStarNode(to);
					node.SetGScore(newGScore);
					node.SetFScore(newGScore + DistanceHeuristic(toX, toY));
					map.put(to, node);
					parent.put(to, c.id);
					if (!inQueue.contains(to))
					{
						inQueue.add(to);
						pQueue.add(node);
						parent.put(to, c.id);
						mGrid.SetState(toX, toY, 1);
					}
				}
				if (toX == mGrid.endX && toY == mGrid.endY)
				{
					found = true;
					return;
				}
			}
		}
		mGrid.SetState(x, y, 2);
	}
	public void Retrace()
	{
		
		ArrayList<Integer> path = new ArrayList<Integer>();
		int node = mGrid.endY * mGrid.gridSize + mGrid.endX;
		path.add(node);
		while (parent.containsKey(node))
		{
			path.add(parent.get(node));
			node = parent.get(node);
		}
		Collections.reverse(path);
		int x;
		int y;
		System.out.println(path);
		if (path.size() != 1)
		{
			for (Integer n : path)
			{
				x = GetX(n);
				y = GetY(n);
				mGrid.grid[x][y] = 3;
			}
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
						if (((k == 0 && l != 0) || (k != 0 && l == 0)) && InBounds(k+i, l+j) && mGrid.GetState(j+l, i+k) != 4)
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
		return c / mGrid.gridSize;
	}
	private boolean InBounds(int x, int y)
	{
		return (x >= 0 && y >= 0 && x < mGrid.gridSize && y < mGrid.gridSize);
	}
}

