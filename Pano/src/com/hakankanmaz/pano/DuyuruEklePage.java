package com.hakankanmaz.pano;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.support.v7.app.ActionBarActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DuyuruEklePage extends ActionBarActivity {

	EditText txtDuyuru;
	Database db;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
	Date date = new Date();
	String tarih = sdf.format(date);
	String saat = sdf2.format(date);
	String strAdSoyad;
	public static final String PREFS_NAME = "LoginPrefs";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_duyuru_ekle_page);
		
		txtDuyuru=(EditText) findViewById(R.id.txtDuyuru);
		
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        strAdSoyad= settings.getString("AdSoyad", "").toString();
            
        setTitle(strAdSoyad);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.duyuru_ekle_page, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.btnKaydet:
			db=new Database(getApplicationContext());
			
			if(txtDuyuru.getText().toString().equals("")){
				Toast.makeText(getApplicationContext(), "Lütfen Duyuruyu Giriniz.", Toast.LENGTH_LONG).show();	
			}
			else{
			db.duyuruEkle(new Duyurular(tarih+" "+saat, txtDuyuru.getText().toString(),strAdSoyad));
			Toast.makeText(getApplicationContext(), "Duyuru Baþarýyla Kaydedildi", Toast.LENGTH_LONG).show();
			}
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
