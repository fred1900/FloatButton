package com.test.adapter;

import java.util.ArrayList;
import java.util.List;












import com.test.R;

import android.R.integer;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodAdapter extends MyBaseAdapter {
	private boolean isExpandAll=false;
	public FoodAdapter(List list, Context context) {
		super(list, context);
		this.list=list;
		// TODO Auto-generated constructor stub
	}

	@Override
	public View createView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder vh;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_food, null);
			vh = new ViewHolder();
			convertView.setTag(vh);
		}else {
			vh = (ViewHolder) convertView.getTag();
		}
		vh.activityList = (ExpandableListView) convertView
				.findViewById(R.id.activity_list);
		final com.test.adapter.ExpandableListAdapter expandableList = new com.test.adapter.ExpandableListAdapter(
				context);
		vh.activityList.setAdapter(expandableList);
		vh.activityList.setDivider(null);
		vh.activityList.setGroupIndicator(null);
		/*µã»÷²»ÊÕËõ*/
	 	vh.activityList.setOnGroupClickListener(new OnGroupClickListener() {
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {  
				return true;
			}
		}); 
		int groupCount = expandableList.getGroupCount();
		for (int i = 0; i < groupCount; i++) {
			vh.activityList.expandGroup(i);
		}
		vh.activityNum=(ImageView) convertView.findViewById(R.id.show_more);
		vh.activityNum.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				List<List<String>> childDataList = new ArrayList<List<String>>();
				List<String> familis=new ArrayList<String>();
				if(isExpandAll)
				{
					familis.add("100¼õ30");
					vh.activityNum.setImageResource(R.drawable.shangla);
					vh.activityNum.setDrawingCacheBackgroundColor(R.drawable.shangla);
				}else
					vh.activityNum.setImageResource(R.drawable.xiala);
				isExpandAll=!isExpandAll;
				List<String> item1 = new ArrayList<String>();
				for (int i = 0; i < familis.size(); i++) {
					item1.add(familis.get(i));
				}
				childDataList.add(item1);
				expandableList.setChildData(childDataList);
				expandableList.notifyDataSetChanged();
			}
		});
		return convertView;
	}
	private static class ViewHolder {
		private ImageView activityNum;
		private ExpandableListView activityList;
	}


}
