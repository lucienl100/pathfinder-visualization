import java.util.*;
public class AStarNode {
	
	public int id;
	public double gScore;
	public double fScore;
	public AStarNode(int id)
	{
        this.id = id;
        gScore = Double.MAX_VALUE;
        fScore = Double.MAX_VALUE;
	}
	public void SetGScore(double g)
	{
		gScore = g;
	}
	public void SetFScore(double f)
	{
		fScore = f;
	}
}
