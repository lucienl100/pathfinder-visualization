import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.*;
public class Main {
	static Grid grid;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame("Title");
		
		grid = new Grid();
		MouseListener mouseListener = new MouseListener(grid);
		f.addMouseListener(mouseListener);
		f.add(grid);
		f.setSize(1080, 720);
		f.setVisible(true);
		JButton startB = new JButton("Start");  
		startB.setBounds(100, 120, 140, 80);
		f.setLayout(null);
		f.add(startB);
		BFS bfs = new BFS(grid);
		
        startB.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                	
                    @Override
                    public Void doInBackground() {
                        startB.setText("Working");
                        grid.ClearGrid();
                        bfs.ClearData();
                        bfs.Start();
                        return null;
                    }

                    @Override
                    protected void done() {
                        // get() would be available here if you want to use it
                        startB.setText("Done");
                    }
                   
                };
                worker.execute();
            }
        });
	}
}
