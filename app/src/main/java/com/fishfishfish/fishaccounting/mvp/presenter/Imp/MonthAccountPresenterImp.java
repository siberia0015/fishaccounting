package com.fishfishfish.fishaccounting.mvp.presenter.Imp;

import com.fishfishfish.fishaccounting.model.bean.local.MonthAccountBean;
import com.fishfishfish.fishaccounting.mvp.model.Imp.MonthAccountModelImp;
import com.fishfishfish.fishaccounting.mvp.model.MonthAccountModel;
import com.fishfishfish.fishaccounting.mvp.presenter.MonthAccountPresenter;
import com.fishfishfish.fishaccounting.mvp.view.MonthAccountView;

public class MonthAccountPresenterImp extends MonthAccountPresenter implements MonthAccountModelImp.MonthAccountOnListener {

    private MonthAccountModel model;
    private MonthAccountView view;

    public MonthAccountPresenterImp(MonthAccountView view) {
        this.model = new MonthAccountModelImp(this);
        this.view = view;
    }

    @Override
    public void onSuccess(MonthAccountBean bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onFailure(Throwable e) {
        view.loadDataError(e);
    }


    @Override
    public void getMonthAccountBills(int id, String year, String month) {
        model.getMonthAccountBills(id, year, month);
    }
}
