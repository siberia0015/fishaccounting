package com.fishfishfish.fishaccounting.mvp.model;

public interface NoteModel {

    void getNote();

    void addSort(BillBean BillBean);

    void addPay(PayBean PayBean);

    void deleteSort(Long id);

    void deletePay(Long id);

    void onUnsubscribe();
}
