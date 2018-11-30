package com.fishfishfish.fishaccounting.mvp.presenter;

import com.fishfishfish.fishaccounting.base.BasePresenter;
import com.fishfishfish.fishaccounting.model.bean.local.BBill;
import com.fishfishfish.fishaccounting.base.BasePresenter;

public abstract  class BillPresenter extends BasePresenter {

    /**
     * 获取信息
     */
    public abstract void getNote();

    /**
     * 添加账单
     */
    public abstract void add(BBill bBill);

    /**
     * 修改账单
     */
    public abstract void update(BBill bBill);


    /**
     * 删除账单
     */
    public abstract void delete(Long id);
}
