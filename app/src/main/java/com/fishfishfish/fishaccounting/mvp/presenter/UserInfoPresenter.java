package com.fishfishfish.fishaccounting.mvp.presenter;

import com.fishfishfish.fishaccounting.base.BasePresenter;
import com.fishfishfish.fishaccounting.model.bean.remote.MyUser;

public abstract class UserInfoPresenter extends BasePresenter {

    public abstract void update(MyUser user);
}
