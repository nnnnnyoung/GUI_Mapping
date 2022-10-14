package MemberMenu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Login_Fail extends JFrame implements ActionListener {



	JLabel fail=new JLabel("로그인 실패");
	JButton ok=new JButton("확인");
	JPanel centerP=new JPanel();
	JPanel centerP1=new JPanel();
	JPanel centerP2=new JPanel();
	
	public Login_Fail() {
		this.setBounds(270, 400, 200, 110);
		
		centerP.setLayout(new GridLayout(2,1));
		centerP1.add(fail);
		centerP2.add(ok);
		
		centerP.add(centerP1);
		centerP.add(centerP2);
		
		
		this.add(centerP, "Center");
		

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		ok.addActionListener(this);
		
	}
	
	public void viewFrame() {
		this.setVisible(true);
	}
	// gui 실행시키는 메서드
	
	
	public void closeFrame() {
		this.setVisible(false);
	}
			
	@Override
	public void actionPerformed(ActionEvent e) {
		Object temp=e.getSource();
		if(temp.equals(ok)) {
			closeFrame();
		}
		
		
	}
}
