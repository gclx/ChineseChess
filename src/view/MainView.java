/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.Stack;

import javax.crypto.spec.OAEPParameterSpec;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.plaf.metal.MetalIconFactory.TreeControlIcon;

import AI.ChessEngine.Level;
import board.ChessBoard;
import step.Step;

/**
 * TODO 
 * @author gclx
 * @version 1.0
 * @date 2018��12��12�� ����8:38:04
 * @copyright 
 * @remarks TODO
 *
 */
public class MainView extends JFrame{
	
	private static final long serialVersionUID = 1L;

	private ChessBoardGUI board;
	
	private ChessPiecesGUI pieces[][];
	
	private ChessPiecesGUI from, to, last1, last2;
	
	private boolean isRedTurn;
	
	private Step step;
	
	private ChessBoard chessBoard;
	
	private Menu menu;
	
	private Stack<Path> track; 
	
	private Container container;
	
	public MainView() {
		// TODO Auto-generated constructor stub
		super("�й�����");
		this.getContentPane().setLayout(null);
		container = getContentPane();
		container.setLayout(null);
		container.setBackground(new Color(205, 133, 63));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(750, 720);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		setLocation((screenSize.width - 750) / 2, (screenSize.height - 720) / 2);
//		setBackground(new Color(205, 133, 63));
		
		board = new ChessBoardGUI();
		menu = new Menu(this);
		
		from = null;
		to = null;
		last1 = null;
		last2 = null;
		
		add(board);
		add(menu);
		menu.setBounds(635, 0, 100, 720);
		
	}
	
