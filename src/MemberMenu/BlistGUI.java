package MemberMenu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import DAO.Blist_DAO;
import DTO.BlistDTO;


public class BlistGUI extends JFrame implements ActionListener,ItemListener{

	private String id;
	private Blist_DAO BDAO;
	private int bno=0;
	
	private String sName=null;
	
	private List buyList = new List(10);
	private JTextArea buyinfo = new JTextArea(10,3);
	private JLabel bnum= new JLabel("�ֹ���ȣ");
	
	private JPanel north=new JPanel();
	private JPanel north1=new JPanel();
	private JPanel north2=new JPanel();
	private JPanel north2_1=new JPanel();
	private JPanel north2_1_1=new JPanel();
	private JPanel north2_1_2=new JPanel();
	private JPanel north2_1_3=new JPanel();
	
	private JPanel center1=new JPanel();
	private JPanel center1_1=new JPanel();
	private JPanel center1_1_2=new JPanel();
	private JTextArea timeArea = new JTextArea(10,3);
	
	private JPanel south=new JPanel();
	
	private JButton all=new JButton("��ü");
	private JButton week=new JButton("7���̳�");
	private JButton month=new JButton("30���̳�");
	private JButton cancel=new JButton("�ֹ����");
	
	private JButton score=new JButton("�����ֱ�");
	private JButton back=new JButton("�ڷΰ���");
	
