package MemberMenu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import DAO.Del_DAO;

public class ScoreGUI extends JFrame implements ActionListener{
	
	
	private String sName=null;
	
	private String kind []= {"1","2","3","4","5"}; //콤보박스에 넣을 자료들

	private JComboBox<String> kindbox; //내가 미리 담아둔 자료만 선택 가능하도록 담는 박스
	
	private JPanel centerP=new JPanel();
	private JPanel centerP1=new JPanel();
	private JPanel centerP2=new JPanel();
	private JPanel centerP3=new JPanel();
	
	private JButton input=new JButton("등록");
	
	private JLabel scoreLB=new JLabel("평점");
	
	
	
	
	
	ScoreGUI(String sName){
		this.sName=sName;
		kindbox = new JComboBox<String>(kind);
		
		centerP.setLayout(new GridLayout(3,1));
		
		JLabel SscoreLB=new JLabel(sName+"의 평점을 등록해주세요!");
		centerP1.add(SscoreLB);
		centerP2.add(scoreLB);
		centerP2.add(kindbox);
		centerP3.add(input);
		
		centerP.add(centerP1);
		centerP.add(centerP2);
		centerP.add(centerP3);
		
		this.setBounds(300, 300, 250, 150);
		
		this.add(centerP, "Center");
		
		input.addActionListener(this);
		
		
	}
	
	public void viewFrame() {
		this.setVisible(true);
	}
	public void closeFrame() {
		this.setVisible(false);
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object temp=e.getSource();
		if(temp.equals(input)) {
			Del_DAO DDAO=new Del_DAO();	
			String score=kindbox.getSelectedItem().toString();
			DDAO.inputScore(sName,score);
			JOptionPane.showMessageDialog(this, "별점이 등록되었습니다.");
			closeFrame();
		}
		
		
	}
	
}
