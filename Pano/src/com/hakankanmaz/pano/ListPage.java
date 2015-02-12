package com.hakankanmaz.pano;


import java.util.List;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class ListPage extends ActionBarActivity{

	ListView listDuyurular;
	Duyurular duyurular;
	Database db;
	public String strAdSoyad;
	List<Duyurular> tumDuyurular;
	ListPageAdapter myListAdapter;
	public static final String PREFS_NAME = "LoginPrefs";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_page);
		
		listDuyurular = (ListView) findViewById(R.id.duyuruListesi);
		
		
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        strAdSoyad= settings.getString("AdSoyad", "").toString();
            
        setTitle(strAdSoyad);
        
        
		db=new Database(getApplicationContext());
	
		
		tumDuyurular = db.tumDuyuruListele();
		if(tumDuyurular.size()==0){
			Toast.makeText(getApplicationContext(), "kayýt Yok", Toast.LENGTH_LONG).show();
		}
		else{
		myListAdapter = new ListPageAdapter(ListPage.this,tumDuyurular);
		listDuyurular.setAdapter(myListAdapter);
		
		}
		
		
		
		listDuyurular.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) {
            	duyurular=(Duyurular) myListAdapter.getItem(position);
            	
                Intent gotodetay = new Intent(ListPage.this, DetayDuyurularPage.class);
                Bundle gidenveri=new Bundle();
                gidenveri.putInt("id", duyurular.getId());
				gidenveri.putString("adsoyad",duyurular.getStrKullaniciAdi());
				gidenveri.putString("tarih",duyurular.getStrtarih());
				gidenveri.putString("duyuru",duyurular.getStrDuyuru());
				gotodetay.putExtras(gidenveri);
                startActivity(gotodetay);
            }
        }); 
		
	/*	listDuyurular.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent,
					View view, int position, long id) {
				Duyurular duyurular=(Duyurular) myListAdapter.getItem(position);
				db.duyuruSil(duyurular.getId());
				return false;
			}
        }); */
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_page, menu);
	
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.btnYeniDuyuru:
			Intent gotoDuyuruEkle=new Intent(ListPage.this, DuyuruEklePage.class);
			startActivity(gotoDuyuruEkle);
			break;

		case R.id.btnLogout:
			SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.remove("AdSoyad");
            editor.remove("logged");
            editor.commit();
            finish();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	
	
}
