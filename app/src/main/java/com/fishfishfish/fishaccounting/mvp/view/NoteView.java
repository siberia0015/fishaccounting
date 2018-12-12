package com.fishfishfish.fishaccounting.mvp.view;

import com.fishfishfish.fishaccounting.base.BaseView;
import com.fishfishfish.fishaccounting.model.bean.local.FishPay;
import com.fishfishfish.fishaccounting.model.bean.local.FishSort;
import com.fishfishfish.fishaccounting.model.bean.local.NoteBean;

public interface NoteView extends BaseView<NoteBean> {

    /**
     * 请求数据成功
     *
     * @param tData
     */
    void loadDataSuccess(FishSort tData);

    void loadDataSuccess(FishPay tData);
}
