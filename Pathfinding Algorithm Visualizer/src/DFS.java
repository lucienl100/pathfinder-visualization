import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class DFS extends Pathfinder{

	ArrayList<Node> st;
	public boolean found;
	public DFS(Grid grid) {
		
		super(grid);
		st = new ArrayList<Node>();
		
	}
	public void AddStartToData(Node n)
	{
		st.add(n);
	}
	public void Step() {
		// TODO Auto-generated method stub
		Node c = st.remove(st.size() - 1);
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
			
			if (!visited.containsKey(to) && !st.contains(to))
			{
				st.add(to);
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
		mGrid.SetState(x, y, 2);
	}
	@Override
	public void ClearAdditionalData()
	{
		st.clear();
	}
}

