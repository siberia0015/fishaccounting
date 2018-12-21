package com.fishfishfish.fishaccounting.model.bean.local;

import com.fishfishfish.fishaccounting.model.bean.BaseBean;

import java.util.List;

public class NoteBean extends BaseBean {

    private List<FishSort> outSortlis;
    private List<FishSort> inSortlis;
    private List<FishPay> payinfo;

    public List<FishSort> getOutSortlis() {
        return outSortlis;
    }

    public void setOutSortlis(List<FishSort> outSortlis) {
        this.outSortlis = outSortlis;
    }

    public List<FishSort> getInSortlis() {
        return inSortlis;
    }

    public void setInSortlis(List<FishSort> inSortlis) {
        this.inSortlis = inSortlis;
    }

    public List<FishPay> getPayinfo() {
        return payinfo;
    }

    public void setPayinfo(List<FishPay> payinfo) {
        this.payinfo = payinfo;
    }
}