	/**
	 * ��ʼ����������
	 */
	public void initial(){
		//track = new Stack<Path>();	//�����ã��Ȳ�д��
		pieces = new ChessPiecesGUI[11][10];
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 9; j++){
				pieces[i][j] = new ChessPiecesGUI(this, board, i, j);
			}
		}
		
		Icon img;
		
		// �ڳ�
		img = new ImageIcon("img/Bche.png");
		pieces[0][0].setAttribute("�ڳ�", Color.black, img, 0, 0);
		pieces[0][0].setBounds(32, 33, 50, 50);
		board.add(pieces[0][0]);
		
		pieces[0][8].setAttribute("�ڳ�", Color.black, img, 0, 8);
		board.add(pieces[0][8]);
		pieces[0][8].setBounds(544, 33, 50, 50);

		//����
		img = new ImageIcon("img/Bma.png");
		pieces[0][1].setAttribute("����", Color.black, img, 0, 1);
		board.add(pieces[0][1]);
		pieces[0][1].setBounds(96, 33, 50, 50);

		pieces[0][7].setAttribute("����", Color.black, img, 0, 7);
		board.add(pieces[0][7]);
		pieces[0][7].setBounds(480, 33, 50, 50);

		// ����
		img = new ImageIcon("img/Bxiang.png");
		pieces[0][2].setAttribute("����", Color.black, img, 0, 2);
		board.add(pieces[0][2]);
		pieces[0][2].setBounds(160, 33, 50, 50);

		pieces[0][6].setAttribute("����", Color.black, img, 0, 6);
		board.add(pieces[0][6]);
		pieces[0][6].setBounds(416, 33, 50, 50);

		// ��ʿ
		img = new ImageIcon("img/Bshi.png");
		pieces[0][3].setAttribute("��ʿ", Color.black, img, 0, 3);
		board.add(pieces[0][3]);
		pieces[0][3].setBounds(224, 33, 50, 50);

		pieces[0][5].setAttribute("��ʿ", Color.black, img, 0, 5);
		board.add(pieces[0][5]);
		pieces[0][5].setBounds(352, 33, 50, 50);

		// ��
		img = new ImageIcon("img/jiang.png");
		pieces[0][4].setAttribute("jiang", Color.black, img, 0, 4);
		board.add(pieces[0][4]);
		pieces[0][4].setBounds(288, 33, 50, 50);
		
		//����
		img = new ImageIcon("img/Bpao.png");
		pieces[2][1].setAttribute("����", Color.black, img, 2, 1);
		board.add(pieces[2][1]);
		pieces[2][1].setBounds(96, 157, 50, 50);
		
		pieces[2][7].setAttribute("����", Color.black, img, 2, 7);
		board.add(pieces[2][7]);
		pieces[2][7].setBounds(480, 157, 50, 50);
		
		//����
		img = new ImageIcon("img/Bbing.png");
		
		for(int i=0;i<10; i += 2){	
			pieces[3][i].setAttribute("����", Color.black, img, 3, i);
			board.add(pieces[3][i]);
			pieces[3][i].setBounds(i*64+32, 219, 50, 50);
		}
		
		//�쳵
		img = new ImageIcon("img/Rche.png");
		pieces[9][0].setAttribute("�쳵", Color.red, img, 9, 0);
		board.add(pieces[9][0]);
		pieces[9][0].setBounds(32, 591, 50, 50);
		
		pieces[9][8].setAttribute("�쳵", Color.red, img, 9, 8);
		board.add(pieces[9][8]);
		pieces[9][8].setBounds(544, 591, 50, 50);
		
		//����
		img = new ImageIcon("img/Rma.png");
		pieces[9][1].setAttribute("����", Color.red, img, 9, 1);
		board.add(pieces[9][1]);
		pieces[9][1].setBounds(96, 591, 50, 50);
		
		pieces[9][7].setAttribute("����", Color.red, img, 9, 7);
		board.add(pieces[9][7]);
		pieces[9][7].setBounds(480, 591, 50, 50);
		
		//����
		img = new ImageIcon("img/Rxiang.png");
		pieces[9][2].setAttribute("����", Color.red, img, 9, 2);
		board.add(pieces[9][2]);
		pieces[9][2].setBounds(160, 591, 50, 50);
		
		pieces[9][6].setAttribute("����", Color.red, img, 9, 6);
		board.add(pieces[9][6]);
		pieces[9][6].setBounds(416, 591, 50, 50);
		
		//����
		img = new ImageIcon("img/Rshi.png");
		pieces[9][3].setAttribute("����", Color.red, img, 9, 3);
		board.add(pieces[9][3]);
		pieces[9][3].setBounds(224,591,50,50);
		
		pieces[9][5].setAttribute("����", Color.red, img, 9, 5);
		board.add(pieces[9][5]);
		pieces[9][5].setBounds(352, 591, 50, 50);
		
		//˧
		img = new ImageIcon("img/shuai.png");
		pieces[9][4].setAttribute("shuai", Color.red, img, 9, 4);
		board.add(pieces[9][4]);
		pieces[9][4].setBounds(288,591,50,50);
		
		//����
		img = new ImageIcon("img/Rpao.png");
		pieces[7][1].setAttribute("����", Color.red, img, 7, 1);
		board.add(pieces[7][1]);
		pieces[7][1].setBounds(96, 467, 50, 50);
		
		pieces[7][7].setAttribute("����", Color.red, img, 7, 7);
		board.add(pieces[7][7]);
		pieces[7][7].setBounds(480, 467, 50, 50);
		
		//���
		img = new ImageIcon("img/Rbing.png");
		for(int i=0;i<10;i+=2){
			pieces[6][i].setAttribute("���", Color.red, img, 6, i);
			board.add(pieces[6][i]);
			pieces[6][i].setBounds(i*64+32, 405, 50, 50);
		}
		
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 9; j++){
				if(!pieces[i][j].isPiece()){
					board.add(pieces[i][j]);
					pieces[i][j].setBounds(j*64+32, i*62+33, 50, 50);
				}
			}
		}
	}
	
	/**
	 * �����Ƿ�Ϸ�
	 * @param from
	 * @param to
	 * @return
	 */
	public boolean isLegal(ChessPiecesGUI from, ChessPiecesGUI to){
		step = new Step(from.getx(), from.gety(), to.getx(), to.gety());
		System.out.println(step.getFromX() + " " + step.getFromY());
		System.out.println(step.getToX() + " " + step.getToY());
		if (chessBoard.isMoveLegal(step)){
			chessBoard.update(step);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * ���ӵ��ƶ�
	 * @param from
	 * @param to
	 */
	public void move(ChessPiecesGUI from, ChessPiecesGUI to){
		pushPath(from, to);
		String toName = to.getName();
		board.remove(from);
		board.remove(to);
		int x1 = from.getx(), y1 = from.gety(), x2 = to.getx(), y2 = to.gety();

		if (last1 != null || last2 != null){
			last1.setBorder(null);
			last2.setBorder(null);
		}
		
		from.setBorder(BorderFactory.createLineBorder(Color.red));
		to.setBorder(BorderFactory.createLineBorder(Color.red));
		
		ChessPiecesGUI piecesGUI;
		if (!to.isPiece()){
			piecesGUI = pieces[to.getx()][to.gety()];
			pieces[x2][y2] = from;
			pieces[x2][y2].x = x2;
			pieces[x2][y2].y = y2;
			pieces[x1][y1] = piecesGUI;
			pieces[x1][y1].x = x1;
			pieces[x1][y1].y = y1;
			
			board.add(pieces[x1][y1]);
			board.add(pieces[x2][y2]);
			pieces[x1][y1].setBounds(y1*64+32, x1*62+33, 50, 50);
			pieces[x2][y2].setBounds(y2*64+32, x2*62+33, 50, 50);
		} else {
			piecesGUI = pieces[to.getx()][to.gety()];
			pieces[x2][y2] = from;
			pieces[x2][y2].x = x2;
			pieces[x2][y2].y = y2;
			pieces[x1][y1] = piecesGUI;
			pieces[x1][y1].x = x1;
			pieces[x1][y1].y = y1;
			
			pieces[x1][y1].setPiece(false);
			pieces[x1][y1].setIcon(null);
			pieces[x1][y1].setName(null);
			pieces[x1][y1].setColor(null);
			
			board.add(pieces[x1][y1]);
			board.add(pieces[x2][y2]);
			pieces[x1][y1].setBounds(y1*64+32, x1*62+33, 50, 50);
			pieces[x2][y2].setBounds(y2*64+32, x2*62+33, 50, 50);
			
			if (toName.equals("jiang")){
				JOptionPane.showMessageDialog(null, "��Ү����Ӯ��!", "��ʾ", JOptionPane.CLOSED_OPTION);
				for (int i = 0; i < 10; i++){
					for (int j = 0; j < 9; j++){
						pieces[i][j].removeListener();
					}
				}
				menu.setUnable();
			} else if (toName.equals("shuai")){
				JOptionPane.showMessageDialog(null, "�궿�ӣ�������", "��ʾ", JOptionPane.CLOSED_OPTION);
				for (int i = 0; i < 10; i++){
					for (int j = 0; j < 9; j++){
						pieces[i][j].removeListener();
					}
				}
				menu.setUnable();
			}
		}
		
		last1 = pieces[x1][y1];
		last2 = pieces[x2][y2];
		setFrom(null);
		setTo(null);
		setRedTurn(!isRedTurn());
	}
	
	public void startPlay(){
		if (!isRedTurn){
			System.out.println("AI start move");
			step = chessBoard.AIMove();
			from = pieces[step.getFromX()][step.getFromY()];
			to = pieces[step.getToX()][step.getToY()];
			move(from, to);
		} else {
			//System.out.println("Player start move");
		}
	}
	
	public void restart(){
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 9; j++){
				board.remove(pieces[i][j]);
			}
		}
		initial();
		chessBoard = new ChessBoard();
		menu.setAble();
		setRedTurn(true);
	}
	
	public void setLevel(Level l){
		restart();
		chessBoard.setLevel(l);
	}

	public ChessBoardGUI getBoard() {
		return board;
	}

	public void setBoard(ChessBoardGUI board) {
		this.board = board;
	}

	public ChessPiecesGUI[][] getPieces() {
		return pieces;
	}

	public void setPieces(ChessPiecesGUI[][] pieces) {
		this.pieces = pieces;
	}

	public ChessPiecesGUI getFrom() {
		return from;
	}

	public void setFrom(ChessPiecesGUI from) {
		this.from = from;
	}

	public ChessPiecesGUI getTo() {
		return to;
	}

	public void setTo(ChessPiecesGUI to) {
		this.to = to;
	}

	public ChessPiecesGUI getLast1() {
		return last1;
	}

	public void setLast1(ChessPiecesGUI last1) {
		this.last1 = last1;
	}

	public ChessPiecesGUI getLast2() {
		return last2;
	}

	public void setLast2(ChessPiecesGUI last2) {
		this.last2 = last2;
	}

	public boolean isRedTurn() {
		return isRedTurn;
	}

	public void setRedTurn(boolean isRedTurn) {
		this.isRedTurn = isRedTurn;
	}

	public Step getStep() {
		return step;
	}

	public void setStep(Step step) {
		this.step = step;
	}

	public ChessBoard getChessBoard() {
		return chessBoard;
	}

	public void setChessBoard(ChessBoard chessBoard) {
		this.chessBoard = chessBoard;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Stack<Path> getTrack() {
		return track;
	}

	public void setTrack(Stack<Path> track) {
		this.track = track;
	}

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

	/**
	 * @param from
	 * @param to
	 */
	private void pushPath(ChessPiecesGUI from, ChessPiecesGUI to) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		MainView mainView = new MainView();
		mainView.chessBoard = new ChessBoard();
		mainView.setRedTurn(true);
//		mainView.setRedTurn(false);
//		mainView.chessBoard.setRedMove(false);
//		mainView.chessBoard.AI.position.setRedMove(false);
		mainView.initial();
//		JLabel label = new JLabel("guapi");
//		label.setBounds(50, 50, 50, 50);
//		mainView.board.add(label);
		
		
		mainView.setVisible(true);
		
		while (true){
			System.out.print("");
			
			mainView.startPlay();
		}
	}
}
