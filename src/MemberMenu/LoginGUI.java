package MemberMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DAO.Login_DAO;

public class LoginGUI extends JFrame implements ActionListener {

	private	JoinGUI JOIN=new JoinGUI();
	
	private	Login_DAO LOGIN=new Login_DAO();
	
	
	private Image image = null;


	private	JPanel north=new JPanel();
	private	JPanel north1=new JPanel();
	private	JLabel titleLB= new JLabel("��޽���==3");
	
	
	
	private	JLabel idLB= new JLabel("         ID    "); //�α���â ���̵�
	private	JLabel pwLB= new JLabel("��й�ȣ"); //�α��� â pw
	private	JTextField idInput=new JTextField(10);  //�α���â ���̵� �Է�
	private	JPasswordField pwInput=new JPasswordField(10);
	//�α���â pw �Է� textfiled�� �ƴ� passwordfield������� ��й�ȣ �Է½� �Է��� ���ڰ� ������ �ʴ´�.  
	
	private	JPanel center=new JPanel();
	private	JPanel center_idP=new JPanel();
	private	JPanel center_pwP=new JPanel();
	
	private	JButton login =new JButton("�α���");
	private	JButton join=new JButton("ȸ������");
	private	JButton exit=new JButton("����");

	
	private	JPanel southP=new JPanel();

	
	public LoginGUI() {
		this.setBounds(300, 300, 300, 450);
		
	    try {
	        URL url = new URL("https://search.pstatic.net/common/?src=http%3A%2F%2Fimgnews.naver.net%2Fimage%2F5493%2F2021%2F10%2F20%2F0000041743_001_20211020205803499.png&type=sc960_832");
	        image = ImageIO.read(url); //�̹��� �ּ� ������ ���� �̹����� ȭ�鿡 �ٷ� ������ �� �� ���� �󺧾ȿ� �־��ְ� ���� ��ġ ���־�� �Ѵ�.
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
		
		north.setLayout(new GridLayout(1,1));
		north1.add(titleLB);
		north.add(north1);
		
		north1.setBackground(Color.LIGHT_GRAY);
		JLabel imageLB= new JLabel(new ImageIcon(image)); //�󺧿� �̹��� �־��ֱ�
	
		center.setLayout(new FlowLayout(3));
		
		center_idP.setBackground(Color.cyan);
		center_pwP.setBackground(Color.cyan);
		
		center_idP.add(idLB);
		center_idP.add(idInput);
		center_idP.setPreferredSize(new Dimension(300,30)); //�г� ������ �������ֱ�
		
		center_pwP.add(pwLB);
		center_pwP.add(pwInput);
		center_pwP.setPreferredSize(new Dimension(300,30));
		
		
		center.add(imageLB);
		center.add(center_idP);
		center.add(center_pwP);
		
		southP.add(login);
		southP.add(join);
		southP.add(exit);
		
		
		this.add(center,"Center");
		this.add(north,"North");
		this.add(southP, "South");
		
		login.addActionListener(this);// ��ư Ŭ���� �Է¹޴� �޼���
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
			if(LOGIN.Login(id, pw)==1) { //id�� pw�� ������̺��� ��ġ�ϴ°� �ִ��� ã�´�
				closeFrame();
				DelGUI DEL=new DelGUI(id); //��ġ�� �α����� �����ϰ� �α��� �� id���� ������ ����ֹ� Ŭ������ �Ѿ��.
				DEL.GUI();
			}else if(LOGIN.Login(id, pw)==0) { //id�� pw�� ��ġ���� ���� �� �α��� ���� �ȳ�â�� ���.
				JOptionPane.showMessageDialog(this, "�α��� ����"); //������ �ȳ�â�� �߰� ���ִ� �޼��� ��µ� ������ �����ָ� �ȴ�.
			}
			
			
		}else if(temp.equals(join)) {
			JOIN.viewFrame();
			//ȸ������ Ŭ���� ȸ������ Ŭ������ �Ѿ��.
			
		}else if(temp.equals(exit)) {
			closeFrame();
			
		}
		
	}
	
	
	
	
	
	

}
