package MemberMenu;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DAO.Login_DAO;

public class LoginGUI extends JFrame implements ActionListener {

	JoinGUI JOIN=new JoinGUI();
	
	Login_DAO LOGIN=new Login_DAO();
	
	
	
	JPanel north=new JPanel();
	JPanel north1=new JPanel();
	JLabel titleLB= new JLabel("배달슝슝==3");
	
	JLabel idLB= new JLabel("      ID      "); //로그인창 아이디
	JLabel pwLB= new JLabel("비밀번호"); //로그인 창 pw
	JTextField idInput=new JTextField(10);  //로그인창 아이디 입력
	JTextField pwInput=new JTextField(10);//로그인창 pw 입력
	
	JPanel center=new JPanel();
	JPanel center_idP=new JPanel();
	JPanel center_pwP=new JPanel();
	
	JButton login =new JButton("로그인");
	JButton join=new JButton("회원가입");
	JButton exit=new JButton("종료");

	
	JPanel southP=new JPanel();

	
	public LoginGUI() {
		this.setBounds(100, 300, 300, 300);
		
		
		
		north.setLayout(new GridLayout(1,1));
		north1.add(titleLB);
		north.add(north1);
		
		north1.setBackground(Color.LIGHT_GRAY);

	
		center.setLayout(new GridLayout(2,1));
		center_idP.setBackground(Color.cyan);
		center_pwP.setBackground(Color.cyan);
		
		center_idP.add(idLB);
		center_idP.add(idInput);
		
		center_pwP.add(pwLB);
		center_pwP.add(pwInput);

		center.add(center_idP);
		center.add(center_pwP);
		
		southP.add(login);
		southP.add(join);
		southP.add(exit);
		
		
		this.add(center,"Center");
		this.add(north,"North");
		this.add(southP, "South");
		
		login.addActionListener(this);
		join.addActionListener(this);
		exit.addActionListener(this);
		
		
		
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	
	public void closeFrame() {
		this.setVisible(false);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object temp=e.getSource();
		if(temp.equals(login)) {
			String id=idInput.getText();
			String pw=pwInput.getText();
			if(LOGIN.Login(id, pw)==1) {
				
				closeFrame();
				DelGUI DEL=new DelGUI(id);
				DEL.GUI();
			}else if(LOGIN.Login(id, pw)==0) {
				Login_Fail LF=new Login_Fail();
				LF.viewFrame();
			}
			
			
		}else if(temp.equals(join)) {
			JOIN.viewFrame();
			
		}else if(temp.equals(exit)) {
			System.exit(0);
		}
		
	}
	
	
	
	
	
	

}
