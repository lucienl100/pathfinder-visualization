import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter {
	
	private Grid grid;
	
	public MouseListener(Grid ingrid)
	{
		grid = ingrid;
	}
	@Override
	public void mousePressed(MouseEvent e)
	{
		grid.SearchBox(e.getX(), e.getY());
		grid.repaint();
	}
}
