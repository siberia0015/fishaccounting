package com.fishfishfish.fishaccounting.mvp.presenter;

import com.fishfishfish.fishaccounting.base.BasePresenter;
import com.fishfishfish.fishaccounting.model.bean.local.FishPay;
import com.fishfishfish.fishaccounting.model.bean.local.FishSort;

public abstract class NotePresenter extends BasePresenter {

    /**
     * 获取信息
     */
    public abstract void getNote();

    /**
     * 添加账单分类
     */
    public abstract void addSort(FishSort fishSort);

    /**
     * 添加账单支付方式
     */
    public abstract void addPay(FishPay fishPay);


    /**
     * 删除账单分类
     */
    public abstract void deleteSort(Long id);

    /**
     * 删除账单支出方式
     */
    public abstract void deletePay(Long id);
}
