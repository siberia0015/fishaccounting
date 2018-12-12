package com.fishfishfish.fishaccounting.mvp.presenter.Imp;

import com.fishfishfish.fishaccounting.model.bean.local.FishPay;
import com.fishfishfish.fishaccounting.model.bean.local.FishSort;
import com.fishfishfish.fishaccounting.model.bean.local.NoteBean;
import com.fishfishfish.fishaccounting.mvp.model.Imp.NoteModelImp;
import com.fishfishfish.fishaccounting.mvp.model.NoteModel;
import com.fishfishfish.fishaccounting.mvp.presenter.NotePresenter;
import com.fishfishfish.fishaccounting.mvp.view.NoteView;

public class NotePresenterImp extends NotePresenter implements NoteModelImp.NoteOnListener {

    private NoteModel model;
    private NoteView view;

    public NotePresenterImp(NoteView view) {
        this.model = new NoteModelImp(this);
        this.view = view;
    }

    @Override
    public void onSuccess(NoteBean bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onSuccess(FishSort bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onSuccess(FishPay bean) {
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
    public void addSort(FishSort fishSort) {
        model.addSort(fishSort);
    }

    @Override
    public void addPay(FishPay fishPay) {
        model.addPay(fishPay);
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
