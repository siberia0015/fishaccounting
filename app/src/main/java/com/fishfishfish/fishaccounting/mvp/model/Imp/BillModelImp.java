package com.fishfishfish.fishaccounting.mvp.model.Imp;

import com.fishfishfish.fishaccounting.base.BaseObserver;
import com.fishfishfish.fishaccounting.model.bean.BaseBean;
import com.fishfishfish.fishaccounting.model.bean.local.FishBill;
import com.fishfishfish.fishaccounting.model.bean.local.FishPay;
import com.fishfishfish.fishaccounting.model.bean.local.FishSort;
import com.fishfishfish.fishaccounting.model.bean.local.NoteBean;
import com.fishfishfish.fishaccounting.model.repository.LocalRepository;
import com.fishfishfish.fishaccounting.mvp.model.BillModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BillModelImp implements BillModel {

    private BillOnListener listener;

    public BillModelImp(BillOnListener listener) {
        this.listener = listener;
    }

    @Override
    public void getNote() {
        final NoteBean note = new NoteBean();
        LocalRepository.getInstance()
                .getFishPay()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<FishPay>>() {
                    @Override
                    protected void onSuccees(List<FishPay> fishPays) throws Exception {
                        note.setPayinfo(fishPays);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e);
                    }
                });
        LocalRepository.getInstance().getFishSort(false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<FishSort>>() {
                    @Override
                    protected void onSuccees(List<FishSort> sorts) throws Exception {
                        note.setOutSortlis(sorts);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e);
                    }
                });
        LocalRepository.getInstance().getFishSort(true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<FishSort>>() {
                    @Override
                    protected void onSuccees(List<FishSort> sorts) throws Exception {
                        note.setInSortlis(sorts);
                        listener.onSuccess(note);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e);
                    }
                });
    }

    @Override
    public void add(FishBill fishBill) {
        LocalRepository.getInstance().saveFishBill(fishBill)
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
    public void delete(Long id) {
        LocalRepository.getInstance()
                .deleteFishBillById(id);
    }

    @Override
    public void onUnsubscribe() {

    }

    /**
     * 回调接口
     */
    public interface BillOnListener {
        void onSuccess(BaseBean bean);

        void onSuccess(NoteBean bean);

        void onFailure(Throwable e);
    }
}
