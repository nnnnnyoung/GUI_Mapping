package DTO;

public class BlistDTO {
	int bnum;
	

	String id=null; //회원의 아이디
	String shop=null;
	String fname=null; //음식이름
	int price;
	String addr=null;
	int indate;
	
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getShop() {
		return shop;
	}
	public void setShop(String shop) {
		this.shop = shop;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getIndate() {
		return indate;
	}
	public void setIndate(int indate) {
		this.indate = indate;
	}
	
	

}
