package wangyaowei.bw.com.xiangmu.presenter;

import java.util.List;

import wangyaowei.bw.com.xiangmu.bean.JsonBean;
import wangyaowei.bw.com.xiangmu.model.NewsModel;
import wangyaowei.bw.com.xiangmu.view.NewsView;

/*
 *********************************
 *作者:wangyaowei
 *时间:2019/3/13  19:24
 **
 *
 *********************************
 */public class NewsPresenter {

    private final NewsModel newsModel;
    private final NewsView newsView;

    public NewsPresenter(NewsView view) {
        newsModel = new NewsModel();
        newsView = view;
    }

    public void relaed(int page) {
        newsModel.getData(page);
        newsModel.setLisenter(new NewsModel.OnNewModelLisenter() {
            @Override
            public void onMessage(List<JsonBean.ResultsBean> results) {
                newsView.onResult(results);
            }
        });
    }
}
