
public class Node {
	int MAX_GRID_SIZE = 100; //Need to change this into a static variable in grid;
	private int x;
	private int y;
	public Node(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	@Override
	public boolean equals(Object o)
	{
		if (o.getClass() == this.getClass())
		{
			return ((Node)o).getX() == x && ((Node)o).getY() == y;
		}
		return false;
	}
	@Override
	public int hashCode()
	{
		return this.x * MAX_GRID_SIZE + this.y;
	}
}
