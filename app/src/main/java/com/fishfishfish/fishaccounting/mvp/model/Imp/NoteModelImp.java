package com.fishfishfish.fishaccounting.mvp.model.Imp;

import com.fishfishfish.fishaccounting.base.BaseObserver;
import com.fishfishfish.fishaccounting.model.bean.local.FishPay;
import com.fishfishfish.fishaccounting.model.bean.local.FishSort;
import com.fishfishfish.fishaccounting.model.bean.local.NoteBean;
import com.fishfishfish.fishaccounting.model.repository.LocalRepository;
import com.fishfishfish.fishaccounting.mvp.model.NoteModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NoteModelImp implements NoteModel {

    private NoteOnListener listener;

    public NoteModelImp(NoteOnListener listener) {
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
    public void addSort(FishSort fishSort) {
        LocalRepository.getInstance().saveFishSort(fishSort);
    }

    @Override
    public void addPay(FishPay fishPay) {
        LocalRepository.getInstance().saveFishPay(fishPay);
    }

    @Override
    public void deleteSort(Long id) {
        LocalRepository.getInstance().deleteFishSortById(id);
    }

    @Override
    public void deletePay(Long id) {
        LocalRepository.getInstance().deleteFishPayById(id);
    }

    @Override
    public void onUnsubscribe() {

    }

    /**
     * 回调接口
     */
    public interface NoteOnListener {

        void onSuccess(NoteBean bean);

        void onSuccess(FishSort bean);

        void onSuccess(FishPay bean);

        void onFailure(Throwable e);
    }
}
