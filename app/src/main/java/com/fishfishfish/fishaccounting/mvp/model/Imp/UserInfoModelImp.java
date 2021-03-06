package com.fishfishfish.fishaccounting.mvp.model.Imp;

import com.fishfishfish.fishaccounting.model.bean.remote.MyUser;
import com.fishfishfish.fishaccounting.mvp.model.UserInfoModel;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class UserInfoModelImp implements UserInfoModel {

    public static int UPDATE_TYPE_GENDER = 1;
    public static int UPDATE_TYPE_PHONE = 2;
    public static int UPDATE_TYPE_EMAIL = 3;

    private UserInfoOnListener listener;

    public UserInfoModelImp(UserInfoOnListener listener) {
        this.listener = listener;
    }

    @Override
    public void update(MyUser user) {
        user.update(user.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null)
                    listener.onSuccess(new MyUser());
                else
                    listener.onFailure(e);
            }
        });

    }

    @Override
    public void onUnsubscribe() {

    }

    /**
     * 回调接口
     */
    public interface UserInfoOnListener {

        void onSuccess(MyUser user);

        void onFailure(Throwable e);
    }
}
