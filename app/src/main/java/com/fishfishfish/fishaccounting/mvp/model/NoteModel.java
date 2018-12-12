package com.fishfishfish.fishaccounting.mvp.model;

import com.fishfishfish.fishaccounting.model.bean.local.FishPay;
import com.fishfishfish.fishaccounting.model.bean.local.FishSort;

public interface NoteModel {

    void getNote();

    void addSort(FishSort fishSort);

    void addPay(FishPay fishPay);

    void deleteSort(Long id);

    void deletePay(Long id);

    void onUnsubscribe();
}
