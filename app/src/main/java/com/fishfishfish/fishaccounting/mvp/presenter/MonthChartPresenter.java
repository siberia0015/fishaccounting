package com.fishfishfish.fishaccounting.mvp.presenter;

import com.fishfishfish.fishaccounting.base.BasePresenter;

public abstract class MonthChartPresenter extends BasePresenter {

    public abstract void getMonthChartBills(int id, String year, String month);
}
