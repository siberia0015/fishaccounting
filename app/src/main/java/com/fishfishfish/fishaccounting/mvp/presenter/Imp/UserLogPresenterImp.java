package com.fishfishfish.fishaccounting.mvp.presenter.Imp;

import com.fishfishfish.fishaccounting.model.bean.remote.MyUser;
import com.fishfishfish.fishaccounting.mvp.model.Imp.UserLogModelImp;
import com.fishfishfish.fishaccounting.mvp.model.UserLogModel;
import com.fishfishfish.fishaccounting.mvp.presenter.UserLogPresenter;
import com.fishfishfish.fishaccounting.mvp.view.UserLogView;

public class UserLogPresenterImp extends UserLogPresenter implements UserLogModelImp.UserLogOnListener {

    private UserLogModel userLogModel;
    private UserLogView userLogView;

    public UserLogPresenterImp(UserLogView userLogView) {
        this.userLogModel = new UserLogModelImp(this);
        this.userLogView = userLogView;
    }

    @Override
    public void onSuccess(MyUser user) {
        userLogView.loadDataSuccess(user);
    }

    @Override
    public void onFailure(Throwable e) {
        userLogView.loadDataError(e);
    }

    @Override
    public void login(String username, String password) {
        userLogModel.login(username, password);
    }

    @Override
    public void signup(String username, String password, String mail) {
        userLogModel.signup(username, password, mail);
    }
}
