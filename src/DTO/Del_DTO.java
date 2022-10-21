package DTO;

public class Del_DTO {
	
	private String kind=null;
	private String shop=null;
	private String fname=null;
	private int price;
	private String addr=null;
	private double score;
	private double  inputscore;
	private double allscore;
	
	public double getAllscore() {
		return allscore;
	}
	public void setAllscore(double allscore) {
		this.allscore = allscore;
	}
	public double getInputscore() {
		return inputscore;
	}
	public void setInputscore(double inputscore) {
		this.inputscore = inputscore;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
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

	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
	
	
	
	
	
	
}
