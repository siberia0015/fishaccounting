package com.fishfishfish.fishaccounting.mvp.presenter.Imp;

import com.fishfishfish.fishaccounting.model.bean.BaseBean;
import com.fishfishfish.fishaccounting.model.bean.local.FishBill;
import com.fishfishfish.fishaccounting.model.bean.local.NoteBean;
import com.fishfishfish.fishaccounting.mvp.model.BillModel;
import com.fishfishfish.fishaccounting.mvp.model.Imp.BillModelImp;
import com.fishfishfish.fishaccounting.mvp.presenter.BillPresenter;
import com.fishfishfish.fishaccounting.mvp.view.BillView;

public class BillPresenterImp extends BillPresenter implements BillModelImp.BillOnListener {

    private BillModel model;
    private BillView view;

    public BillPresenterImp(BillView view) {
        this.model = new BillModelImp(this);
        this.view = view;
    }

    @Override
    public void onSuccess(BaseBean bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onSuccess(NoteBean bean) {
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
    public void add(FishBill fishBill) {
        model.add(fishBill);
    }

    @Override
    public void update(FishBill fishBill) {
        model.update(fishBill);
    }

    @Override
    public void delete(Long id) {
        model.delete(id);
    }
}
