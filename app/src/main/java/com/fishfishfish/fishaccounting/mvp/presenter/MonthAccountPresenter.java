package com.fishfishfish.fishaccounting.mvp.presenter;

import com.fishfishfish.fishaccounting.base.BasePresenter;

public abstract class MonthAccountPresenter extends BasePresenter {

    public abstract void getMonthAccountBills(int id, String year, String month);
}
