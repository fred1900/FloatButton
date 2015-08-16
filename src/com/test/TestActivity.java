package com.test;

import java.util.ArrayList;
import java.util.List;

import com.faizmalkani.floatingactionbutton.FloatingActionButton;
import com.test.adapter.ExpandableListAdapter;
import com.test.adapter.FoodAdapter;
import com.test.floatbutton.widget.MyScrollView;
import com.test.floatbutton.widget.MyScrollView.OnScrollListener;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

public class TestActivity extends Activity implements OnScrollListener,
		OnClickListener {
	/** Called when the activity is first created. */
	private MyScrollView main_scrollview;
	private FloatingActionButton mFab;
	private int highMyScrollView = 800;
	private ListView listview;
	private ListView listview2;
	private ListView listview3;
	private RadioButton radioButton1;
	private RadioButton radioButton2;
	private RadioButton radioButton3;
	private RadioButton radioButton4;
	private LinearLayout navigationLayout;
	private List<RadioButton> radioButtonList;
	private int lastPosition;
	private LinearLayout navigation1, navigation2;
	private RadioGroup mainHead;
	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				Bundle b = msg.getData();
				lastPosition = b.getInt("lastPosition");
				break;
			}
			super.handleMessage(msg);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
		main_scrollview = (MyScrollView) this.findViewById(R.id.ScrollView);
		List<String> list = new ArrayList<String>();
		list.add("jiangxi");
		list.add("zhege");
		list.add("sfsdf");
		list.add("sfsdf");
		list.add("sfsdf");
		list.add("sfsdf");
		FoodAdapter foodAdapter = new FoodAdapter(list, getBaseContext());
		listview2.setAdapter(foodAdapter);
		listview2.setDivider(null);
		listview.setAdapter(foodAdapter);
		listview3.setAdapter(foodAdapter);
		main_scrollview.setOnScrollListener(this);
		mFab = (FloatingActionButton) findViewById(R.id.fabbutton);
		mFab.setVisibility(View.INVISIBLE);
		mFab.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				int scrollTo = highMyScrollView;
				main_scrollview.scrollTo(0, 500);
				mFab.hide(true);
				Toast.makeText(TestActivity.this, "哈哈哈哈哈哈哈哈哈", 1).show();
			}
		});
	}

	private List<String> getData() {
		List<String> data = new ArrayList<String>();
		data.add("测试数据3");
		data.add("测试数据3");
		data.add("测试数据3");
		data.add("测试数据3");
		data.add("测试数据3");
		data.add("测试数据3");
		data.add("测试数据3");
		data.add("测试数据3");
		data.add("测试数据3");
		data.add("测试数据3");
		data.add("测试数据3");
		data.add("测试数据3");
		data.add("测试数据3");
		data.add("测试数据3");
		data.add("测试数据3");
		return data;
	}

	private List<String> getData2() {
		List<String> data = new ArrayList<String>();
		data.add("a");
		data.add("b");
		data.add("c");
		data.add("d");
		data.add("e");
		data.add("a");
		data.add("b");
		data.add("c");
		data.add("d");
		data.add("e");
		return data;
	}

	public void init() {
		listview = (ListView) this.findViewById(R.id.listview);
		listview2 = (ListView) this.findViewById(R.id.listview2);
		listview3 = (ListView) this.findViewById(R.id.listview3);
		radioButton1 = (RadioButton) this.findViewById(R.id.radioh_one);
		radioButton2 = (RadioButton) this.findViewById(R.id.radioh_two);
		radioButton3 = (RadioButton) this.findViewById(R.id.radioh_three);
		radioButton4 = (RadioButton) this.findViewById(R.id.radioh_four);
		navigation1 = (LinearLayout) findViewById(R.id.Navigation1);
		navigation2 = (LinearLayout) findViewById(R.id.Navigation2);
		mainHead = (RadioGroup) findViewById(R.id.main_head);
		radioButtonList = new ArrayList<RadioButton>();
		radioButtonList.add(radioButton1);
		radioButtonList.add(radioButton2);
		radioButtonList.add(radioButton3);
		radioButtonList.add(radioButton4);
		radioButton1.setOnClickListener(this);
		radioButton2.setOnClickListener(this);
		radioButton3.setOnClickListener(this);
	}

	public void onScroll(int scrollY) {
		// TODO Auto-generated method stub
		if (scrollY >= navigation1.getTop()) {
			if (mainHead.getParent() == navigation1) {
				navigation1.removeView(mainHead);
				navigation2.addView(mainHead);
			}
		} else {
			if (mainHead.getParent() == navigation2) {
				navigation2.removeView(mainHead);
				navigation1.addView(mainHead);
			}
		}
		if (scrollY > highMyScrollView + 10) {
			mFab.setVisibility(View.VISIBLE);
			mFab.hide(false);
		} else if (scrollY < highMyScrollView) {
			mFab.hide(true);
		}
		Message msg = new Message();
		Bundle data = msg.getData();
		data.putInt("lastPosition", scrollY);
		msg.setData(data);
		msg.what = 0;
		myHandler.sendMessageDelayed(msg, 500);
		updateRadioButton(scrollY);
	}

	private void updateRadioButton(int y) {
		// TODO Auto-generated method stub
		if (y > listview.getTop() && y < listview2.getTop())
			chooseRadioButton(radioButton1);
		else if (y > listview2.getTop() && y < listview3.getTop())
			chooseRadioButton(radioButton2);
		else if (y > listview3.getTop()) {
			chooseRadioButton(radioButton3);
		}
	}

	private void chooseRadioButton(RadioButton radioButton) {
		// TODO Auto-generated method stub
		for (int i = 0; i < radioButtonList.size(); i++) {
			if (radioButton != radioButtonList.get(i))
				radioButtonList.get(i).setChecked(false);
			else {
				radioButtonList.get(i).setChecked(true);
			}
		}
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.radioh_one:
			main_scrollview.scrollTo(0, listview.getTop());
			break;
		case R.id.radioh_two:
			main_scrollview.scrollTo(0, listview2.getTop() - 150);
			break;
		case R.id.radioh_three:
			main_scrollview.scrollTo(0, listview3.getTop() - 150);
		default:
			break;
		}

	}
}