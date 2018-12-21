package com.fishfishfish.fishaccounting.mvp.view;

import com.fishfishfish.fishaccounting.base.BaseView;
import com.fishfishfish.fishaccounting.model.bean.BaseBean;
import com.fishfishfish.fishaccounting.model.bean.local.NoteBean;

public interface BillView extends BaseView<BaseBean> {

    void loadDataSuccess(NoteBean tData);
}
