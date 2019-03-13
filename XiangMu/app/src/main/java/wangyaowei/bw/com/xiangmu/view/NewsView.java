package wangyaowei.bw.com.xiangmu.view;

import java.util.List;

import wangyaowei.bw.com.xiangmu.bean.JsonBean;

/*
 *********************************
 *作者:wangyaowei
 *时间:2019/3/13  19:25
 **
 *
 *********************************
 */public interface NewsView {

    void onResult(List<JsonBean.ResultsBean> results);
}
