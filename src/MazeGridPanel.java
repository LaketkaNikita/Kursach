

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MazeGridPanel extends JPanel {

	private static final long serialVersionUID = 7237062514425122227L;
	private final List<Cell> grid = new ArrayList<Cell>();
	private List<Cell> currentCells = new ArrayList<Cell>();

	public MazeGridPanel(int rows, int cols) {
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < cols; y++) {
				grid.add(new Cell(x, y));
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(Maze.WIDTH + 1, Maze.HEIGHT + 1);
	}

	public void generate(int index) {
		switch (index) {
		case 0:
			new KruskalsGen(grid, this);;
			break;
		case 1:
			new PrimsGen(grid, this);
			break;
		default:
			new PrimsGen(grid, this);
			break;
		}
	}

	public void solve(int index) {
		switch (index) {

		case 0:
			new BFSSolve(grid, this);
			break;
		case 1:
			new DijkstraSolve(grid, this);
			break;
		default:
			new DijkstraSolve(grid, this);
			break;
		}
	}
	
	public void resetSolution() {
		for (Cell c : grid) {
			c.setDeadEnd(false);
			c.setPath(false);
			c.setDistance(-1);
			c.setParent(null);
		}
		repaint();
	}
	
	public void setCurrent(Cell current) {
		if(currentCells.size() == 0) {
			currentCells.add(current);
		} else {
			currentCells.set(0, current);			
		}
	}
	
	public void setCurrentCells(List<Cell> currentCells) {
		this.currentCells = currentCells;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Cell c : grid) {
			c.draw(g);
		}
		for (Cell c : currentCells) {
			if(c != null) c.displayAsColor(g, Color.ORANGE);
		}
		grid.get(0).displayAsColor(g, Color.GREEN); // start cell
		grid.get(grid.size() - 1).displayAsColor(g, Color.YELLOW); // goal cell
	}
}
