package main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MemberMenu.LoginGUI;

public class Menu extends JFrame implements ActionListener{
	
	JPanel North=new JPanel();
	JLabel NorthLB=new JLabel("안녕하세요 배달 슝슝==3 입니다 ^*^");
	
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
		
		North.add(NorthLB);
		
		this.add(North, "North");
		
		member.setPreferredSize(new Dimension(200,150));
		manager.setPreferredSize(new Dimension(200,150));
		this.add(centerP, "Center");
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		member.addActionListener(this);
		manager.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object temp=e.getSource();
		if(temp.equals(member)) {
			LoginGUI LGUI=new LoginGUI();
			LGUI.viewFrame();
			this.setVisible(false);
		}else if(temp.equals(manager)) {
			
		}
		
	}



	
	
}