	public BlistGUI(String id,Blist_DAO BDAO){
		this.id=id;
		this.BDAO=BDAO;
		north.setLayout(new GridLayout(2,1));
		JLabel titleLB= new JLabel(id+"�� �ֹ�����");
		north1.add(titleLB);
		
		north2.setLayout(new BorderLayout());
		
		north2.add(bnum,"West");
		
		north2_1_1.add(all);		
		north2_1_2.add(month);
		north2_1_3.add(week);
		north2_1.add(north2_1_1);
		north2_1.add(north2_1_2);
		north2_1.add(north2_1_3);
		
		north2.add(north2_1,"Center");
		
		north.add(north1);
		north.add(north2);
		
		south.add(back,"Center");
		
		this.setBounds(300, 300, 500, 500);
	
		center1.setLayout(new BorderLayout());
		center1.add(buyList,"West");
		center1_1.setLayout(new BorderLayout());
		center1_1.add(buyinfo,"North");
		center1.add(center1_1,"Center");
		center1_1_2.setLayout(new GridLayout(2,1));
		this.add(center1,"Center");
		this.add(north,"North");
		this.add(south,"South");
		
		all.addActionListener(this);
		week.addActionListener(this);
		month.addActionListener(this);
		back.addActionListener(this);
		score.addActionListener(this);
		buyList.addItemListener(this);
		cancel.addActionListener(this);
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	public void allPrt() {
		buyList.removeAll();
		center1_1.removeAll();
		ArrayList<BlistDTO> blist=BDAO.prtAll(id);
		// blist ���̺� �α��� �� id�� �Ű������� �Ѱ��־� �ش����� �ֹ������� ����ϰ� �ֹ���ȣ�� ArrayList�� �����Ѵ�.
		
		for(BlistDTO b : blist) {
			buyList.add(Integer.toString(b.getBnum()));
			// ArrayList�� ����� ������ List�� �����Ѵ�.
			// �׷��� ����Ʈ���� int�ڷ����� ������� �ʾƼ� String���� ��ȯ������ �� �־��־���.
		}
		
		this.repaint(); //ȭ�鿡 �ٽ� �׸���.
		this.setVisible(true);
	}
	
	public void weekPrt() {
		buyList.removeAll();
		center1_1.removeAll();
		ArrayList<BlistDTO> blist=BDAO.prtWeek(id);
		// blist ���̺� �α��� �� id�� �Ű������� �Ѱ��־� �ش����� �ֹ������� ����ϰ� �ֹ���ȣ�� ArrayList�� �����Ѵ�. 
		//�� �������� ������ �ɾ� 7�� �̳��� �ֹ������� ��µȴ�.
		for(BlistDTO b : blist) {
			buyList.add(Integer.toString(b.getBnum()));
		}
		this.repaint(); 	
		this.setVisible(true);
	}
	public void monthPrt() {
		// blist ���̺� �α��� �� id�� �Ű������� �Ѱ��־� �ش����� �ֹ������� ����ϰ� �ֹ���ȣ�� ArrayList�� �����Ѵ�. 
		//�� �������� ������ �ɾ� 30�� �̳��� �ֹ������� ��µȴ�.
		buyList.removeAll();
		center1_1.removeAll();
		
		ArrayList<BlistDTO> blist=BDAO.prtMonth(id);
		for(BlistDTO b : blist) {
			buyList.add(Integer.toString(b.getBnum()));
		}
		this.repaint(); 	
		this.setVisible(true);
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
		if(temp.equals(all)){
			allPrt(); //��ü���� ��ư Ŭ���� allprt() �޼��� ����
		}else if(temp.equals(week)){
			weekPrt();//7���̳� ��ư Ŭ���� weekPrt() �޼��� ����
		}else if(temp.equals(month)){
			monthPrt();//30���̳� ��ư Ŭ���� monthPrt() �޼��� ����
		}else if(temp.equals(back)){
			closeFrame(); //���� ��ư Ŭ���� GUI����
		}else if(temp.equals(cancel)){
			if(BDAO.fiveMi(id,bno)) { //�ֹ��ϰ� 5���� ������ �ʾҴ��� Ȯ���ϴ� �޼���, �ֹ���ȣ�� �Ű������� �Ѱ� �޼��� ���� 
				BDAO.del(bno); //5���� ������ �ʾҴٸ� �ֹ���ȣ�� �Ű������� �Ѱ� blist ���̺��� ���� ��Ŵ
				JOptionPane.showMessageDialog(this, "�ֹ���� �Ϸ�"); //��ҿϷ� �޼���
			}else if(!BDAO.fiveMi(id,bno)){
				JOptionPane.showMessageDialog(this, "��Ұ��� �ð��� �������ϴ�.");//��ҺҰ� �޼���
				//�ֹ��ð��� ����ð��� 5�� �̻� ���̰� ���ٸ� �ּҺҰ�
			}
		}else if(temp.equals(score)){
			ScoreGUI SC=new ScoreGUI(sName);
			SC.viewFrame();
		}		
	}
	
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(buyList)) {
			String bnum = buyList.getSelectedItem(); //�ֹ���ȣ Ŭ��
			int time=0; //��޽ð� �޾ƿ� ����
			Image image = null; //�̹��� ����
			center1_1_2.removeAll(); //�������� ȭ�鿡 �ִ°��� �����ְ� ����
			center1_1.removeAll();
			BlistDTO w = BDAO.selectOne(bnum); //�ֹ���ȣ �Ѱ���
			
			sName=w.getShop();
			
			buyinfo.setText(""); //�ֹ���ȣ�� ���� ���
			buyinfo.append("��ȣ�� : "+w.getShop()+"\n");
			buyinfo.append("�޴� : "+w.getFname()+"\n");
			buyinfo.append("���� : "+w.getPrice()+"\n");
			buyinfo.append("�ּ� : "+w.getAddr()+"\n");
			buyinfo.append("�ֹ��ð� : "+w.getIndate()+"\n");
			
			
			center1_1.add(buyinfo,"North");
			time=BDAO.time(id, bnum,time); 
			//��޽ð� �޼���, �⺻���� ����� 40�� �ҿ�ȴٰ� ����
			center1_1_2.add(timeArea);
			center1_1.add(center1_1_2,"Center");
			if(time==0) { //�ֹ��� 40���� �����ٸ� time�� 0�� ����
				//�̹� 40���� �����⶧���� timeArea�� ��޿Ϸ� �Է�
				timeArea.setText("-------------------------------------��޿Ϸ�==3------------------------------------");
			    try {
			    	//�̹����� �Ϸ� �̹��� ����
			        URL url = new URL("https://blogfiles.pstatic.net/MjAyMjEwMjBfMTg5/MDAxNjY2MjM4MzEwMDg2.55LCADfIpj246Gb_1mnpxRRe-aNNZGpFSscifEUqZaog.tX80B22pY3GNleu9O7uIi9iU3hrKbcEpvDp8gdtXA5gg.JPEG.tjs40106/KakaoTalk_20221020_125810842.jpg?type=w2");
			        image = ImageIO.read(url).getScaledInstance(100, 70, Image.SCALE_DEFAULT);
			        //�̹��� ũ�� ����
			        JLabel imageLB= new JLabel(new ImageIcon(image));
			        //�󺧿� �̹��� ����
			        center1_1_2.add(imageLB);
			        //���� �гο� ����
			        center1_1.add(score,"South");
			    } catch (IOException e1) {
			    	e1.printStackTrace();
			    }
				
			}else {
				timeArea.setText("--------------------------��޽ð� "+time+"�� ���ҽ��ϴ� ^*^--------------------------");
			    try {
			        URL url = new URL("https://blogfiles.pstatic.net/MjAyMjEwMjBfNTAg/MDAxNjY2MjM4MTEyNzYw.iKIg5dxJ2D1gctIlyAph47HipOhOroa9mcfapNaweuQg.Yz4cqdWDCSy9ve4sQGZiyIZSoPrVrwdk5CxLFOTXO88g.JPEG.tjs40106/KakaoTalk_20221020_124538700.jpg?type=w2");
			        image = ImageIO.read(url).getScaledInstance(90, 60, Image.SCALE_DEFAULT);
			        JLabel imageLB= new JLabel(new ImageIcon(image));
			        center1_1_2.add(imageLB);
			        center1_1.add(cancel,"South");
			    } catch (IOException e1) {
			    	e1.printStackTrace();
			    }
			}
						
			
			
			
			bno=Integer.decode(bnum); //List�� �ֹ���ȣ�� String���� �־��־��� ������ �ٽ� int�� �ٲ��ش�.
			
			this.repaint(); 		
			this.setVisible(true);
		}
	}

}
