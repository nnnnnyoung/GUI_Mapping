package sign;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OverlapGUI extends JFrame implements ActionListener{



	private	JLabel overlap=new JLabel("���̵� �ߺ��Դϴ�.");
	private	JButton ok=new JButton("Ȯ��");
	private	JPanel centerP=new JPanel();
	private	JPanel centerP1=new JPanel();
	private	JPanel centerP2=new JPanel();
	
	public OverlapGUI() {
		this.setBounds(270, 400, 200, 110);
		
		centerP.setLayout(new GridLayout(2,1));
		centerP1.add(overlap);
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
	// gui �����Ű�� �޼���
	
	
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
