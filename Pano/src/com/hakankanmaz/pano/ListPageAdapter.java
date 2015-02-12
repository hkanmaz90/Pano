package com.hakankanmaz.pano;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListPageAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<Duyurular> duyuruList;
	
	public ListPageAdapter(Activity activity, List<Duyurular> tumDuyurular) {
		
		mInflater = (LayoutInflater) activity.getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		
		duyuruList = tumDuyurular;
	}
	
	@Override
	public int getCount() {
		return duyuruList.size();
	}

	@Override
	public Object getItem(int position) {
		return duyuruList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		View rowView;
		rowView = mInflater.inflate(R.layout.satir_duyuru, null);
		
		TextView lblAdSoyad=(TextView) rowView.findViewById(R.id.lblAdSoyad);
		TextView lblTarih=(TextView) rowView.findViewById(R.id.lblTarih);
		TextView lblDuyuru=(TextView) rowView.findViewById(R.id.lblDuyuru);
		ImageView imgNextIcon=(ImageView) rowView.findViewById(R.id.imgNextIcon);
		
		
		Duyurular duyurular=duyuruList.get(position);
		
		
		lblAdSoyad.setText(duyurular.getStrKullaniciAdi());
		lblTarih.setText(duyurular.getStrtarih());
		
		String kisaDuyuru=duyurular.getStrDuyuru();
		if(kisaDuyuru.length()>10)
		{
			lblDuyuru.setText(kisaDuyuru.substring(0, 10)+"...");
		}
		else
		{
			lblDuyuru.setText(kisaDuyuru);
		}
		
		
		imgNextIcon.setImageResource(R.drawable.ic_action_next_item);
		
		
		
		return rowView;
		
	}
}
