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
	private JLabel bnum= new JLabel("주문번호");
	
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
	
	private JButton all=new JButton("전체");
	private JButton week=new JButton("7일이내");
	private JButton month=new JButton("30일이내");
	private JButton cancel=new JButton("주문취소");
	
	private JButton score=new JButton("별점주기");
	private JButton back=new JButton("뒤로가기");
	
	public BlistGUI(String id,Blist_DAO BDAO){
		this.id=id;
		this.BDAO=BDAO;
		north.setLayout(new GridLayout(2,1));
		JLabel titleLB= new JLabel(id+"의 주문내역");
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
		// blist 테이블에 로그인 한 id를 매개변수로 넘겨주어 해당멤버의 주문내역만 출력하고 주문번호를 ArrayList에 저장한다.
		
		for(BlistDTO b : blist) {
			buyList.add(Integer.toString(b.getBnum()));
			// ArrayList에 저장된 내용을 List에 저장한다.
			// 그런데 리스트에는 int자료형이 저장되지 않아서 String으로 변환시켜준 뒤 넣어주었다.
		}
		
		this.repaint(); //화면에 다시 그린다.
		this.setVisible(true);
	}
	
	public void weekPrt() {
		buyList.removeAll();
		center1_1.removeAll();
		ArrayList<BlistDTO> blist=BDAO.prtWeek(id);
		// blist 테이블에 로그인 한 id를 매개변수로 넘겨주어 해당멤버의 주문내역만 출력하고 주문번호를 ArrayList에 저장한다. 
		//단 쿼리문에 조건을 걸어 7일 이내의 주문내역만 출력된다.
		for(BlistDTO b : blist) {
			buyList.add(Integer.toString(b.getBnum()));
		}
		this.repaint(); 	
		this.setVisible(true);
	}
	public void monthPrt() {
		// blist 테이블에 로그인 한 id를 매개변수로 넘겨주어 해당멤버의 주문내역만 출력하고 주문번호를 ArrayList에 저장한다. 
		//단 쿼리문에 조건을 걸어 30일 이내의 주문내역만 출력된다.
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
			allPrt(); //전체보기 버튼 클릭시 allprt() 메서드 실행
		}else if(temp.equals(week)){
			weekPrt();//7일이내 버튼 클릭시 weekPrt() 메서드 실행
		}else if(temp.equals(month)){
			monthPrt();//30일이내 버튼 클릭시 monthPrt() 메서드 실행
		}else if(temp.equals(back)){
			closeFrame(); //종료 버튼 클릭시 GUI종료
		}else if(temp.equals(cancel)){
			if(BDAO.fiveMi(id,bno)) { //주문하고 5분이 지나지 않았는지 확인하는 메서드, 주문번호를 매개변수로 넘겨 메서드 실행 
				BDAO.del(bno); //5분이 지나지 않았다면 주문변호를 매개변수로 넘겨 blist 테이블에서 삭제 시킴
				JOptionPane.showMessageDialog(this, "주문취소 완료"); //취소완료 메세지
			}else if(!BDAO.fiveMi(id,bno)){
				JOptionPane.showMessageDialog(this, "취소가능 시간이 지났습니다.");//취소불가 메세지
				//주문시간과 현재시간이 5분 이상 차이가 난다면 최소불가
			}
		}else if(temp.equals(score)){
			ScoreGUI SC=new ScoreGUI(sName);
			SC.viewFrame();
		}		
	}
	
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(buyList)) {
			String bnum = buyList.getSelectedItem(); //주문번호 클릭
			int time=0; //배달시간 받아올 변수
			Image image = null; //이미지 변수
			center1_1_2.removeAll(); //시작전에 화면에 있는것을 지워주고 시작
			center1_1.removeAll();
			BlistDTO w = BDAO.selectOne(bnum); //주문번호 넘겨줌
			
			sName=w.getShop();
			
			buyinfo.setText(""); //주문번호의 내용 출력
			buyinfo.append("상호명 : "+w.getShop()+"\n");
			buyinfo.append("메뉴 : "+w.getFname()+"\n");
			buyinfo.append("가격 : "+w.getPrice()+"\n");
			buyinfo.append("주소 : "+w.getAddr()+"\n");
			buyinfo.append("주문시간 : "+w.getIndate()+"\n");
			
			
			center1_1.add(buyinfo,"North");
			time=BDAO.time(id, bnum,time); 
			//배달시간 메서드, 기본으로 배달이 40분 소요된다고 설정
			center1_1_2.add(timeArea);
			center1_1.add(center1_1_2,"Center");
			if(time==0) { //주문후 40분이 지났다면 time은 0을 리턴
				//이미 40분이 지났기때문에 timeArea에 배달완료 입력
				timeArea.setText("-------------------------------------배달완료==3------------------------------------");
			    try {
			    	//이미지에 완료 이미지 삽입
			        URL url = new URL("https://blogfiles.pstatic.net/MjAyMjEwMjBfMTg5/MDAxNjY2MjM4MzEwMDg2.55LCADfIpj246Gb_1mnpxRRe-aNNZGpFSscifEUqZaog.tX80B22pY3GNleu9O7uIi9iU3hrKbcEpvDp8gdtXA5gg.JPEG.tjs40106/KakaoTalk_20221020_125810842.jpg?type=w2");
			        image = ImageIO.read(url).getScaledInstance(100, 70, Image.SCALE_DEFAULT);
			        //이미지 크기 지정
			        JLabel imageLB= new JLabel(new ImageIcon(image));
			        //라벨에 이미지 삽입
			        center1_1_2.add(imageLB);
			        //라벨을 패널에 삽입
			        center1_1.add(score,"South");
			    } catch (IOException e1) {
			    	e1.printStackTrace();
			    }
				
			}else {
				timeArea.setText("--------------------------배달시간 "+time+"분 남았습니다 ^*^--------------------------");
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
						
			
			
			
			bno=Integer.decode(bnum); //List에 주문번호를 String으로 넣어주었기 때문에 다시 int로 바꿔준다.
			
			this.repaint(); 		
			this.setVisible(true);
		}
	}

}
