package com.test.adapter;

import java.util.ArrayList;
import java.util.List;
import com.test.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

/**
* @author IdeasAndroid 
* ��չ�����������б�ʾ��
*/
public class ExpandableListAdapter extends BaseExpandableListAdapter {

        private Context mContext = null;
        private LayoutInflater mLayoutInflater;  
        // �������ݣ�����ʱ�����������ݿ⣬����....
        private String[] groups = { " " };
        private String[] familis = {  };
       
        private List<String> groupList = null;
        private List<List<String>> itemList = null;
        public ExpandableListAdapter(Context context) {
                this.mContext = context;
                groupList = new ArrayList<String>();
                itemList = new ArrayList<List<String>>();
                mLayoutInflater = (LayoutInflater)      
               		 mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
                initData();
        }
        public void setChildData(List<List<String>> list)
        {
        	itemList=list;
        }
 
        /**
         * ��ʼ�����ݣ���������ݷŵ�List�У����㴦��
         */
        private void initData() {
                for (int i = 0; i < groups.length; i++) {
                        groupList.add(groups[i]);
                }
                List<String> item1 = new ArrayList<String>();
                for (int i = 0; i < familis.length; i++) {
                        item1.add(familis[i]);
                }
                itemList.add(item1);
        }
 
        public boolean areAllItemsEnabled() {
                return false;
        }
        /*
         * �����ӽڵ�������¼�����ʱ���صĶ��󣬿ɴ��һЩ����
         */
        public Object getChild(int groupPosition, int childPosition) {
                return itemList.get(groupPosition).get(childPosition);
        }

        public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
        }

        /*
         * �ֽڵ���ͼ������������ʾһ���ı�����
         */
        @SuppressLint("NewApi")
		public View getChildView(int groupPosition, int childPosition,
                        boolean isLastChild, View convertView, ViewGroup parent) {
        	 ChildViewHolder childViewHolder;  
             View view = convertView;  
             int i1 = getChildType(groupPosition, childPosition);  
             if (view == null) {  
                 childViewHolder = new ChildViewHolder(); 
                  
                 view = mLayoutInflater.inflate(R.layout.item_expand_child, null);  
                 view.setTag(childViewHolder);   
             }else {  
                 childViewHolder = (ChildViewHolder)view.getTag();  
             }  
             childViewHolder.activityDetail=(TextView) view.findViewById(R.id.acticity_detail);
             childViewHolder.activityDetail.setText(getChild(groupPosition, childPosition)
                     .toString());
             return view;  
         }  
       
         class ChildViewHolder{  
             TextView activityDetail;  
         }  

        /*
         * ���ص�ǰ������ֽڵ����
         */
        public int getChildrenCount(int groupPosition) {
                return itemList.get(groupPosition).size();
        }

        /*
         * ���ط����������һЩ���ݴ��ݣ����¼�����ʱ��ֱ��ȡ�úͷ�����ص�����
         */
        public Object getGroup(int groupPosition) {
                return groupList.get(groupPosition);
        }

        /*
         * ����ĸ���
         */
        public int getGroupCount() {
                return groupList.size();
        }

        public long getGroupId(int groupPosition) {
                return groupPosition;
        }

        /*
         * ������ͼ������Ҳ��һ���ı���ͼ
         */
        public View getGroupView(int groupPosition, boolean isExpanded,
                        View convertView, ViewGroup parent) {
                TextView text = null;
                if (convertView == null) {
                        text = new TextView(mContext);
                } else {
                        text = (TextView) convertView;
                }
                String name = (String) groupList.get(groupPosition);
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                                0,  0);
                text.setLayoutParams(lp);
                text.setTextSize(0);
                text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                text.setPadding(0, 0, 0, 0);
                text.setText(name);
                return text;
        }

        /*
         * �жϷ����Ƿ�Ϊ�գ���ʾ���������ǹ̶��ģ����Բ���Ϊ�գ����Ƿ���false 
         * ��������������ݿ⣬����ʱ�����԰��ж��߼�д����������У����Ϊ��
         * ʱ����true
         */
        public boolean isEmpty() {
                return false;
        }

        /*
         * �����б�ʱҪ����Ķ����������
         */
        public void onGroupCollapsed(int groupPosition) {

        }

        /*
         * չ���б�ʱҪ����Ķ����������
         */
        public void onGroupExpanded(int groupPosition) {

        }

        /*
         * Indicates whether the child and group IDs are stable across changes to
         * the underlying data.
         */
        public boolean hasStableIds() {
                return false;
        }

        /*
         * Whether the child at the specified position is selectable.
         */
        public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
        }
}