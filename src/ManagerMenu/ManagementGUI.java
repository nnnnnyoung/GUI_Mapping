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
	private JLabel titleLB= new JLabel("관리자모드");
	
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
	
	private JLabel kindLB=new JLabel("음식 카테고리");
	private JLabel shopLB=new JLabel("상호명");
	private JLabel fnameLB=new JLabel("메뉴명");
	private JLabel priceLB=new JLabel("가격");
	private JLabel addrLB=new JLabel("주소");
	
	private JTextArea addrSh=new JTextArea(100,30);
	
	private JTextField shopInput=new JTextField(10);
	private JTextField fnameInput =new JTextField(10);
	private JTextField priceInput=new JTextField(10);
	private JTextField addrInput=new JTextField(10);
		
	private JLabel ModiNameLB=new JLabel("이름");
	private JLabel ModiPwLB=new JLabel("비밀번호");
	private JLabel ModiAddrLB=new JLabel("주소");
	
	private JTextField modiName=new JTextField(7);
	private JTextField modiPw=new JTextField(7);
	private JTextField modiAddr=new JTextField(7);
	
	
	private JTextField searchF=new JTextField(10);
	
	private String kind []= {"chicken","pizza","cafe"}; //콤보박스에 넣을 자료들

	private JComboBox<String> kindbox; //내가 미리 담아둔 자료만 선택 가능하도록 담는 박스
	
    private JButton input=new JButton("식당등록");
    private JButton sList=new JButton("식당목록");
    private JButton mList=new JButton("회원목록");
    private JButton sInput=new JButton("등록");
    private JButton search=new JButton("주소검색");
    private JButton modiM=new JButton("수정");
    
    
    private String sName=null;
	
	private JPanel south=new JPanel();
	private JButton logout=new JButton("로그아웃");
	
	private JButton modi=new JButton("수정하기");	

	private List shopList = new List(10);
	private JTextArea shopinfo = new JTextArea(10,3);

	
	private Object ob[][]=new Object[0][4]; //j테이블에 행 셋팅
	private String col[]= { "ID","비밀번호","이름","주소"};//j테이블에 컬럼명 셋팅
	
	private DefaultTableModel model; //table에 바로 데이터를 입력하면 수정시마다 테이블을 새로 만들어야하기 때문에 데이터 모델을 따로 만들어준다.
	private JTable table; //테이블 선언
	private JScrollPane js;//스크롤에 테이블을 집어넣어야 한다. 
	
	
	
	public void GUI() {
		this.setVisible(true);
		this.setBounds(300, 300, 700, 700);
		
		kindbox = new JComboBox<String>(kind); //kind 배열에 있는 단어들을 콤보박스에 넣어줌
		
		north.setLayout(new GridLayout(2,1));
		north1.add(titleLB);
		north.add(north1);

		north.add(north2);
		
        model=new DefaultTableModel(ob, col); //1)데이저 저장[][], 2)컬럼명
        table=new JTable(model); // 테이블에 데이터 넣어주기
        js=new JScrollPane(table); //스크롤에 테이블 넣어주기

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
		center3_1.add(kindbox); //업종은 텍스트로 입력받지 않고 내가 지정한것들만 선택되게 해줌
		
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
		
		table.addMouseListener(this); //마우스리스너 적용
		
		shopList.addItemListener(this);
		
	}

	// gui 실행시키는 메서드
	
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
			this.add(center3, "Center"); //center3에 가게 input할 수 있게 셋팅해두고 input클릭시 center에 그려지게 함
			this.repaint();   // 램에다가 화면을 다시 그려라.
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
				shopList.add(w.getShop()); //리스트에 항목 추가..
			}
			this.repaint();   // 램에다가 화면을 다시 그려라.
			this.setVisible(true);
		}else if(temp.equals(mList)) {
			for(int i=0; i<model.getRowCount();) {
				model.removeRow(i);
			} //테이블 데이터 값 초기화 시켜주기 튜플하나가 삭제되면 위로 밀리니 i++은 해주지 않고 데이터가 없어질때까지 0번 행만 삭제해주면 된다. 
			this.remove(center);
			this.remove(center2);
			this.remove(center3);
			this.remove(center5);
			
			this.add(center4, "Center");
			
			MDAO.prtM(model);//데이터 모델을 매개변수로 넘기고 모든 회원의 정보를 저장해준다.
			
			this.repaint();   // 램에다가 화면을 다시 그려라.
			this.setVisible(true);
			
		}else if(temp.equals(modi)) {
			ModiGUI mGui=new ModiGUI(sName);
			//선택한 가게의 정보 수정
			mGui.viewFrame();
			
		}else if(temp.equals(search)) {
			addrSh.setText("");
			String sh=searchF.getText();//검색할 단어 입력
			this.remove(center);
			this.remove(center2);
			this.remove(center3);
			this.remove(center4);
			this.add(center5, "Center");
			
			if(!MDAO.searsh(sh,addrSh)) { //입력한 주소 sh와 addrSh를 매개변수로 넘겨 sh와 주소가 같은 회원을 addrShdp에 담아온다.
				addrSh.setText("검색결과가 없습니다.");
			}
			this.repaint();   // 램에다가 화면을 다시 그려라.
			this.setVisible(true);		
			searchF.setText("");
			
		}else if(temp.equals(sInput)) {
			String kind=kindbox.getSelectedItem().toString(); 
			//콤보박스에 있는 정보는 자료형 Object를 가지기 때문에 String으로 변환 해준다.
			String shop=shopInput.getText();
			String addr=addrInput.getText();
			String fname=fnameInput.getText();
			String price=priceInput.getText();
			
			Del_DTO newShop=new Del_DTO(); //새로운 가게 객체 생성, 정보 넣어줌
			newShop.setKind(kind);
			newShop.setFname(fname);
			newShop.setAddr(addr);
			newShop.setPrice(Integer.parseInt(price));
			newShop.setShop(shop);
			
			DDAO.inputShop(newShop);//Del_DTO 객체의 변수를 매개변수로 넘겨주어 쿼리문에 사용
			JOptionPane.showMessageDialog(this, "가게등록이 완료 되었습니다");
			
			shopInput.setText("");//텍스트 필드의 값 지워주기
			addrInput.setText("");
			fnameInput.setText("");
			priceInput.setText("");
			this.repaint(); 	
		}else if(temp.equals(modiM)) {
			Member_DTO modiM=new Member_DTO();
			
			modiM.setId((String) table.getModel().getValueAt(rowV, 0)); 
			//테이블의 value값은 object로 나오기쨰문에 String 형변환 작업이 필요하다.
			modiM.setPw(modiPw.getText());
			modiM.setAddr(modiAddr.getText());
			modiM.setName(modiName.getText());
		
			MDAO.ModiM(modiM);
			JOptionPane.showMessageDialog(this, "수정이 완료되었습니다.");
			
			modiPw.setText("");
			modiAddr.setText("");
			modiName.setText("");
			
			for(int i=0; i<model.getRowCount();) {
				model.removeRow(i);
			} //테이블 데이터 값 초기화 시켜주기 튜플하나가 삭제되면 위로 밀리니 i++은 해주지 않고 데이터가 없어질때까지 0번 행만 삭제해주면 된다. 
			this.remove(center);
			this.remove(center2);
			this.remove(center3);
			this.remove(center5);
			
			this.add(center4, "Center");
			
			MDAO.prtM(model);//데이터 모델을 매개변수로 넘기고 모든 회원의 정보를 저장해준다.
			
			this.repaint();   // 램에다가 화면을 다시 그려라.
			this.setVisible(true);
			
		}
	}
	
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(shopList)) {
			String shop = shopList.getSelectedItem();
			
			Del_DTO w = DDAO.selectOne(shop);
			
			shopinfo.setText("");
			shopinfo.append("업종 : "+w.getKind().toUpperCase()+"\n");
			shopinfo.append("메뉴 : "+w.getFname()+"\n");
			shopinfo.append("가격 : "+w.getPrice()+"\n");
			shopinfo.append("주소 : "+w.getAddr()+"\n");
			
			center2_1.add(shopinfo); //가게정보 셋팅
			center2_1.add(modi); //수정버튼 셋팅
			sName=shop;
			this.repaint(); 			
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		colV = table.getSelectedColumn(); //클릭한 셀의 열값 가져오기
		rowV = table.getSelectedRow(); //클릭한 셀의 행값 가져오기
		
		if(colV==0) { //id를 클릭했을때만 경고창이 뜨길 원한다. id칼럼이 맨앞에 위치해있기 때문에 id를 클릭하면 그때의 열값은 0이다. 
			int ans=JOptionPane.showConfirmDialog
			(null, table.getModel().getValueAt(rowV, colV)+" 회원을 탈퇴시키겠습니까?", 
			"회원탈퇴", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			//YES_NO_OPTION으로 예 아니오 선택하는 경고창이 뜬다.
			// 이때  예를 누르게 되면 0이 리턴되고 아니오를 누르면 1이 리턴된다. 
		
			if(ans==0) { //예를 눌렀을때  해당 id를 멤버테이블에서 delete하는 쿼리문이 실행된다. 
			MDAO.dropM(table.getModel().getValueAt(rowV, colV));
			JOptionPane.showMessageDialog(this,"회원이 탈퇴되었습니다.");
			for(int i=0; i<model.getRowCount();) {
				model.removeRow(i);
			}
			MDAO.prtM(model);//데이터 모델을 매개변수로 넘기고 모든 회원의 정보를 저장해준다.
			
			this.repaint();   // 램에다가 화면을 다시 그려라.
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
