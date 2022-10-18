package MemberMenu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DAO.Member_DAO;
import DTO.Member_DTO;
import sign.CompleteGUI;
import sign.OverlapGUI;

public class JoinGUI extends JFrame implements ActionListener {
	
	private Member_DAO JDAO=new Member_DAO();
	
	private JPanel northP=new JPanel();
	private JPanel northP1=new JPanel();
	private JLabel titleLB= new JLabel("회원가입");
	
	private JPanel centerP=new JPanel();
	private JPanel centerP1=new JPanel();
	private JPanel centerP2=new JPanel();
	private JPanel centerP3=new JPanel();
	private JPanel centerP4=new JPanel();
	
	private JLabel idLB=new JLabel(" ID ");
	private JLabel pwLB=new JLabel("비밀번호 ");
	private JLabel nameLB=new JLabel("이  름      ");
	private JLabel addrLB=new JLabel("주  소      ");
	
	private JTextField idInput=new JTextField(10);
	private JTextField pwInput=new JTextField(10);
	private JTextField nameInput=new JTextField(10);
	private JTextField addrInput=new JTextField(10);
		
	private	JPanel southP=new JPanel();
	private	JButton join= new JButton("가입");
	private	JButton exit=new JButton("종료");
		
	public JoinGUI(){
		
		this.setBounds(200, 300, 400, 400);
		
		northP1.add(titleLB);
		northP.add(northP1);
		
		this.add(northP,"North");
		
		centerP1.add(idLB);
		centerP1.add(idInput);

		
		centerP2.add(pwLB);
		centerP2.add(pwInput);
		
		centerP3.add(nameLB);
		centerP3.add(nameInput);
		
		centerP4.add(addrLB);
		centerP4.add(addrInput);		
		
		centerP.setLayout(new GridLayout(4,1));
		
		centerP.add(centerP1);
		centerP.add(centerP2);
		centerP.add(centerP3);
		centerP.add(centerP4);
		
		this.add(centerP, "Center");
		
		
		southP.add(join);
		southP.add(exit);
		this.add(southP, "South");
		

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		join.addActionListener(this);
		exit.addActionListener(this);

		
	}
	
	
	public void viewFrame() {
		this.setVisible(true);
	}
	// gui 실행시키는 메서드
	
	
	public void closeFrame() {
		this.setVisible(false);
	}
	// gui 종료시키는 메서드
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object temp= e.getSource();
		if(temp.equals(join)) {
			String id=idInput.getText();
			String pw=pwInput.getText();
			String addr=addrInput.getText();
			String name=nameInput.getText();
			
			
			
			Member_DTO JDTO=new Member_DTO();
			
			
			
			if(JDAO.checkId(id)==1) {
				OverlapGUI Over=new OverlapGUI();
				Over.viewFrame();
			}else if(JDAO.checkId(id)==0){
				JDTO.setId(id);
				JDTO.setPw(pw);
				JDTO.setAddr(addr);
				JDTO.setName(name);
				
				JDAO.newmember(JDTO);
				
				CompleteGUI Com=new CompleteGUI();
				Com.viewFrame();
				idInput.setText("");
				nameInput.setText("");
				pwInput.setText("");
				addrInput.setText("");
				closeFrame();
			}
			
			
			

			
		}else if (temp.equals(exit)) {
			closeFrame();
		}
		
	}
	
	
	
	
	
}
