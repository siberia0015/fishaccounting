package com.fishfishfish.fishaccounting.mvp.model;

public interface BillModel {

    void getNote();

    /**
     * 添加账单
     */
    void add(BillBean BillBean);

    /**
     * 修改账单
     */
    void update(BillBean BillBean);

    /**
     * 删除账单
     */
    void delete(Long id);

    void onUnsubscribe();
}
