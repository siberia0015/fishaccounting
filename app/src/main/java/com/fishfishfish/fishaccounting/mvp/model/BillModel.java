package com.fishfishfish.fishaccounting.mvp.model;

import com.fishfishfish.fishaccounting.model.bean.local.BBill;

public interface BillModel {

    void getNote();
    /**
     * 添加账单
     */
    void add(BBill bBill);
    /**
     * 修改账单
     */
    void update(BBill bBill);
    /**
     * 删除账单
     */
    void delete(Long id);

    void onUnsubscribe();
}
