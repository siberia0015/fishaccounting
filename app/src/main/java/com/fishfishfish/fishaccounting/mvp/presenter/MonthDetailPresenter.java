package com.fishfishfish.fishaccounting.mvp.presenter;

import com.fishfishfish.fishaccounting.base.BasePresenter;
import com.fishfishfish.fishaccounting.model.bean.local.FishBill;

public abstract class MonthDetailPresenter extends BasePresenter {

    public abstract void getMonthDetailBills(int id, String year, String month);

    public abstract void deleteBill(Long id);

    public abstract void updateBill(FishBill fishBill);
}
