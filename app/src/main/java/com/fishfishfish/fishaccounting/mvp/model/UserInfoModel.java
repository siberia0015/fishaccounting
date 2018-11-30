package com.fishfishfish.fishaccounting.mvp.model;

import com.fishfishfish.fishaccounting.model.bean.remote.MyUser;

public interface UserInfoModel {

    void update(MyUser user);

    void onUnsubscribe();
}
