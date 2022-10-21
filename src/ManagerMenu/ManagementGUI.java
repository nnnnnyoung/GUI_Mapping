package ManagerMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.Del_DAO;
import DAO.Member_DAO;
import DTO.Del_DTO;
import DTO.Member_DTO;

public class ManagementGUI extends JFrame  implements ActionListener,ItemListener,MouseListener{
	private Del_DAO DDAO=new Del_DAO();
	private Member_DAO MDAO=new Member_DAO();
	
	private int colV;
	private int rowV;

	private JPanel north=new JPanel();
	private JPanel north1=new JPanel();
	private JPanel north2=new JPanel();
	private JLabel titleLB= new JLabel("�����ڸ��");
	
	private JPanel center=new JPanel();
	private JPanel center1=new JPanel();
	private JPanel center2=new JPanel();
	private JPanel center2_1=new JPanel();
	private JPanel center3=new JPanel();
	private JPanel center3_1=new JPanel();
	private JPanel center3_2=new JPanel();
	private JPanel center3_3=new JPanel();
	private JPanel center3_4=new JPanel();
	private JPanel center3_5=new JPanel();
	private JPanel center3__1=new JPanel();
	private JPanel center4=new JPanel();
	private JPanel center4_1=new JPanel();
	private JPanel center4_2=new JPanel();
	private JPanel center4_2_1=new JPanel();
	private JPanel center4_2_2=new JPanel();	
	private JPanel center5=new JPanel();
	
	private JLabel kindLB=new JLabel("���� ī�װ�");
	private JLabel shopLB=new JLabel("��ȣ��");
	private JLabel fnameLB=new JLabel("�޴���");
	private JLabel priceLB=new JLabel("����");
	private JLabel addrLB=new JLabel("�ּ�");
	
	private JTextArea addrSh=new JTextArea(100,30);
	
	private JTextField shopInput=new JTextField(10);
	private JTextField fnameInput =new JTextField(10);
	private JTextField priceInput=new JTextField(10);
	private JTextField addrInput=new JTextField(10);
		
	private JLabel ModiNameLB=new JLabel("�̸�");
	private JLabel ModiPwLB=new JLabel("��й�ȣ");
	private JLabel ModiAddrLB=new JLabel("�ּ�");
	
	private JTextField modiName=new JTextField(7);
	private JTextField modiPw=new JTextField(7);
	private JTextField modiAddr=new JTextField(7);
	
	
	private JTextField searchF=new JTextField(10);
	
	private String kind []= {"chicken","pizza","cafe"}; //�޺��ڽ��� ���� �ڷ��

	private JComboBox<String> kindbox; //���� �̸� ��Ƶ� �ڷḸ ���� �����ϵ��� ��� �ڽ�
	
    private JButton input=new JButton("�Ĵ���");
    private JButton sList=new JButton("�Ĵ���");
    private JButton mList=new JButton("ȸ�����");
    private JButton sInput=new JButton("���");
    private JButton search=new JButton("�ּҰ˻�");
    private JButton modiM=new JButton("����");
    
    
    private String sName=null;
	
	private JPanel south=new JPanel();
	private JButton logout=new JButton("�α׾ƿ�");
	
	private JButton modi=new JButton("�����ϱ�");	

	private List shopList = new List(10);
	private JTextArea shopinfo = new JTextArea(10,3);

	
	private Object ob[][]=new Object[0][4]; //j���̺� �� ����
	private String col[]= { "ID","��й�ȣ","�̸�","�ּ�"};//j���̺� �÷��� ����
	
	private DefaultTableModel model; //table�� �ٷ� �����͸� �Է��ϸ� �����ø��� ���̺��� ���� �������ϱ� ������ ������ ���� ���� ������ش�.
	private JTable table; //���̺� ����
	private JScrollPane js;//��ũ�ѿ� ���̺��� ����־�� �Ѵ�. 
	
	
	
