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
	private	JPasswordField pwInput=new JPasswordField(10);
	//로그인창 pw 입력 textfiled가 아닌 passwordfield사용으로 비밀번호 입력시 입력한 글자가 보이지 않는다.  
	
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
	        image = ImageIO.read(url); //이미지 주소 가지고 오기 이미지는 화면에 바로 가지고 올 수 없고 라벨안에 넣어주고 라벨을 배치 해주어야 한다.
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
		
		north.setLayout(new GridLayout(1,1));
		north1.add(titleLB);
		north.add(north1);
		
		north1.setBackground(Color.LIGHT_GRAY);
		JLabel imageLB= new JLabel(new ImageIcon(image)); //라벨에 이미지 넣어주기
	
		center.setLayout(new FlowLayout(3));
		
		center_idP.setBackground(Color.cyan);
		center_pwP.setBackground(Color.cyan);
		
		center_idP.add(idLB);
		center_idP.add(idInput);
		center_idP.setPreferredSize(new Dimension(300,30)); //패널 사이즈 지정해주기
		
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
		
		login.addActionListener(this);// 버튼 클릭을 입력받는 메서드
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
			if(LOGIN.Login(id, pw)==1) { //id와 pw가 멤버테이블에서 일치하는게 있는지 찾는다
				closeFrame();
				DelGUI DEL=new DelGUI(id); //일치시 로그인이 성공하고 로그인 한 id값을 가지고 배달주문 클래스로 넘어간다.
				DEL.GUI();
			}else if(LOGIN.Login(id, pw)==0) { //id와 pw가 일치하지 않을 시 로그인 실패 안내창이 뜬다.
				JOptionPane.showMessageDialog(this, "로그인 실패"); //간단한 안내창을 뜨게 해주는 메서드 출력될 내용을 적어주면 된다.
			}
			
			
		}else if(temp.equals(join)) {
			JOIN.viewFrame();
			//회원가입 클릭시 회원가입 클래스로 넘어간다.
			
		}else if(temp.equals(exit)) {
			closeFrame();
			
		}
		
	}
	
	
	
	
	
	

}
