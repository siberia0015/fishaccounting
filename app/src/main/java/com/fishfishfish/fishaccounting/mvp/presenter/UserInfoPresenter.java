package com.fishfishfish.fishaccounting.mvp.presenter;

import com.fishfishfish.fishaccounting.base.BasePresenter;
import com.fishfishfish.fishaccounting.model.bean.remote.MyUser;
import com.fishfishfish.fishaccounting.base.BasePresenter;

public abstract  class UserInfoPresenter extends BasePresenter {

    public abstract void update(MyUser user);
}