	public void GUI() {
		this.setVisible(true);
		this.setBounds(300, 300, 700, 700);
		
		kindbox = new JComboBox<String>(kind); //kind �迭�� �ִ� �ܾ���� �޺��ڽ��� �־���
		
		north.setLayout(new GridLayout(2,1));
		north1.add(titleLB);
		north.add(north1);

		north.add(north2);
		
        model=new DefaultTableModel(ob, col); //1)������ ����[][], 2)�÷���
        table=new JTable(model); // ���̺� ������ �־��ֱ�
        js=new JScrollPane(table); //��ũ�ѿ� ���̺� �־��ֱ�

		north1.setBackground(Color.LIGHT_GRAY);
		center1.setLayout(new GridLayout(1,3));
		
		north2.add(input);
		north2.add(sList);
		north2.add(mList);
		
		center.add(center1);
		south.add(logout);
		
		center2.setLayout(new BorderLayout());
		center2.add(shopList,"West");
		center2_1.setLayout(new GridLayout(2,1));
		center2_1.add(shopinfo);

		center2.add(center2_1,"Center");
		
		center3.setLayout(new GridLayout(6,1));
		center3_1.add(kindLB);
		center3_1.add(kindbox); //������ �ؽ�Ʈ�� �Է¹��� �ʰ� ���� �����Ѱ͵鸸 ���õǰ� ����
		
		center3_2.add(shopLB);
		center3_2.add(shopInput);
		
		center3_3.add(fnameLB);
		center3_3.add(fnameInput);
		
		center3_4.add(priceLB);
		center3_4.add(priceInput);
		
		center3_5.add(addrLB);
		center3_5.add(addrInput);
		
		center3.add(center3_1);
		center3.add(center3_2);
		center3.add(center3_3);
		center3.add(center3_4);
		center3.add(center3_5);
		center3__1.add(sInput);
		center3.add(center3__1);
		
		center4.setLayout(new BorderLayout());
		
		center4_1.add(js);
		
		center4_2_1.add(searchF);
		center4_2_1.add(search);
		
		center4_2_2.add(ModiPwLB);
		center4_2_2.add(modiPw);
		
		center4_2_2.add(ModiNameLB);
		center4_2_2.add(modiName);
		
		center4_2_2.add(ModiAddrLB);
		center4_2_2.add(modiAddr);
		
		
		center4_2_2.add(modiM);
		
		center4_2.setLayout(new GridLayout(2,1));
		
		center4_2.add(center4_2_2);
		center4_2.add(center4_2_1);
		
		center4.add(center4_1,"Center");
		center4.add(center4_2,"South");
		
		center5.add(addrSh);
		
		this.add(south, "South");
		this.add(center, "Center");
		this.add(north, "North");
		
		input.addActionListener(this);
		sList.addActionListener(this);
		mList.addActionListener(this);		
		logout.addActionListener(this);
		search.addActionListener(this);
		modi.addActionListener(this);
		sInput.addActionListener(this);
		modiM.addActionListener(this);
		
		table.addMouseListener(this); //���콺������ ����
		
		shopList.addItemListener(this);
		
	}

	// gui �����Ű�� �޼���
	
