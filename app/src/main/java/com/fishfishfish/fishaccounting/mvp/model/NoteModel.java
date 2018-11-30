package com.fishfishfish.fishaccounting.mvp.model;

import com.fishfishfish.fishaccounting.model.bean.local.BPay;
import com.fishfishfish.fishaccounting.model.bean.local.BSort;

public interface NoteModel {

    void getNote();

    void addSort(BSort bSort);

    void addPay(BPay bPay);

    void deleteSort(Long id);

    void deletePay(Long id);

    void onUnsubscribe();
}
