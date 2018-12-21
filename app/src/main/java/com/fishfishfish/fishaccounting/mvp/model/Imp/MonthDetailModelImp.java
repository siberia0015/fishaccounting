package com.fishfishfish.fishaccounting.mvp.model.Imp;

import com.fishfishfish.fishaccounting.base.BaseObserver;
import com.fishfishfish.fishaccounting.model.bean.BaseBean;
import com.fishfishfish.fishaccounting.model.bean.local.FishBill;
import com.fishfishfish.fishaccounting.model.bean.local.MonthDetailBean;
import com.fishfishfish.fishaccounting.model.repository.LocalRepository;
import com.fishfishfish.fishaccounting.mvp.model.MonthDetailModel;
import com.fishfishfish.fishaccounting.utils.BillUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MonthDetailModelImp implements MonthDetailModel {

    private MonthDetailOnListener listener;

    public MonthDetailModelImp(MonthDetailOnListener listener) {
        this.listener = listener;
    }


    @Override
    public void getMonthDetailBills(int id, String year, String month) {
        LocalRepository.getInstance().getFishBillByUserIdWithYM(id, year, month)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<FishBill>>() {
                    @Override
                    protected void onSuccees(List<FishBill> fishBills) throws Exception {
                        listener.onSuccess(BillUtils.packageDetailList(fishBills));
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e);
                    }
                });
    }


    @Override
    public void delete(Long id) {
        LocalRepository.getInstance().deleteFishBillById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Long>() {
                    @Override
                    protected void onSuccees(Long l) throws Exception {
                        listener.onSuccess(new BaseBean());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e);
                    }
                });
    }

    /**
     * 伪删除操作
     *
     * @param fishBill
     */
    @Override
    public void update(FishBill fishBill) {
        LocalRepository.getInstance()
                .updateFishBill(fishBill)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<FishBill>() {
                    @Override
                    protected void onSuccees(FishBill fishBill) throws Exception {
                        listener.onSuccess(new BaseBean());
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
    public interface MonthDetailOnListener {

        void onSuccess(MonthDetailBean bean);

        void onSuccess(BaseBean bean);

        void onFailure(Throwable e);
    }
}
