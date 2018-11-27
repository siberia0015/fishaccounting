package com.fishfishfish.fishaccounting.mvp.model.Imp;

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
                .getPayBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<PayBean>>() {
                    @Override
                    protected void onSuccees(List<PayBean> PayBeans) throws Exception {
                        note.setPayinfo(PayBeans);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e);
                    }
                });
        LocalRepository.getInstance().getSortBean(false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<SortBean>>() {
                    @Override
                    protected void onSuccees(List<SortBean> sorts) throws Exception {
                        note.setOutSortlis(sorts);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e);
                    }
                });
        LocalRepository.getInstance().getSortBean(true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<SortBean>>() {
                    @Override
                    protected void onSuccees(List<SortBean> sorts) throws Exception {
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
    public void add(BillBean BillBean) {
        LocalRepository.getInstance().saveBillBean(BillBean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BillBean>() {
                    @Override
                    protected void onSuccees(BillBean BillBean) throws Exception {
                        listener.onSuccess(new BaseBean());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e);
                    }
                });
    }

    @Override
    public void update(BillBean BillBean) {
        LocalRepository.getInstance()
                .updateBillBean(BillBean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BillBean>() {
                    @Override
                    protected void onSuccees(BillBean BillBean) throws Exception {
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
                .deleteBillBeanById(id);
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
