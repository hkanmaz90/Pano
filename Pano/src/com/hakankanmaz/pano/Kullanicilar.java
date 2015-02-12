package com.hakankanmaz.pano;

import java.io.Serializable;

public class Kullanicilar implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String strKulAdi, strAdSoyad, strSifre;
	
	public Kullanicilar(){
		super();
	}
	
	public Kullanicilar(String strKulAdi,String  strAdSoyad,String  strSifre){
		super();
		this.strAdSoyad=strAdSoyad;
		this.strKulAdi=strKulAdi;
		this.strSifre=strSifre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStrKulAdi() {
		return strKulAdi;
	}

	public void setStrKulAdi(String strKulAdi) {
		this.strKulAdi = strKulAdi;
	}

	public String getStrAdSoyad() {
		return strAdSoyad;
	}

	public void setStrAdSoyad(String strAdSoyad) {
		this.strAdSoyad = strAdSoyad;
	}

	public String getStrSifre() {
		return strSifre;
	}

	public void setStrSifre(String strSifre) {
		this.strSifre = strSifre;
	}
}
