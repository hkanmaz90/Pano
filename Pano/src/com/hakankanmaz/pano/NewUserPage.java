package com.hakankanmaz.pano;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewUserPage extends ActionBarActivity implements OnClickListener {

	EditText txtKullaniciAdi, txtAdSoyad, txtSifre, txtSifre2;
	Button btnKaydet;
	Database db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_user_page);
		
		txtKullaniciAdi=(EditText) findViewById(R.id.txtKullaniciAdi);
		txtAdSoyad=(EditText) findViewById(R.id.txtAdSoyad);
		txtSifre=(EditText) findViewById(R.id.txtSifre);
		txtSifre2=(EditText) findViewById(R.id.txtSifre2);
		btnKaydet=(Button) findViewById(R.id.btnKullaniciEkle);
		
		btnKaydet.setOnClickListener(this);
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_user_page, menu);
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
		case R.id.btnKullaniciEkle:
			db=new Database(getApplicationContext());
			
			if(txtKullaniciAdi.getText().toString().equals("") || txtAdSoyad.getText().toString().equals("") || txtSifre.getText().toString().equals("") || txtSifre2.getText().toString().equals("")){
				Toast.makeText(getApplicationContext(), "Boþ Alanlarý Doldurunuz", Toast.LENGTH_LONG).show();	
			}
			else if(!txtSifre.getText().toString().equals(txtSifre2.getText().toString())){
				Toast.makeText(getApplicationContext(), "Þifreleriniz Uyumsuz", Toast.LENGTH_LONG).show();				
			}
			else{
			db.KullaniciEkle(new Kullanicilar(txtKullaniciAdi.getText().toString(), txtAdSoyad.getText().toString(), txtSifre.getText().toString()));
			Toast.makeText(getApplicationContext(), "Bilgiler Baþarýyla Kaydedildi", Toast.LENGTH_LONG).show();
			}
			break;

		default:
			break;
		}
		
	}
}
