package com.fishfishfish.fishaccounting.mvp.presenter.Imp;

import com.fishfishfish.fishaccounting.model.bean.remote.MyUser;
import com.fishfishfish.fishaccounting.mvp.model.Imp.UserInfoModelImp;
import com.fishfishfish.fishaccounting.mvp.model.UserInfoModel;
import com.fishfishfish.fishaccounting.mvp.presenter.UserInfoPresenter;
import com.fishfishfish.fishaccounting.mvp.view.UserInfoView;

public class UserInfoPresenterImp extends UserInfoPresenter implements UserInfoModelImp.UserInfoOnListener {

    private UserInfoModel model;
    private UserInfoView view;

    public UserInfoPresenterImp(UserInfoView view) {
        this.model = new UserInfoModelImp(this);
        this.view = view;
    }

    @Override
    public void onSuccess(MyUser user) {
        view.loadDataSuccess(user);
    }

    @Override
    public void onFailure(Throwable e) {
        view.loadDataError(e);
    }

    @Override
    public void update(MyUser user) {
        model.update(user);
    }
}
