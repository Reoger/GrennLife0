package com.reoger.grennlife.Recycle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.reoger.grennlife.R;
import com.reoger.grennlife.Recycle.model.OldThing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 24540 on 2016/9/15.
 * 动态的list适配器
 */
public class OldThingsAdapter extends RecyclerView.Adapter<OldThingsAdapter.OldThingHolder>{
    private Context mContext;
    private List<OldThing> mDatas = new ArrayList<>();


    public OldThingsAdapter(Context contex, List<OldThing> datas) {
        mContext = contex;
        mDatas= datas;
    }

    @Override
    public OldThingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.item_oldthing,parent,false);
        OldThingHolder holder = new OldThingHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(OldThingHolder holder, int position) {
        OldThing oldThing = mDatas.get(position);
        //设置
        if(oldThing != null){
            holder.mUserName.setText(oldThing.getAuthor().getUsername());
            holder.mContent.setText(oldThing.getContent());
            holder.mLocation.setText(oldThing.getAuthor().getLocations());
            holder.mTime.setText(oldThing.getCreatedAt()+"pp");
            holder.mPhoneNum.setText(oldThing.getNum().toString());
            if(oldThing.getImageUrl() != null){
                holder.mAddPoto.removeAllViews();
                String a = oldThing.getImageUrl();
                String b = a.substring(1,a.length()-1);
                String[] imageUrl = b.split(", ");//这里是获得一个image的url
                for (int i = 0; i < imageUrl.length; i++) {
                    final ImageView imageView = new ImageView(mContext);
                    Glide.with(mContext).load(imageUrl[i])
                            .placeholder(R.mipmap.ic_launcher)//设置占位图片
                            .error(android.R.drawable.stat_notify_error)//图片加载失败的显示
                            .crossFade()//设置淡入淡出效果
                            .override(600,300)//设置图片大小
                            .skipMemoryCache(true)
                            .into(imageView);
                    holder.mAddPoto.addView(imageView);
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    class OldThingHolder extends RecyclerView.ViewHolder{
        public TextView mUserName;
        public TextView mContent;
        public TextView mPhoneNum;
        public TextView mLocation;
        public TextView mTime;
        public LinearLayout mAddPoto;


        public OldThingHolder(View itemView) {
            super(itemView);
            mUserName = (TextView) (itemView).findViewById(R.id.item_oldthing_username);
            mContent = (TextView) (itemView).findViewById(R.id.item_oldthing_content);
            mPhoneNum = (TextView) (itemView).findViewById(R.id.item_oldthing_phone_num);
            mLocation = (TextView) (itemView).findViewById(R.id.item_oldthing_location);
            mTime = (TextView) (itemView).findViewById(R.id.item_oldthing_time);
            mAddPoto = (LinearLayout) (itemView).findViewById(R.id.item_oldthing_add_photo);
        }
    }

}
