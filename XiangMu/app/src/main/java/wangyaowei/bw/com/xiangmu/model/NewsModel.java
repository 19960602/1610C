package wangyaowei.bw.com.xiangmu.model;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import wangyaowei.bw.com.xiangmu.bean.JsonBean;
import wangyaowei.bw.com.xiangmu.utils.OkHttpUtils;

/*
 *********************************
 *作者:wangyaowei
 *时间:2019/3/13  19:24
 **
 *
 *********************************
 */public class NewsModel {

     public interface OnNewModelLisenter{
         void onMessage(List<JsonBean.ResultsBean> results);
     }
     private OnNewModelLisenter lisenter;

    public void setLisenter(OnNewModelLisenter lisenter) {
        this.lisenter = lisenter;
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String obj= (String) msg.obj;
                    Gson gson = new Gson();
                    JsonBean jsonBean = gson.fromJson(obj, JsonBean.class);
                    List<JsonBean.ResultsBean> results = jsonBean.getResults();
                    if (lisenter!=null){
                        lisenter.onMessage(results);
                    }
                    break;
            }
        }
    };

    public void getData(int page) {
        String path="http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/"+page;
        OkHttpUtils.getInstance().getData(path, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();

                Message message = new Message();
                message.what=0;
                message.obj=json;
                handler.sendMessage(message);
            }
        });
    }
}
