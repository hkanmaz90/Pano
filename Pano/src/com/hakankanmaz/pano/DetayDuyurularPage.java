package com.hakankanmaz.pano;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class DetayDuyurularPage extends ActionBarActivity {

	TextView txtDetayAdSoyad, txtDetayTarih, txtDetayDuyuru;
	public String strAdSoyad;
	public static final String PREFS_NAME = "LoginPrefs";
	int silinecekId;
	Database db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detay_duyurular_page);
		
		txtDetayAdSoyad=(TextView) findViewById(R.id.txtDetayAdSoyad);
		txtDetayTarih=(TextView) findViewById(R.id.txtDetayTarih);
		txtDetayDuyuru=(TextView) findViewById(R.id.txtDetayDuyuru);
		
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        strAdSoyad= settings.getString("AdSoyad", "").toString();
            
        setTitle(strAdSoyad);
		
		Bundle gelenveri=getIntent().getExtras();
		silinecekId=gelenveri.getInt("id");
		txtDetayAdSoyad.setText(gelenveri.getString("adsoyad"));
		txtDetayTarih.setText(gelenveri.getString("tarih"));
		txtDetayDuyuru.setText(gelenveri.getString("duyuru"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detay_duyurular_page, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.btnSil:
			DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			    @Override
			    public void onClick(DialogInterface dialog, int which) {
			        switch (which){
			        case DialogInterface.BUTTON_POSITIVE:

						db=new Database(getApplicationContext());
						db.duyuruSil(silinecekId);
						Toast.makeText(getApplicationContext(), "Kayýt Baþarýyla Silindi", Toast.LENGTH_SHORT).show();
					/*	DetayDuyurularPage.this.finish();
						Intent gotoback=new Intent(DetayDuyurularPage.this,ListPage.class);
						startActivity(gotoback); */
			            break;

			        case DialogInterface.BUTTON_NEGATIVE:
			            //No button clicked
			            break;
			        }
			    }
			};

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Duyuruyu Silmek Ýstediðinizden Eminmisiniz?").setPositiveButton("Evet", dialogClickListener)
			    .setNegativeButton("Hayýr", dialogClickListener).show();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
