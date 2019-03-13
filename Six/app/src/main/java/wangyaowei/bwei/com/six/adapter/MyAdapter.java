package wangyaowei.bwei.com.six.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import wangyaowei.bwei.com.six.R;
import wangyaowei.bwei.com.six.bean.JsonBean;

/**
 * *********************************
 * *
 * 作者:王耀威
 * 时间:2018年1月7号
 * 异步解析
 * <p>
 * <p>
 * ****************************************
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<JsonBean.DataBean> list;

    public MyAdapter(Context context, ArrayList<JsonBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView= View.inflate(context, R.layout.item_layout ,null );
            TextView text1=convertView.findViewById(R.id.text1);
            ImageView img=convertView.findViewById(R.id.img);

            viewHolder=new ViewHolder();
            viewHolder.imageView=img;
            viewHolder.textView=text1;

            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(list.get(position).getTitle());
        ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s(),viewHolder.imageView );
        return convertView;
    }
    class ViewHolder{
        public TextView textView;
        public ImageView imageView;
    }
}
