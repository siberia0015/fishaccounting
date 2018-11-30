package com.fishfishfish.fishaccounting.mvp.presenter.Imp;

import com.fishfishfish.fishaccounting.model.bean.local.BPay;
import com.fishfishfish.fishaccounting.model.bean.local.BSort;
import com.fishfishfish.fishaccounting.model.bean.local.NoteBean;
import com.fishfishfish.fishaccounting.mvp.model.Imp.NoteModelImp;
import com.fishfishfish.fishaccounting.mvp.model.NoteModel;
import com.fishfishfish.fishaccounting.mvp.presenter.NotePresenter;
import com.fishfishfish.fishaccounting.mvp.view.NoteView;

public class NotePresenterImp extends NotePresenter implements NoteModelImp.NoteOnListener{

    private NoteModel model;
    private NoteView view;

    public NotePresenterImp(NoteView view) {
        this.model=new NoteModelImp(this);
        this.view = view;
    }

    @Override
    public void onSuccess(NoteBean bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onSuccess(BSort bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onSuccess(BPay bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onFailure(Throwable e) {
        view.loadDataError(e);
    }

    @Override
    public void getNote() {
        model.getNote();
    }

    @Override
    public void addSort(BSort bSort) {
        model.addSort(bSort);
    }

    @Override
    public void addPay(BPay bPay) {
        model.addPay(bPay);
    }

    @Override
    public void deleteSort(Long id) {
        model.deleteSort(id);
    }

    @Override
    public void deletePay(Long id) {
        model.deletePay(id);
    }
}
