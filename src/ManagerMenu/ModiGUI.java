package ManagerMenu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DAO.Del_DAO;

public class ModiGUI extends JFrame implements ActionListener {
	private String sName=null;
	private Del_DAO DDAO=new Del_DAO();
	
	private JPanel northP=new JPanel();
	private JPanel northP1=new JPanel();
	private JLabel titleLB= new JLabel("가게정보수정");
	
	private JPanel centerP=new JPanel();
	private JPanel centerP1=new JPanel();
	private JPanel centerP2=new JPanel();

	
	private JLabel foodLB=new JLabel(" 음식명 ");
	private JLabel priceLB=new JLabel("가격 ");

	private JTextField foodInput=new JTextField(10);
	private JTextField priceInput=new JTextField(10);

	private	JPanel southP=new JPanel();
	private	JButton modi= new JButton("수정");
	private	JButton exit=new JButton("종료");
		
	public ModiGUI( String sName){
		this.sName=sName;
		this.setBounds(200, 300, 400, 400);
				
		northP1.add(titleLB);
		northP.add(northP1);
		
		this.add(northP,"North");
		
		centerP1.add(foodLB);
		centerP1.add(foodInput);

		
		centerP2.add(priceLB);
		centerP2.add(priceInput);
	
		
		centerP.setLayout(new GridLayout(2,1));
		
		centerP.add(centerP1);
		centerP.add(centerP2);

		this.add(centerP, "Center");
		
		southP.add(modi);
		southP.add(exit);
		this.add(southP, "South");

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		modi.addActionListener(this);
		exit.addActionListener(this);

		
	}
	
	
	public void viewFrame() {
		this.setVisible(true);
	}
	// gui 실행시키는 메서드
	
	
	public void closeFrame() {
		this.setVisible(false);
	}
	// gui 종료시키는 메서드
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object temp= e.getSource();
		if(temp.equals(modi)) {
			String fname=foodInput.getText();
			int price=Integer.parseInt(priceInput.getText());
			
			DDAO.modiShop(sName,fname,price);

			JOptionPane.showMessageDialog(this, "수정완료");
			closeFrame();

		}else if (temp.equals(exit)) {
			closeFrame();
		}
		
	}
	
	
	
	
	
}
