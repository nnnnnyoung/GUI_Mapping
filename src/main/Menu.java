package main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu extends JFrame implements ActionListener{

	JPanel centerP=new JPanel();
	JPanel centerP1=new JPanel();
	JPanel centerP2=new JPanel();
	
	JButton member=new JButton("회원 모드");
	JButton manager=new JButton("관리자 모드");
	
	public Menu() {
		
		this.setBounds(100, 200, 300, 400);
		centerP1.add(member);
		centerP2.add(manager);
		centerP.setLayout(new GridLayout(2,1));
		centerP.add(centerP1);
		centerP.add(centerP2);
		
		
		this.add(centerP, "Center");
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
