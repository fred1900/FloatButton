package com.test.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public abstract class MyBaseAdapter<T> extends BaseAdapter {
	public List<T> list;
	public Context context;
	public LayoutInflater inflater;
	
	
	
	public MyBaseAdapter(List<T> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}
	
	
	
	public int getCount() {
		return list != null && !list.isEmpty() ? list.size() : 0;
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		return createView(position, convertView, parent);
	}

	public abstract View createView(int position, View convertView, ViewGroup parent);

}
