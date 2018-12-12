package com.fishfishfish.fishaccounting.mvp.model;

import com.fishfishfish.fishaccounting.model.bean.local.FishBill;

public interface BillModel {

    void getNote();

    /**
     * 添加账单
     */
    void add(FishBill fishBill);

    /**
     * 修改账单
     */
    void update(FishBill fishBill);

    /**
     * 删除账单
     */
    void delete(Long id);

    void onUnsubscribe();
}
