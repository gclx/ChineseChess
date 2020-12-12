/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import AI.ChessEngine.Level;

/**
 * TODO 
 * @author gclx
 * @version 1.0
 * @date 2018年12月12日 下午8:49:55
 * @copyright 
 * @remarks TODO
 *
 */
public class Menu extends JPanel implements ActionListener, ItemListener{

	private static final long serialVersionUID = 1L;

	private JButton reset;
	
	private JButton back;
	
	private JButton choose;
	
	private Icon icon1;
	private Icon icon2;
	private Icon icon3;
	
	private Icon m1;
	private Icon m2;
	private Icon m3;
	
	private int count = 0;
	
	private int lastSelectItem = 0;
	
	private boolean cancel = false;
	
	private JComboBox<Icon> levelList;
	
	private Vector<Icon> levelListPic;
	
	private MainView view;
	
	/**
	 * @param mainView
	 */
	public Menu(MainView mainView) {
		// TODO Auto-generated constructor stub
		setView(mainView);
		setBackground(new Color(205, 133, 63));
		
		icon1 = new ImageIcon("img/reset.jpg");
		icon2 = new ImageIcon("img/back.jpg");
		icon3 = new ImageIcon("img/check.jpg");
		
		m1 = new ImageIcon("img/menu11.jpg");
		m2 = new ImageIcon("img/menu22.jpg");
		m3 = new ImageIcon("img/menu33.jpg");
		
		levelListPic = new Vector<Icon>(3);
		levelListPic.add(m1);
		levelListPic.add(m2);
		levelListPic.add(m3);
		
		reset = new JButton(icon1);
		back = new JButton(icon2);
		choose = new JButton("后手");
		choose.setBackground(Color.pink);
		choose.setPreferredSize(new Dimension(90, 36));
		Font font = new Font("华文行楷", 0, 25);
		choose.setFont(font);
		
		reset.setBorder(null);
		reset.setBorderPainted(false);
		
		back.setBorder(null);
		back.setBorderPainted(false);
		
		reset.addActionListener(this);
		back.addActionListener(this);
		choose.addActionListener(this);
		
		levelList = new JComboBox<>(levelListPic);
		levelList.setBackground(new Color(205, 133, 63));
		levelList.addItemListener(this);
		
		setLayout(new FlowLayout());
		
		add(reset);
		add(back);
		add(levelList);
		add(choose);
		
		setSize(100, 700);
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		int selectItem;
		int currentSelectItem = levelList.getSelectedIndex();
		if (e.getItem().equals(m1)){
			System.out.println(e.getItem());
			count = (count + 1) % 2;
			if (count == 0 && !cancel){
				selectItem = JOptionPane.showConfirmDialog(null, "难度变更将重新开局，是否确定？", "修改提示", JOptionPane.OK_CANCEL_OPTION);
				if (selectItem == 0){
					view.setLevel(Level.ONE);
					lastSelectItem = currentSelectItem;
				} else {
					cancel = true;
					setLastSelectItem(lastSelectItem);
				}
			} else if (count == 0 && cancel) {
				cancel = false;
			}
		} else if (e.getItem().equals(m2)) {
			System.out.println(e.getItem());
			count = (count + 1) % 2;
			if (count == 0 && !cancel){
				selectItem = JOptionPane.showConfirmDialog(null, "难度变更将重新开局，是否确定？", "修改提示", JOptionPane.OK_CANCEL_OPTION);
				if (selectItem == 0){
					view.setLevel(Level.TWO);
					lastSelectItem = currentSelectItem;
				} else {
					cancel = true;
					setLastSelectItem(lastSelectItem);
				}
			} else if (count == 0 && cancel) {
				cancel = false;
			}
		} else if (e.getItem().equals(m3)){
			System.out.println(e.getItem());
			count = (count + 1) % 2;
			if (count == 0 && !cancel){
				selectItem = JOptionPane.showConfirmDialog(null, "难度变更将重新开局，是否确定？", "修改提示", JOptionPane.OK_CANCEL_OPTION);
				if (selectItem == 0){
					view.setLevel(Level.THREE);
					lastSelectItem = currentSelectItem;
				} else {
					cancel = true;
					setLastSelectItem(lastSelectItem);
				}
			} else if (count == 0 && cancel) {
				cancel = false;
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == reset){
			view.restart();
		} else if (e.getSource() == back){
			// to be continued...
		} else {
			if (view.getChessBoard().getAI().isFlag() == false){	//先后手选择只在开始时有效
				view.setRedTurn(false);
				view.getChessBoard().setRedMove(false);
				view.getChessBoard().getAI().position.setRedMove(false);
			} else {
				;
			}
		}
	}

	public void setAble() {
		// TODO Auto-generated method stub
		back.addActionListener(this);
		levelList.addItemListener(this);
	}

	public void setUnable() {
		// TODO Auto-generated method stub
		back.removeActionListener(this);
		levelList.removeItemListener(this);
	}

	public JButton getReset() {
		return reset;
	}

	public void setReset(JButton reset) {
		this.reset = reset;
	}

	public JButton getBack() {
		return back;
	}

	public void setBack(JButton back) {
		this.back = back;
	}

	public Icon getIcon1() {
		return icon1;
	}

	public void setIcon1(Icon icon1) {
		this.icon1 = icon1;
	}

	public Icon getIcon2() {
		return icon2;
	}

	public void setIcon2(Icon icon2) {
		this.icon2 = icon2;
	}

	public Icon getIcon3() {
		return icon3;
	}

	public void setIcon3(Icon icon3) {
		this.icon3 = icon3;
	}

	public Icon getM1() {
		return m1;
	}

	public void setM1(Icon m1) {
		this.m1 = m1;
	}

	public Icon getM2() {
		return m2;
	}

	public void setM2(Icon m2) {
		this.m2 = m2;
	}

	public Icon getM3() {
		return m3;
	}

	public void setM3(Icon m3) {
		this.m3 = m3;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getLastSelectItem() {
		return lastSelectItem;
	}

	public void setLastSelectItem(int lastSelectItem) {
		this.lastSelectItem = lastSelectItem;
	}

	public boolean isCancel() {
		return cancel;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}

	public JComboBox<Icon> getLevelList() {
		return levelList;
	}

	public void setLevelList(JComboBox<Icon> levelList) {
		this.levelList = levelList;
	}

	public Vector<Icon> getLevelListPic() {
		return levelListPic;
	}

	public void setLevelListPic(Vector<Icon> levelListPic) {
		this.levelListPic = levelListPic;
	}

	public MainView getView() {
		return view;
	}

	public void setView(MainView view) {
		this.view = view;
	}

	
}
