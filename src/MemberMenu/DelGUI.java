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
	JLabel titleLB= new JLabel("��޽���==3");
	
	
	JPanel center1=new JPanel();
	JButton chicken=new JButton("ġŲ");
	JButton pizza=new JButton("����");
	JButton cafe=new JButton("ī��");
	
	JPanel south=new JPanel();
	JButton logout=new JButton("�α׾ƿ�");
	JButton blist=new JButton("�ֹ�����");
	JButton basket=new JButton("��ٱ���");
	
	
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
	
	
	
	
	

	// gui �����Ű�� �޼���
	
	
	public void closeFrame() {
		this.setVisible(false);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
