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
	private	JLabel titleLB= new JLabel("배달슝슝==3");
	
	
	
	private	JLabel idLB= new JLabel("         ID    "); //로그인창 아이디
	private	JLabel pwLB= new JLabel("비밀번호"); //로그인 창 pw
	private	JTextField idInput=new JTextField(10);  //로그인창 아이디 입력
	private	JPasswordField pwInput=new JPasswordField(10);//로그인창 pw 입력
	
	private	JPanel center=new JPanel();
	private	JPanel center_idP=new JPanel();
	private	JPanel center_pwP=new JPanel();
	
	private	JButton login =new JButton("로그인");
	private	JButton join=new JButton("회원가입");
	private	JButton exit=new JButton("종료");

	
	private	JPanel southP=new JPanel();

	
	public LoginGUI() {
		this.setBounds(300, 300, 300, 450);
		
	    try {
	        URL url = new URL("https://search.pstatic.net/common/?src=http%3A%2F%2Fimgnews.naver.net%2Fimage%2F5493%2F2021%2F10%2F20%2F0000041743_001_20211020205803499.png&type=sc960_832");
	        image = ImageIO.read(url);
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
		
		north.setLayout(new GridLayout(1,1));
		north1.add(titleLB);
		north.add(north1);
		
		north1.setBackground(Color.LIGHT_GRAY);
		JLabel imageLB= new JLabel(new ImageIcon(image));
	
		center.setLayout(new FlowLayout(3));
		
		center_idP.setBackground(Color.cyan);
		center_pwP.setBackground(Color.cyan);
		
		center_idP.add(idLB);
		center_idP.add(idInput);
		center_idP.setPreferredSize(new Dimension(300,30));
		
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
				JOptionPane.showMessageDialog(this, "로그인 실패");
			}
			
			
		}else if(temp.equals(join)) {
			JOIN.viewFrame();
			
		}else if(temp.equals(exit)) {
			closeFrame();
		}
		
	}
	
	
	
	
	
	

}
