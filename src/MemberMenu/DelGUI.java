package MemberMenu;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DelGUI extends JFrame  implements ActionListener{

	JPanel north=new JPanel();
	JPanel north1=new JPanel();
	JLabel titleLB= new JLabel("배달슝슝==3");
	
	
	JPanel center1=new JPanel();
	JButton chicken=new JButton("치킨");
	JButton pizza=new JButton("피자");
	JButton cafe=new JButton("카페");
	
	JPanel south=new JPanel();
	JButton logout=new JButton("로그아웃");
	JButton blist=new JButton("주문내역");
	JButton basket=new JButton("장바구니");
	
	
	String id=null;
	
	
	public DelGUI(String id) {
		this.id=id;
	}
	
	public void GUI() {
		this.setVisible(true);
		this.setBounds(100, 300, 500, 500);
		
		
		north.setLayout(new GridLayout(1,1));
		north1.add(titleLB);
		north.add(north1);
		
		north1.setBackground(Color.LIGHT_GRAY);
		center1.setLayout(new GridLayout(1,3));
		center1.add(chicken);
		center1.add(pizza);
		center1.add(cafe);
		
		south.add(logout);
		south.add(blist);
		south.add(basket);
		
		
		
		
		
		this.add(south, "South");
		this.add(center1, "Center");;
		this.add(north, "North");
	}
	
	
	
	
	

	// gui 실행시키는 메서드
	
	
	public void closeFrame() {
		this.setVisible(false);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
