package com.fishfishfish.fishaccounting.mvp.view;

import com.fishfishfish.fishaccounting.base.BaseView;
import com.fishfishfish.fishaccounting.model.bean.BaseBean;
import com.fishfishfish.fishaccounting.model.bean.local.MonthDetailBean;

public interface MonthDetailView extends BaseView<MonthDetailBean> {

    /**
     * 本地当月账单
     *
     * @param list
     */
    void loadDataSuccess(MonthDetailBean list);

    /**
     * 删除成功
     *
     * @param tData
     */
    void loadDataSuccess(BaseBean tData);
}
