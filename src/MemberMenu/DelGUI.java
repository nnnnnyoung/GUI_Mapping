package MemberMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import DAO.Blist_DAO;
import DAO.Del_DAO;
import DTO.Del_DTO;
import sign.Buy_Complete;




public class DelGUI extends JFrame  implements ActionListener,ItemListener{
	private Del_DAO DDAO=new Del_DAO();
	private Blist_DAO BDAO=new Blist_DAO();
	
	
	private JPanel north=new JPanel();
	private JPanel north1=new JPanel();
	private JPanel north2=new JPanel();
	private JLabel titleLB= new JLabel("배달슝슝==3");
	
	private JPanel center=new JPanel();
	private JPanel center1=new JPanel();
	private JPanel center2=new JPanel();
	private JPanel center2_1=new JPanel();
	
	private JButton chicken=new JButton("치킨");
	private JButton pizza=new JButton("피자");
	private JButton cafe=new JButton("카페");
	
	private String sName=null;
	
	private JPanel south=new JPanel();
	private JButton logout=new JButton("로그아웃");
	private JButton blist=new JButton("주문내역");

	
	private JButton buy=new JButton("주문하기");
	
	private String id=null; //로그인 한 아이디 받아오기
	private List shopList = new List(10);
	private JTextArea shopinfo = new JTextArea(10,3);
	
	public DelGUI(String id) {
		this.id=id;
	}
	
	public void GUI() {
		this.setVisible(true);
		this.setBounds(100, 300, 500, 500);
		
		
		north.setLayout(new GridLayout(2,1));
		north1.add(titleLB);
		north.add(north1);
		north.add(north2);
		
		north1.setBackground(Color.LIGHT_GRAY);
		center1.setLayout(new GridLayout(1,3));
		
		north2.add(chicken);
		north2.add(pizza);
		north2.add(cafe);
		
		center.add(center1);
		south.add(blist);

		south.add(logout);
		
		center2.setLayout(new BorderLayout());
		center2.add(shopList,"West");
		center2_1.setLayout(new GridLayout(2,1));
		center2_1.add(shopinfo);

		center2.add(center2_1,"Center");
		
		
		
		this.add(south, "South");
		this.add(center, "Center");;
		this.add(north, "North");
		
		
		chicken.addActionListener(this);
		pizza.addActionListener(this);
		cafe.addActionListener(this);
		
		logout.addActionListener(this);
		blist.addActionListener(this);

		
		shopList.addItemListener(this);
		buy.addActionListener(this);
		
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
			LoginGUI LGUI=new LoginGUI();
			LGUI.viewFrame();
		}else if(temp.equals(blist)) {
			BlistGUI listGUI=new BlistGUI(id, BDAO);
			listGUI.viewFrame();
		}else if(temp.equals(chicken)) {
			
			this.remove(center);
			this.add(center2, "Center");
			center2_1.removeAll();
			String addr=DDAO.getAddr(id);
			
			String kind="chicken";
			ArrayList<Del_DTO> SList=DDAO.selAddr(addr,kind);
			shopList.removeAll(); 
			shopinfo.removeAll();

			for(int i=0; i<SList.size(); i++) {
				shopList.add(SList.get(i).getShop());
			}
			
			this.repaint();   // 램에다가 화면을 다시 그려라.
			this.setVisible(true);
		}else if(temp.equals(pizza)) {
			this.remove(center);
			this.add(center2, "Center");
			center2_1.removeAll();
			String addr=DDAO.getAddr(id);
			String kind="pizza";
			ArrayList<Del_DTO> SList=DDAO.selAddr(addr,kind);

			shopList.removeAll(); 
			for(Del_DTO w : SList) {
				shopList.add(w.getShop()); //리스트에 항목 추가..
			}
			this.repaint();   // 램에다가 화면을 다시 그려라.
			this.setVisible(true);
		}else if(temp.equals(cafe)) {
			this.remove(center);
			this.add(center2, "Center");
			String addr=DDAO.getAddr(id);
			String kind="cafe";
			ArrayList<Del_DTO> SList=DDAO.selAddr(addr,kind);

			shopList.removeAll(); 
			for(Del_DTO w : SList) {
				shopList.add(w.getShop()); //리스트에 항목 추가..
			}
			this.repaint();   // 램에다가 화면을 다시 그려라.
			this.setVisible(true);
		}else if(temp.equals(buy)) {
			BDAO.buy(sName, id);
			Buy_Complete bc=new Buy_Complete();
			bc.viewFrame();
			

		}
		
	}
	
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(shopList)) {
			String shop = shopList.getSelectedItem();
			
			Del_DTO w = DDAO.selectOne(shop);
			
			shopinfo.setText("");
			shopinfo.append("메뉴 : "+w.getFname()+"\n");
			shopinfo.append("가격 : "+w.getPrice()+"\n");
			shopinfo.append("주소 : "+w.getAddr()+"\n");
			
			center2_1.add(shopinfo);
			center2_1.add(buy);
			sName=shop;
			this.repaint(); 			
		}
	}
	
	
	
}
