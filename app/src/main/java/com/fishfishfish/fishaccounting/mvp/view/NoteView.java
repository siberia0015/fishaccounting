package com.fishfishfish.fishaccounting.mvp.view;

import com.fishfishfish.fishaccounting.base.BaseView;
import com.fishfishfish.fishaccounting.model.bean.local.BPay;
import com.fishfishfish.fishaccounting.model.bean.local.BSort;
import com.fishfishfish.fishaccounting.model.bean.local.NoteBean;

public interface NoteView extends BaseView<NoteBean> {

    /**
     * 请求数据成功
     * @param tData
     */
    void loadDataSuccess(BSort tData);
    void loadDataSuccess(BPay tData);
}
