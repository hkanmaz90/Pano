package com.hakankanmaz.pano;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends ActionBarActivity implements OnClickListener {

	Button btngiris, btnyenikayit;
	EditText kullaniciAdi, sifre;
	Database db;
	public static final String PREFS_NAME = "LoginPrefs";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_page);
		
		btngiris = (Button) findViewById(R.id.btnGiris);
		kullaniciAdi = (EditText) findViewById(R.id.txtKullaniciAdi);
		sifre = (EditText) findViewById(R.id.txtSifre);
		btnyenikayit = (Button) findViewById(R.id.btnYeni);
		
		btngiris.setOnClickListener(this);
		btnyenikayit.setOnClickListener(this);	
		
		 SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
	        if (settings.getString("logged", "").toString().equals("logged")) {
	            Intent intent = new Intent(LoginPage.this, ListPage.class);
	            startActivity(intent);
	        }
		

        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_page, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnGiris:
			db=new Database(getApplicationContext());
			HashMap<String, String> userMap=db.KullaniciDetay(kullaniciAdi.getText().toString(), sifre.getText().toString());
			String strAdSoyad=userMap.get("AdSoyad");
			String strKulAdi = userMap.get("KullaniciAdi");
	        String strSifre = userMap.get("Sifre");
			
			if(kullaniciAdi.getText().toString().equals(strKulAdi) && sifre.getText().toString().equals(strSifre)){
				 //make SharedPreferences object
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("logged", "logged");
                editor.putString("AdSoyad", strAdSoyad);
                editor.commit();
				
				Intent gotoList=new Intent(LoginPage.this, ListPage.class);
				startActivity(gotoList);
				
			}
			else{
				Toast.makeText(getApplicationContext(), "Hatalý Bilgi", Toast.LENGTH_LONG).show();
			}
			
			
			break;

		case R.id.btnYeni:
			Intent gotoNewUser=new Intent(LoginPage.this, NewUserPage.class);
			startActivity(gotoNewUser);
			break;
		default:
			break;
		}
		
	}
}
