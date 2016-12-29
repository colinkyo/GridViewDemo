package com.example.a7yan.gridviewdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {
    private GridView gv;
    private List<Map<String,Object>> list;
    private  List<Map<String,Object>> list1;
    private int[] images={R.mipmap.icon1,R.mipmap.icon2,R.mipmap.icon3,R.mipmap.icon4,R.mipmap.icon5,R.mipmap.icon7};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        gv = (GridView) findViewById(R.id.gv);
        list =new ArrayList<Map<String,Object>>();
        list1 = new ArrayList<Map<String,Object>>();
        for(int i=0;i<5;i++){
            Map<String,Object> map= new HashMap<String,Object>();
            map.put("img",images[i]);
            map.put("text","头像"+i);
            list.add(map);
        }
        Toast.makeText(this, "list.size():" + list.size(), Toast.LENGTH_SHORT).show();
        MyBaseAdapter adapter=new MyBaseAdapter();
       gv.setAdapter(adapter);
       gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent();
               intent.putExtra("imageId",images[position]);
               setResult(Activity.RESULT_OK,intent);
               ResultActivity.this.finish();
           }
       });
    }
    class MyBaseAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
            if(convertView==null)
            {
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView =inflater.inflate(R.layout.grid_view,null);
                holder = new ViewHolder();
                holder.iv= (ImageView) convertView.findViewById(R.id.iv);
                holder.tv= (TextView) convertView.findViewById(R.id.tv);
                convertView.setTag(holder);

            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            holder.tv.setText((CharSequence) list.get(position).get("text"));
            holder.iv.setImageResource((Integer) list.get(position).get("img"));
            return convertView;
        }
    }
    static  class ViewHolder{
        ImageView iv;
        TextView tv;
    }
}
