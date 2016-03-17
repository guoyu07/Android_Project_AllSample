package com.example.demo5;

import java.util.List;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("ViewHolder")
public class MyAdapter extends BaseAdapter{

	List<String> imagUrls;
	Context context;
	LayoutInflater inflater;
	
	public MyAdapter(Context c,List<String> i) {
		this.imagUrls=i;
		this.context=c;
	}
	
	
	@Override
	public int getCount() {
		return imagUrls.size();
	}

	@Override
	public Object getItem(int position) {
		return imagUrls.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("deprecation")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		inflater = LayoutInflater.from(context);
		View view=inflater.inflate(R.layout.picitem, null);

		final ImageView iv2= (ImageView) view.findViewById(R.id.iv2);
		
		ImageRequest request = new ImageRequest(imagUrls.get(position),
				new Response.Listener<Bitmap>() {
					public void onResponse(Bitmap bitmap) {
						iv2.setImageBitmap(bitmap);
					}
				}, 0, 0, android.graphics.Bitmap.Config.RGB_565, new Response.ErrorListener() {
					public void onErrorResponse(VolleyError error) {
//						iv2.setImageResource(R.drawable.ic_launcher);
					}
				});
		VolleySingleton.getVolleySingleton(context.getApplicationContext()).addToRequestQueue(request);
		
		
		
//		TextView tv3=(TextView) view.findViewById(R.id.tv3);
//		tv3.setText(imagUrls.get(position));
		
		
		
		return view;
	}

	
}
