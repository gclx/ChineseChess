/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * TODO 
 * @author gclx
 * @version 1.0
 * @date 2018年12月12日 下午8:44:31
 * @copyright 
 * @remarks TODO
 *
 */
public class ChessBoardGUI extends JPanel{
	
	private static final long serialVersionUID = 1L;

	private Image image;
	
	private ImageIcon icon;
	
	public ChessBoardGUI() {
		// TODO Auto-generated constructor stub
		icon = new ImageIcon("img/qipan.png");
		image = icon.getImage();
		this.setLayout(null);
		setSize(630, 700);
		setVisible(true);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(image, 0, 0, 630, 680, this);
	}
	
	public static void main(String[] args) {
		JFrame jFrame = new JFrame("asdfasdf");
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLayout(null);
		jFrame.setBounds(400, 400, 400, 400);
		ChessBoardGUI board = new ChessBoardGUI();
		Icon img = new ImageIcon("img/Bche.png");
		JLabel label = new JLabel();
		label.setIcon(img);
		label.setBounds(32, 33, 50, 50);
		label.setVisible(true);
//		ChessPiecesGUI pieces = new ChessPiecesGUI(null, board, 0, 0);
//		pieces.setAttribute("黑车", Color.black, img, 0, 0);
//		pieces.setBounds(32, 33, 50, 50);
//		board.add(pieces);
//		JLabel label = new JLabel("asdfffffffffffffffff");
//		label.setBounds(100, 100, 100, 100);
		board.add(label);
		board.setVisible(true);
		jFrame.add(board);
		jFrame.setVisible(true);
	}
}
