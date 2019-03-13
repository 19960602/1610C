package wangyaowei.bwei.com.six.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwei.xlistview.XlistView;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import wangyaowei.bwei.com.six.R;
import wangyaowei.bwei.com.six.adapter.MyAdapter;
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
public class YeMian_05 extends Fragment {
    private int page=1;
    private XlistView xlv;
    private Handler handler=new Handler();
    private ArrayList<JsonBean.DataBean> dataBeans;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.yemian_05, container, false);
        xlv = view.findViewById(R.id.xlv);
        xlv.setPullRefreshEnable(true);
        xlv.setPullLoadEnable(true);

        xlv.setXListViewListener(new XlistView.IXListViewListener() {
            @Override
            public void onRefresh() {

                    getdata();


                //               // page=1;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xlv.stopRefresh();
                    }
                },2000 );
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                            page++;
                            getdata();


                        xlv.stopLoadMore();
                    }
                }, 2000);
            }
        });
        getdata();

        return view;
    }

    private void getdata() {
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
    }
    class MyAsyncTask extends AsyncTask<String,Integer,String> {

        private List<JsonBean.DataBean> data;

        @Override
        protected String doInBackground(String... strings) {
            String path="http://www.xieast.com/api/news/news.php?page="+page;
            try {
                URL url = new URL(path);
                HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(5000);
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("GET");
                if (conn.getResponseCode()==200){
                    InputStream inputStream = conn.getInputStream();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] bytes = new byte[1024];
                    int len=0;
                    while ((len=inputStream.read(bytes))!=-1){
                        bos.write(bytes,0 ,len );
                    }
                    inputStream.close();
                    bos.close();
                    String json = bos.toString();
                    return json;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            JsonBean jsonBean = gson.fromJson(s, JsonBean.class);
            data = jsonBean.getData();

            if (page==1){
                dataBeans = new ArrayList<>();

            }
            dataBeans.addAll(data);

            MyAdapter myAdapter = new MyAdapter(getActivity(), dataBeans);
            xlv.setAdapter(myAdapter);
            xlv.setSelection(dataBeans.size()-(data.size()-1));
        }
    }
}
