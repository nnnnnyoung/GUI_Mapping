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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import DAO.Blist_DAO;
import DAO.Del_DAO;
import DTO.Del_DTO;

public class DelGUI extends JFrame  implements ActionListener,ItemListener{
	private Del_DAO DDAO=new Del_DAO();
	private Blist_DAO BDAO=new Blist_DAO();
	
	
	private JPanel north=new JPanel();
	private JPanel north1=new JPanel();
	private JPanel north2=new JPanel();
	private JLabel titleLB= new JLabel("배달슝슝==3"); //타이틀 라벨

	
	private JPanel center=new JPanel();
	private JPanel center1=new JPanel();
	private JPanel center2=new JPanel();
	private JPanel center2_1=new JPanel();
	
	private JButton chicken=new JButton("치킨");
	private JButton pizza=new JButton("피자");
	private JButton cafe=new JButton("카페");
	
	private String sName=null; //가게이름을 저장해 줄 변수, 가게이름은 food 테이블의 pk로 주문시 쿼리문 조건에 사용한다.
	
	private JPanel south=new JPanel();
	private JButton logout=new JButton("로그아웃");
	private JButton blist=new JButton("주문내역");
	private JButton buy=new JButton("주문하기");
	
	private String id=null; //로그인 한 아이디 받아오기
	
	private List shopList = new List(10); //가게들의 이름만 저장할 리스트
	private JTextArea shopinfo = new JTextArea(10,3); //가게의 정보를 저장할 공간
	
	public DelGUI(String id) {
		this.id=id; //로그인 한 아이디를 받아온다.
	}
	
	public void GUI() {
		this.setVisible(true);
		this.setBounds(300, 300, 500, 500);
		
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
		buy.addActionListener(this);
		
		shopList.addItemListener(this); //리스트를 클릭시 이벤트가 발생할 수 있게 만들어주는 메서드, 인터페이스 ItemListener에서 가지고 옴
		
	}
	
	public void closeFrame() {
		this.setVisible(false);
		// gui 종료 메서드
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object temp= e.getSource();
		if(temp.equals(logout)) {
			closeFrame();
			LoginGUI LGUI=new LoginGUI();
			LGUI.viewFrame(); //로그아웃 버튼 클릭시 로그인 화면으로 돌아간다.
		}else if(temp.equals(blist)) {
			BlistGUI listGUI=new BlistGUI(id, BDAO);
			listGUI.viewFrame(); //주문내역 클릭시 id를 매개변수로 넘기고 BlistGUI클래스의 GUI가 실행된다.
		}else if(temp.equals(chicken)) {
			this.remove(center); // 클릭시마다 화면이 중첩될 수 있으니 화면을 지워주고 시작
			this.add(center2, "Center"); //center에 가게리스트와 가게 정보가 있는 center2패널을 배치시킨다.
			center2_1.removeAll(); //이전의 기록이 남아있을 수 있으니 전부 지우고 시작
			String addr=DDAO.getAddr(id); 
			//로그인 한 회원의 주소에 있는 가게만 띄우기 때문에 id로 회원의 주소를 불러온다.
			
			String kind="chicken"; //업종 치킨만 띄워야하기 때문에 kind는 치킨
			ArrayList<Del_DTO> SList=DDAO.selAddr(addr,kind); 
			//회원의 주소와 업종을 같이 매개변수로 넘겨준다. food 테이블에서 업종과 주소가 일치하는 가게의 이름을 ArrayList에 가지고 온다.
			shopList.removeAll(); 
			shopinfo.removeAll();

			for(int i=0; i<SList.size(); i++) {
				shopList.add(SList.get(i).getShop());
//				ArrayList에 있는내용을 List에 저장시켜준다.
			}
			
			this.repaint();   // 램에다가 화면을 다시 그려라.
			this.setVisible(true);
		}else if(temp.equals(pizza)) {
			this.remove(center);
			this.add(center2, "Center");
			center2_1.removeAll();
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
			BDAO.buy(sName, id); //주문버튼 클릭시 가게의 이름과 id를 매개변수로 넘겨주어 blist테이블에 저장시킨다.
			JOptionPane.showMessageDialog(this, "주문완료");
			
		}
		
	}
	
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(shopList)) {
			String shop = shopList.getSelectedItem(); //List에 있는 가게를 클릭시 가게 이름이 shop에 저장된다. 
			
			Del_DTO w = DDAO.selectOne(shop); //가게 이름을 매개변수로 넘겨주고 food 테이블에서 그 가게의 정보를 가지고 온다.
			
			shopinfo.setText(""); //TextArea에 남아있는 글자가 없게 하기위해 ""을 사용하여 빈칸으로 셋팅
			shopinfo.append("메뉴 : "+w.getFname()+"\n");//append 문자열 합치기 메서드를 사용하여 Area내용을 셋팅
			shopinfo.append("가격 : "+w.getPrice()+"\n");
			shopinfo.append("주소 : "+w.getAddr()+"\n");
			
			System.out.println(w.getScore());
			if(w.getScore()==0) {
				shopinfo.append("등록된 평점이 없습니다.");
			}else {
				shopinfo.append("평점 : "+w.getScore()+"\n");
			}
			
			
			center2_1.add(shopinfo); //가게 이름 클릭시 가게 정보가 center2_1에 셋팅
			center2_1.add(buy);//가게 이름 클릭시 구매버튼이  center2_1에 셋팅
			sName=shop;
			this.repaint(); //셋팅한 프레임을 다시 화면에 그려라	
		}
	}
	
	
	
}
