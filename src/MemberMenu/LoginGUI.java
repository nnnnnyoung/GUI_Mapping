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
import sign.Login_Fail;

public class LoginGUI extends JFrame implements ActionListener {

	private	JoinGUI JOIN=new JoinGUI();
	
	private	Login_DAO LOGIN=new Login_DAO();
	
	
	
	private	JPanel north=new JPanel();
	private	JPanel north1=new JPanel();
	private	JLabel titleLB= new JLabel("��޽���==3");
	
	private	JLabel idLB= new JLabel("      ID      "); //�α���â ���̵�
	private	JLabel pwLB= new JLabel("��й�ȣ"); //�α��� â pw
	private	JTextField idInput=new JTextField(10);  //�α���â ���̵� �Է�
	private	JTextField pwInput=new JTextField(10);//�α���â pw �Է�
	
	private	JPanel center=new JPanel();
	private	JPanel center_idP=new JPanel();
	private	JPanel center_pwP=new JPanel();
	
	private	JButton login =new JButton("�α���");
	private	JButton join=new JButton("ȸ������");
	private	JButton exit=new JButton("����");

	
	private	JPanel southP=new JPanel();

	
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
	
	
	public void viewFrame() {
		this.setVisible(true);
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
			closeFrame();
		}
		
	}
	
	
	
	
	
	

}
