package com.fishfishfish.fishaccounting.mvp.model;

import com.fishfishfish.fishaccounting.model.bean.local.FishBill;

public interface MonthDetailModel {

    /**
     * 每月账单详情
     */
    void getMonthDetailBills(int id, String year, String month);

    /**
     * 删除账单
     */
    void delete(Long id);

    void update(FishBill fishBill);

    void onUnsubscribe();
}
