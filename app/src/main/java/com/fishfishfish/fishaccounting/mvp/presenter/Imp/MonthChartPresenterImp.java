package com.fishfishfish.fishaccounting.mvp.presenter.Imp;

import com.fishfishfish.fishaccounting.model.bean.local.MonthChartBean;
import com.fishfishfish.fishaccounting.mvp.model.Imp.MonthChartModelImp;
import com.fishfishfish.fishaccounting.mvp.model.MonthChartModel;
import com.fishfishfish.fishaccounting.mvp.presenter.MonthChartPresenter;
import com.fishfishfish.fishaccounting.mvp.view.MonthChartView;

public class MonthChartPresenterImp extends MonthChartPresenter implements MonthChartModelImp.MonthChartOnListener {

    private MonthChartModel model;
    private MonthChartView view;

    public MonthChartPresenterImp(MonthChartView view) {
        this.model = new MonthChartModelImp(this);
        this.view = view;
    }

    @Override
    public void onSuccess(MonthChartBean bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onFailure(Throwable e) {
        view.loadDataError(e);
    }

    @Override
    public void getMonthChartBills(int id, String year, String month) {
        model.getMonthChartBills(id, year, month);
    }

}
