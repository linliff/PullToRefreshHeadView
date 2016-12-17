package com.linlif.pulltorefresh;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.linlif.pulltorefresh.ptr.PullToRefreshBase;
import com.linlif.pulltorefresh.ptr.PullToRefreshListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeadListViewActivity extends AppCompatActivity {

    //private ArrayAdapter<String> mAdapter;
    private PullToRefreshListView girdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        initView();
    }


    Handler mHandler=new Handler()
    {
        public void handleMessage(Message msg)
        {
            switch(msg.what)
            {
                case 1:

                    girdView.onRefreshComplete();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private void initView() {
        String []data = new String[] {"android","ios","wp","java","c++","c#"};

        List<String> string = new ArrayList<>();
        string.addAll(Arrays.asList(data));

        LinearLayout headView = (LinearLayout) getLayoutInflater().inflate(R.layout.head_view , null);

        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
                AbsListView.LayoutParams.WRAP_CONTENT);

        girdView = (PullToRefreshListView) findViewById(R.id.pGridViewRights);

        girdView.setMode(PullToRefreshBase.Mode.BOTH);
        headView.setLayoutParams(layoutParams);
        ListView lv = girdView.getRefreshableView();
        lv.addHeaderView(headView);


        girdView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView)  {
                Toast.makeText(getApplicationContext(), "下拉刷新", Toast.LENGTH_SHORT).show();


               Thread thread =  new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Message message=new Message();
                        message.what=1;
                        mHandler.sendMessage(message);

                    }
                });
                thread.start();


            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Toast.makeText(getApplicationContext(), "上拉加载", Toast.LENGTH_SHORT).show();
                Thread thread =  new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Message message=new Message();
                        message.what=1;
                        mHandler.sendMessage(message);

                    }
                });
                thread.start();

            }
        });

        //mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, string);


       // girdView.setAdapter(mAdapter);
        girdView.setAdapter( new myAdapter(this ,string));
    }

    class myAdapter extends BaseAdapter{

        Context context;
        List<String> mDatas;
        private  LayoutInflater mLayoutInflater;

        public myAdapter(Context context , List<String> data){
            this.context = context;
            this.mDatas = data;
            this.mLayoutInflater =   LayoutInflater.from(this.context);
        }
        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                convertView = this.mLayoutInflater.inflate(R.layout.item, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.text.setText(this.mDatas.get(position));

            return convertView;
        }

        class ViewHolder{
            private  TextView text;
            public ViewHolder(View view) {
                text =   (TextView) view.findViewById(R.id.test);
            }
        }
    }
}
