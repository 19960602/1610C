package wangyaowei.bw.com.xiangmu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;

import wangyaowei.bw.com.xiangmu.R;
import wangyaowei.bw.com.xiangmu.bean.JsonBean;

/*
 *********************************
 *作者:wangyaowei
 *时间:2019/3/13  19:22
 **
 *
 *********************************
 */public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
     private Context context;
     private ArrayList<JsonBean.ResultsBean> list;

    public MyAdapter(Context context, ArrayList<JsonBean.ResultsBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_layout,null,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.type.setText(list.get(i).getType());
        myViewHolder.who.setText(list.get(i).getWho());
        Gson gson = new Gson();

        Glide.with(context).load(list.get(i).getUrl()).into(myViewHolder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView who;
        private final TextView type;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            type = itemView.findViewById(R.id.type);
            who = itemView.findViewById(R.id.who);
        }
    }
}
