package com.hakankanmaz.pano;

import java.io.Serializable;

public class Duyurular implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String strtarih, strDuyuru, strKullaniciAdi;

	public Duyurular(){
		super();
	}
	
	public Duyurular(String strtarih,String strDuyuru, String strKullaniciAdi){
		super();
		this.strKullaniciAdi=strKullaniciAdi;
		this.strDuyuru=strDuyuru;
		this.strtarih=strtarih;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStrtarih() {
		return strtarih;
	}

	public void setStrtarih(String strtarih) {
		this.strtarih = strtarih;
	}

	public String getStrDuyuru() {
		return strDuyuru;
	}

	public void setStrDuyuru(String strDuyuru) {
		this.strDuyuru = strDuyuru;
	}

	public String getStrKullaniciAdi() {
		return strKullaniciAdi;
	}

	public void setStrKullaniciAdi(String strKullaniciAdi) {
		this.strKullaniciAdi = strKullaniciAdi;
	}


	
	
	
}
