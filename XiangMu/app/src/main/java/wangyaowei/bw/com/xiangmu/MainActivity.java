package wangyaowei.bw.com.xiangmu;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import wangyaowei.bw.com.xiangmu.adapter.MyAdapter;
import wangyaowei.bw.com.xiangmu.bean.JsonBean;
import wangyaowei.bw.com.xiangmu.presenter.NewsPresenter;
import wangyaowei.bw.com.xiangmu.view.NewsView;

public class MainActivity extends AppCompatActivity implements NewsView {

    private int page=1;
    private RecyclerView rlv;
    private NewsPresenter presenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<JsonBean.ResultsBean> resultsBeans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rlv = findViewById(R.id.rlv);


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rlv.setLayoutManager(linearLayoutManager);

        presenter = new NewsPresenter(this);
        presenter.relaed(page);

        swipeRefreshLayout = findViewById(R.id.srl);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary,R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page++;
                presenter.relaed(page);

            }
        });
    }


    @Override
    public void onResult(List<JsonBean.ResultsBean> results) {


        if (page==1){
            resultsBeans = new ArrayList<>();
        }
        resultsBeans.addAll(results);

        MyAdapter myAdapter = new MyAdapter(MainActivity.this, (ArrayList<JsonBean.ResultsBean>) results);
        rlv.setAdapter(myAdapter);
        rlv.scrollToPosition(resultsBeans.size()-(results.size()));
    }
}
