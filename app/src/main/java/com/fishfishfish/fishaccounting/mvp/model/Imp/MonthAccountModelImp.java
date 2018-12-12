package com.fishfishfish.fishaccounting.mvp.model.Imp;

import com.fishfishfish.fishaccounting.base.BaseObserver;
import com.fishfishfish.fishaccounting.model.bean.local.FishBill;
import com.fishfishfish.fishaccounting.model.bean.local.MonthAccountBean;
import com.fishfishfish.fishaccounting.model.repository.LocalRepository;
import com.fishfishfish.fishaccounting.mvp.model.MonthAccountModel;
import com.fishfishfish.fishaccounting.utils.BillUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MonthAccountModelImp implements MonthAccountModel {

    private MonthAccountOnListener listener;

    public MonthAccountModelImp(MonthAccountOnListener listener) {
        this.listener = listener;
    }


    @Override
    public void getMonthAccountBills(int id, String year, String month) {
        LocalRepository.getInstance().getFishBillByUserIdWithYM(id, year, month)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<FishBill>>() {
                    @Override
                    protected void onSuccees(List<FishBill> fishBills) throws Exception {
                        listener.onSuccess(BillUtils.packageAccountList(fishBills));
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
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
    public interface MonthAccountOnListener {

        void onSuccess(MonthAccountBean bean);

        void onFailure(Throwable e);
    }
}
