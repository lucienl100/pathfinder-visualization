import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class BFS extends Pathfinder{

	ArrayList<Node> q;
	public BFS(Grid grid) 
	{
		super(grid);
		q = new ArrayList<Node>();
	}
	public void AddStartToData(Node n)
	{
		q.add(n);
	}
	public void Step() {
		// TODO Auto-generated method stub
		Node c = q.get(0);
		int x = c.getX();
		int y = c.getY();
		
		if (x == mGrid.endX && y == mGrid.endY)
		{
			setFound(true);
			return;
		}
		
		ArrayList<Node> candidates = adjLists.get(c);
		for (Node to : candidates)
		{
			if (!visited.containsKey(to) && !q.contains(to))
			{
				q.add(to);
				int toX = to.getX();
				int toY = to.getY();
				mGrid.SetState(toX, toY, 1);
				parent.put(to, c);
				if (toX == mGrid.endX && toY == mGrid.endY)
				{
					setFound(true);
					return;
				}
			}
		}
		visited.put(c, true);
		q.remove(0);
		mGrid.SetState(x, y, 2);
	}
	@Override
	public void ClearAdditionalData()
	{
		q.clear();
	}
}