	public void closeFrame() {
		this.setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object temp= e.getSource();
		if(temp.equals(logout)) {
			closeFrame();
			MLoginGUI MLGUI=new MLoginGUI();
			MLGUI.viewFrame(); 
		}else if(temp.equals(input)) {
			this.remove(center);
			this.remove(center2);	
			this.remove(center4);
			this.remove(center5);
			this.add(center3, "Center"); //center3�� ���� input�� �� �ְ� �����صΰ� inputŬ���� center�� �׷����� ��
			this.repaint();   // �����ٰ� ȭ���� �ٽ� �׷���.
			this.setVisible(true);
		}else if(temp.equals(sList)) {
			this.remove(center);
			this.remove(center3);
			this.remove(center4);
			this.remove(center5);
			this.add(center2, "Center");
			
			ArrayList<Del_DTO> SList=DDAO.getShop();

			shopList.removeAll(); 
			for(Del_DTO w : SList) {
				shopList.add(w.getShop()); //����Ʈ�� �׸� �߰�..
			}
			this.repaint();   // �����ٰ� ȭ���� �ٽ� �׷���.
			this.setVisible(true);
		}else if(temp.equals(mList)) {
			for(int i=0; i<model.getRowCount();) {
				model.removeRow(i);
			} //���̺� ������ �� �ʱ�ȭ �����ֱ� Ʃ���ϳ��� �����Ǹ� ���� �и��� i++�� ������ �ʰ� �����Ͱ� ������������ 0�� �ุ �������ָ� �ȴ�. 
			this.remove(center);
			this.remove(center2);
			this.remove(center3);
			this.remove(center5);
			
			this.add(center4, "Center");
			
			MDAO.prtM(model);//������ ���� �Ű������� �ѱ�� ��� ȸ���� ������ �������ش�.
			
			this.repaint();   // �����ٰ� ȭ���� �ٽ� �׷���.
			this.setVisible(true);
			
		}else if(temp.equals(modi)) {
			ModiGUI mGui=new ModiGUI(sName);
			//������ ������ ���� ����
			mGui.viewFrame();
			
		}else if(temp.equals(search)) {
			addrSh.setText("");
			String sh=searchF.getText();//�˻��� �ܾ� �Է�
			this.remove(center);
			this.remove(center2);
			this.remove(center3);
			this.remove(center4);
			this.add(center5, "Center");
			
			if(!MDAO.searsh(sh,addrSh)) { //�Է��� �ּ� sh�� addrSh�� �Ű������� �Ѱ� sh�� �ּҰ� ���� ȸ���� addrShdp�� ��ƿ´�.
				addrSh.setText("�˻������ �����ϴ�.");
			}
			this.repaint();   // �����ٰ� ȭ���� �ٽ� �׷���.
			this.setVisible(true);		
			searchF.setText("");
			
		}else if(temp.equals(sInput)) {
			String kind=kindbox.getSelectedItem().toString(); 
			//�޺��ڽ��� �ִ� ������ �ڷ��� Object�� ������ ������ String���� ��ȯ ���ش�.
			String shop=shopInput.getText();
			String addr=addrInput.getText();
			String fname=fnameInput.getText();
			String price=priceInput.getText();
			
			Del_DTO newShop=new Del_DTO(); //���ο� ���� ��ü ����, ���� �־���
			newShop.setKind(kind);
			newShop.setFname(fname);
			newShop.setAddr(addr);
			newShop.setPrice(Integer.parseInt(price));
			newShop.setShop(shop);
			
			DDAO.inputShop(newShop);//Del_DTO ��ü�� ������ �Ű������� �Ѱ��־� �������� ���
			JOptionPane.showMessageDialog(this, "���Ե���� �Ϸ� �Ǿ����ϴ�");
			
			shopInput.setText("");//�ؽ�Ʈ �ʵ��� �� �����ֱ�
			addrInput.setText("");
			fnameInput.setText("");
			priceInput.setText("");
			this.repaint(); 	
		}else if(temp.equals(modiM)) {
			Member_DTO modiM=new Member_DTO();
			
			modiM.setId((String) table.getModel().getValueAt(rowV, 0)); 
			//���̺��� value���� object�� �����⤊���� String ����ȯ �۾��� �ʿ��ϴ�.
			modiM.setPw(modiPw.getText());
			modiM.setAddr(modiAddr.getText());
			modiM.setName(modiName.getText());
		
			MDAO.ModiM(modiM);
			JOptionPane.showMessageDialog(this, "������ �Ϸ�Ǿ����ϴ�.");
			
			modiPw.setText("");
			modiAddr.setText("");
			modiName.setText("");
			
			for(int i=0; i<model.getRowCount();) {
				model.removeRow(i);
			} //���̺� ������ �� �ʱ�ȭ �����ֱ� Ʃ���ϳ��� �����Ǹ� ���� �и��� i++�� ������ �ʰ� �����Ͱ� ������������ 0�� �ุ �������ָ� �ȴ�. 
			this.remove(center);
			this.remove(center2);
			this.remove(center3);
			this.remove(center5);
			
			this.add(center4, "Center");
			
			MDAO.prtM(model);//������ ���� �Ű������� �ѱ�� ��� ȸ���� ������ �������ش�.
			
			this.repaint();   // �����ٰ� ȭ���� �ٽ� �׷���.
			this.setVisible(true);
			
		}
	}
	
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(shopList)) {
			String shop = shopList.getSelectedItem();
			
			Del_DTO w = DDAO.selectOne(shop);
			
			shopinfo.setText("");
			shopinfo.append("���� : "+w.getKind().toUpperCase()+"\n");
			shopinfo.append("�޴� : "+w.getFname()+"\n");
			shopinfo.append("���� : "+w.getPrice()+"\n");
			shopinfo.append("�ּ� : "+w.getAddr()+"\n");
			
			center2_1.add(shopinfo); //�������� ����
			center2_1.add(modi); //������ư ����
			sName=shop;
			this.repaint(); 			
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		colV = table.getSelectedColumn(); //Ŭ���� ���� ���� ��������
		rowV = table.getSelectedRow(); //Ŭ���� ���� �ప ��������
		
		if(colV==0) { //id�� Ŭ���������� ���â�� �߱� ���Ѵ�. idĮ���� �Ǿտ� ��ġ���ֱ� ������ id�� Ŭ���ϸ� �׶��� ������ 0�̴�. 
			int ans=JOptionPane.showConfirmDialog
			(null, table.getModel().getValueAt(rowV, colV)+" ȸ���� Ż���Ű�ڽ��ϱ�?", 
			"ȸ��Ż��", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			//YES_NO_OPTION���� �� �ƴϿ� �����ϴ� ���â�� ���.
			// �̶�  ���� ������ �Ǹ� 0�� ���ϵǰ� �ƴϿ��� ������ 1�� ���ϵȴ�. 
		
			if(ans==0) { //���� ��������  �ش� id�� ������̺��� delete�ϴ� �������� ����ȴ�. 
			MDAO.dropM(table.getModel().getValueAt(rowV, colV));
			JOptionPane.showMessageDialog(this,"ȸ���� Ż��Ǿ����ϴ�.");
			for(int i=0; i<model.getRowCount();) {
				model.removeRow(i);
			}
			MDAO.prtM(model);//������ ���� �Ű������� �ѱ�� ��� ȸ���� ������ �������ش�.
			
			this.repaint();   // �����ٰ� ȭ���� �ٽ� �׷���.
			this.setVisible(true);
			}		
		}
		
		
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
