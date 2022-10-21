package ManagerMenu;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DAO.MLogin_DAO;

public class MLoginGUI extends JFrame implements ActionListener {
	private MLogin_DAO MLOGIN=new MLogin_DAO();
	
	private JPanel north=new JPanel();
	private JPanel north1=new JPanel();
	private JLabel titleLB= new JLabel("������ ���");
	
	private JLabel idLB= new JLabel("      ID      "); //�α���â ���̵�
	private JLabel pwLB= new JLabel("��й�ȣ"); //�α��� â pw
	private JTextField idInput=new JTextField(10);  //�α���â ���̵� �Է�
	private JPasswordField pwInput=new JPasswordField(10);//�α���â pw �Է�
	
	private JPanel center=new JPanel();
	private JPanel center_idP=new JPanel();
	private JPanel center_pwP=new JPanel();
	
	private JButton login =new JButton("�α���");
	private JButton exit=new JButton("����");
	
	private JPanel southP=new JPanel();

	public MLoginGUI() {
		this.setBounds(300, 300, 300, 300);
		
		north.setLayout(new GridLayout(1,1));
		north1.add(titleLB);
		north.add(north1);
		
		north1.setBackground(Color.LIGHT_GRAY);
	
		center.setLayout(new GridLayout(2,1));
		center_idP.setBackground(Color.green);
		center_pwP.setBackground(Color.green);
		
		center_idP.add(idLB);
		center_idP.add(idInput);
		
		center_pwP.add(pwLB);
		center_pwP.add(pwInput);

		center.add(center_idP);
		center.add(center_pwP);
		
		southP.add(login);
		southP.add(exit);
		
		
		this.add(center,"Center");
		this.add(north,"North");
		this.add(southP, "South");
		
		login.addActionListener(this);
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
			String mid=idInput.getText();
			String mpw=pwInput.getText();
			if(MLOGIN.Login(mid, mpw)==1) {
				
				closeFrame();
				ManagementGUI mng=new ManagementGUI();
				mng.GUI();
			}else if(MLOGIN.Login(mid, mpw)==0) {
				JOptionPane.showMessageDialog(this, "�α��� ����");
			}
			
			
		}else if(temp.equals(exit)) {
			closeFrame();
		}
		
	}
	
	
	
	
	
	

}
