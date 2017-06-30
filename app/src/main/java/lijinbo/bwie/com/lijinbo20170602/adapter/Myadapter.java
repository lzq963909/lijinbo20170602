package lijinbo.bwie.com.lijinbo20170602.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import lijinbo.bwie.com.lijinbo20170602.R;
import lijinbo.bwie.com.lijinbo20170602.TwoActivity;
import lijinbo.bwie.com.lijinbo20170602.bean.Commit;

/**类用途 适配器 用于加载布局文件
 * Created by lijinbo on 2017/6/2.
 */

public class Myadapter extends BaseAdapter {

    Context context;
    List<Commit.DataBean.ComicsBean> list;

    public Myadapter(Context context, List<Commit.DataBean.ComicsBean> list) {

        this.context=context;
        this.list=list;
    }

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
    //XListView列表展示数据并优化
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView==null){
            holder=new ViewHolder();
            convertView=View.inflate(context, R.layout.lv_item,null);
              holder.tv1= (TextView) convertView.findViewById(R.id.tv1);
            holder.tv2= (TextView) convertView.findViewById(R.id.tv2);
            holder.title_tv= (TextView) convertView.findViewById(R.id.title_tv);
            holder.quanji= (TextView) convertView.findViewById(R.id.quanji);
            holder.pic= (ImageView) convertView.findViewById(R.id.pic);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        holder.tv1.setText(list.get(position).getLabel_text());
        holder.title_tv.setText(list.get(position).getTitle());
        holder.tv2.setText(list.get(position).getTopic().getTitle());
        Glide.with(context).load(list.get(position).getCover_image_url()).into(holder.pic);

        //点击全集跳转到图2并把数据传递到图2页面
        holder.quanji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,TwoActivity.class);
                Bundle bundle=new Bundle();
                bundle.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) list);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder{
        TextView tv1;
        TextView tv2;
        TextView title_tv;
        ImageView pic;
        TextView quanji;
    }


}
