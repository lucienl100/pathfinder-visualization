import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public abstract class Pathfinder {
	
	Grid mGrid;
	
	private boolean found;
	HashMap<Node, ArrayList<Node>> adjLists = new HashMap<Node, ArrayList<Node>>();
	HashMap<Node, Node> parent = new HashMap<Node, Node>();
	HashMap<Node, Boolean> visited = new HashMap<Node, Boolean>();
	
	public Pathfinder(Grid grid) {
		mGrid = grid;
		
	}
	public void Start()
	{
		GenerateAdjLists();
		this.AddStartToData(new Node(mGrid.startX, mGrid.startY));
		mGrid.SetState(mGrid.startX, mGrid.startY, 1);
		found = false;
		
		while (!found)
		{
			Step();
			mGrid.repaint();
			try {
			    Thread.sleep((long) (0.03 * 1000));
			} catch (InterruptedException ie) {
			    Thread.currentThread().interrupt();
			}
			
			continue;
		}
		Retrace();
		mGrid.repaint();
	}
	public abstract void AddStartToData(Node n);
	public abstract void Step();
	
	public void Retrace()
	{
		
		ArrayList<Node> path = new ArrayList<Node>();
		Node node = new Node(mGrid.endX, mGrid.endY);
		path.add(node);
		while (parent.containsKey(node))
		{
			path.add(parent.get(node));
			node = parent.get(node);
		}
		Collections.reverse(path);
		if (path.size() != 1)
		{
			for (Node n : path)
			{
				mGrid.SetState(n.getX(), n.getY(), 3);
			}
		}
	}
	public void GenerateAdjLists()
	{
		for (int x = 0; x < mGrid.gridSize; x++)
		{
			for (int y = 0; y < mGrid.gridSize; y++)
			{
				ArrayList<Node> adj = new ArrayList<Node>();
				for (int k = -1; k < 2; k++)
				{
					for (int l = -1; l < 2; l++)
					{
						if (((k == 0 && l != 0) || (k != 0 && l == 0)) && mGrid.InBounds(k+x, l+y) && mGrid.GetState(x+k, y+l) != 4)
						{
							adj.add(new Node(k+x, l+y));
							
						}
					}
				}
				System.out.println(adj);
				adjLists.put(new Node(x, y), adj);
			}
		}
	}
	public void ClearData()
	{
		ClearAdditionalData();
		setFound(false);
		adjLists.clear();
		parent.clear();
		visited.clear();
	}
	public abstract void ClearAdditionalData();
	public boolean getFound() {
		return found;
	}
	public void setFound(boolean found) {
		this.found = found;
	}
}
