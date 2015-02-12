package com.hakankanmaz.pano;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class Database extends SQLiteOpenHelper {
	
	private static String DATABASENAME="PANODB";
	private static String TABLENAMEDUYURU="Duyurular";
	private static String TABLENAMEKULLANICI="Kullanicilar";
	

	public Database(Context context) {
		super(context, DATABASENAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sqlDuyuru="CREATE TABLE "+ TABLENAMEDUYURU + "(id INTEGER PRIMARY KEY AUTOINCREMENT, KullaniciAdi TEXT, Duyuru TEXT, Tarih TEXT"+")";
		db.execSQL(sqlDuyuru);
		
		String sqlKullanici="CREATE TABLE "+ TABLENAMEKULLANICI + "(id INTEGER PRIMARY KEY AUTOINCREMENT, AdSoyad TEXT, KullaniciAdi TEXT, Sifre TEXT"+")";
		db.execSQL(sqlKullanici);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		
	}
	
	public void duyuruEkle(Duyurular duyurular) {
		SQLiteDatabase db=this.getWritableDatabase();
		
		ContentValues values=new ContentValues();
		
		values.put("KullaniciAdi", duyurular.getStrKullaniciAdi());
		values.put("Duyuru", duyurular.getStrDuyuru());
		values.put("Tarih", duyurular.getStrtarih());
		
		
		db.insert(TABLENAMEDUYURU, DATABASENAME, values);
		db.close();
		
	}
	
	public void KullaniciEkle(Kullanicilar kullanicilar) {
		SQLiteDatabase db=this.getWritableDatabase();
		
		ContentValues values=new ContentValues();
		
		
		values.put("AdSoyad", kullanicilar.getStrAdSoyad());
		values.put("KullaniciAdi", kullanicilar.getStrKulAdi());
		values.put("Sifre", kullanicilar.getStrSifre());
		
		db.insert(TABLENAMEKULLANICI, DATABASENAME, values);
		db.close();
		
	}
	
	public List<Duyurular> tumDuyuruListele() {
		List<Duyurular> tumDuyurular=new ArrayList<Duyurular>();
		SQLiteDatabase db=this.getWritableDatabase();
		
		Cursor cursor=db.query(TABLENAMEDUYURU, new String[]{"id","KullaniciAdi", "Duyuru", "Tarih"} , null, null,null, null, "id desc");
		
		while (cursor.moveToNext()) {
			Duyurular duyurular=new Duyurular();
			duyurular.setId(cursor.getInt(0));
			duyurular.setStrKullaniciAdi(cursor.getString(1));
			duyurular.setStrDuyuru(cursor.getString(2));
			duyurular.setStrtarih(cursor.getString(3));
			
			
			tumDuyurular.add(duyurular);
					
		}

		return tumDuyurular;
	}
	
	public List<Kullanicilar> tumKullanicilariListele() {
		List<Kullanicilar> tumKullanicilar=new ArrayList<Kullanicilar>();
		SQLiteDatabase db=this.getWritableDatabase();
		
		Cursor cursor=db.query(TABLENAMEKULLANICI, new String[]{"id","AdSoyad", "KullaniciAdi", "Sifre"} , null, null,null, null, null);
		while (cursor.moveToNext()) {
			Kullanicilar kullanicilar=new Kullanicilar();
			
			kullanicilar.setId(cursor.getInt(0));
			kullanicilar.setStrAdSoyad(cursor.getString(1));
			kullanicilar.setStrKulAdi(cursor.getString(2));
			kullanicilar.setStrSifre(cursor.getString(3));
			tumKullanicilar.add(kullanicilar);
					
		}
		return tumKullanicilar;
	}
	
	 public HashMap<String, String> KullaniciDetay(String kulAdi, String sfr){
	        HashMap<String,String> kullanici = new HashMap<String,String>();
	        String selectQuery = "SELECT * FROM " + TABLENAMEKULLANICI + " WHERE KullaniciAdi='" + kulAdi + "' and Sifre='" + sfr + "'";
	 
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor cursor = db.rawQuery(selectQuery, null);
	        cursor.moveToFirst();
	        if(cursor.getCount() > 0){
	        	kullanici.put("AdSoyad", cursor.getString(1));
	        	kullanici.put("KullaniciAdi", cursor.getString(2));
	        	kullanici.put("Sifre", cursor.getString(3));
	        }
	        cursor.close();
	        db.close();
	        return kullanici;
	    }
	 
	 public boolean duyuruSil(int id) 
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
	     return db.delete(TABLENAMEDUYURU, "id=" + id, null) > 0;
	 }
}
