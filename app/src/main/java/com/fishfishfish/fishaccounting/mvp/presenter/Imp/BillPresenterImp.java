package com.fishfishfish.fishaccounting.mvp.presenter.Imp;

import com.fishfishfish.fishaccounting.model.bean.BaseBean;
import com.fishfishfish.fishaccounting.model.bean.local.BBill;
import com.fishfishfish.fishaccounting.model.bean.local.NoteBean;
import com.fishfishfish.fishaccounting.mvp.model.BillModel;
import com.fishfishfish.fishaccounting.mvp.model.Imp.BillModelImp;
import com.fishfishfish.fishaccounting.mvp.presenter.BillPresenter;
import com.fishfishfish.fishaccounting.mvp.view.BillView;

public class BillPresenterImp extends BillPresenter implements BillModelImp.BillOnListener{

    private BillModel model;
    private BillView view;

    public BillPresenterImp(BillView view) {
        this.model=new BillModelImp(this);
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
    public void add(BBill bBill) {
        model.add(bBill);
    }

    @Override
    public void update(BBill bBill) {
        model.update(bBill);
    }

    @Override
    public void delete(Long id) {
        model.delete(id);
    }
}
