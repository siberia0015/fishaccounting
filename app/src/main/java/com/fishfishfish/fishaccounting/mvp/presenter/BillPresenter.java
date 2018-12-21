package com.fishfishfish.fishaccounting.mvp.presenter;

import com.fishfishfish.fishaccounting.base.BasePresenter;
import com.fishfishfish.fishaccounting.model.bean.local.FishBill;

public abstract class BillPresenter extends BasePresenter {

    /**
     * 获取信息
     */
    public abstract void getNote();

    /**
     * 添加账单
     */
    public abstract void add(FishBill fishBill);

    /**
     * 修改账单
     */
    public abstract void update(FishBill fishBill);


    /**
     * 删除账单
     */
    public abstract void delete(Long id);
}
