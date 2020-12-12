/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.Transparency;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;

/**
 * TODO 
 * @author gclx
 * @version 1.0
 * @date 2018年12月12日 下午9:17:56
 * @copyright 
 * @remarks TODO
 *
 */
public class ChessPiecesGUI extends JLabel implements MouseListener{

	private static final long serialVersionUID = 1L;

	private boolean isPiece;
	
	private Color color;
	
	private String name;
	
	private Icon img;
	
	private MainView view;
	
	private ChessBoardGUI boardGUI;
	
	public int x, y;

	/**
	 * @param mainView
	 * @param board
	 * @param x
	 * @param y
	 */
	public ChessPiecesGUI(MainView mainView, ChessBoardGUI board, int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		setPiece(false);
		setSize(50, 50);
		this.addMouseListener(this);
		setView(mainView);
		setBoardGUI(board);
	}
	
	public void setAttribute(String name, Color color, Icon img, int x, int y){
		setIcon(img);
		setColor(color);
		setName(name);
		this.x = x;
		this.y = y;
		setPiece(true);
		setBackground(Color.red);
	}

	public void removeListener(){
		this.removeMouseListener(this);
	}

	public boolean isPiece() {
		return isPiece;
	}

	public void setPiece(boolean isPiece) {
		this.isPiece = isPiece;
	}

	public int getx(){
		return this.x;
	}
	
	public int gety(){
		return this.y;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Icon getImg() {
		return img;
	}

	public void setImg(Icon img) {
		this.img = img;
	}

	public MainView getView() {
		return view;
	}

	public void setView(MainView view) {
		this.view = view;
	}

	public ChessBoardGUI getBoardGUI() {
		return boardGUI;
	}

	public void setBoardGUI(ChessBoardGUI boardGUI) {
		this.boardGUI = boardGUI;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (view.isRedTurn()){
			
			if (view.getFrom() == null && isPiece && color.equals(Color.red)){
				view.setFrom(this);
				setBorder(BorderFactory.createLineBorder(Color.red));
			} else if (view.getFrom() != null && this.equals(view.getFrom())){
				view.setFrom(null);
				setBorder(null);
			} else if (view.getFrom() != null && isPiece && color.equals(Color.red)){
				view.getFrom().setBorder(null);
				view.setFrom(this);
				setBorder(BorderFactory.createLineBorder(Color.red));
			} else if (view.getFrom() != null && !this.equals(view.getFrom())){
				System.out.println("准备走棋");
				view.setTo(this);
				if (view.isLegal(view.getFrom(), view.getTo())){
					view.move(view.getFrom(), view.getTo());
					System.out.println("已转换行棋方");
				} else {
					System.out.println("步法不合法");
					view.setTo(null);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
