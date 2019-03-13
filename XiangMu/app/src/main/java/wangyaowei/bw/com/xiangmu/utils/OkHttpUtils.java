package wangyaowei.bw.com.xiangmu.utils;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/*
 *********************************
 *作者:wangyaowei
 *时间:2019/3/13  19:24
 **
 *
 *********************************
 */public class OkHttpUtils {
        private static OkHttpUtils okHttpUtils=null;

    public OkHttpUtils() {

    }
    public static OkHttpUtils getInstance(){
        if (okHttpUtils==null){
            synchronized (OkHttpUtils.class){
                if (okHttpUtils==null){
                    okHttpUtils=new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }

    private static OkHttpClient okHttpClient=null;
    public static synchronized OkHttpClient getOkHttpClient(){
        if (okHttpClient==null){
            okHttpClient=new OkHttpClient();
        }
        return okHttpClient;
    }
    public static void getData(String url, Callback callback){
        OkHttpClient okHttpClient=getOkHttpClient();
        Request build = new Request.Builder()
                .url(url)
                .build();

        Call call = okHttpClient.newCall(build);
        call.enqueue(callback);
    }
}
