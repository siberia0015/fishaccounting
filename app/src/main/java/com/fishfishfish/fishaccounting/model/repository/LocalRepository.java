package com.fishfishfish.fishaccounting.model.repository;

import com.fishfishfish.fishaccounting.model.bean.local.FishBill;
import com.fishfishfish.fishaccounting.model.bean.local.FishPay;
import com.fishfishfish.fishaccounting.model.bean.local.FishSort;
import com.fishfishfish.fishaccounting.model.gen.DaoSession;
import com.fishfishfish.fishaccounting.model.gen.FishBillDao;
import com.fishfishfish.fishaccounting.model.gen.FishSortDao;
import com.fishfishfish.fishaccounting.utils.DateUtils;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class LocalRepository {

    private static final String TAG = "LocalRepository";
    private static final String DISTILLATE_ALL = "normal";
    private static final String DISTILLATE_BOUTIQUES = "distillate";

    private static volatile LocalRepository sInstance;
    private DaoSession mSession;

    private LocalRepository() {
        mSession = DaoDbHelper.getInstance().getSession();
    }

    public static LocalRepository getInstance() {
        if (sInstance == null) {
            synchronized (LocalRepository.class) {
                if (sInstance == null) {
                    sInstance = new LocalRepository();
                }
            }
        }
        return sInstance;
    }

    /******************************save**************************************/
    public Observable<FishBill> saveFishBill(final FishBill bill) {
        return Observable.create(new ObservableOnSubscribe<FishBill>() {
            @Override
            public void subscribe(ObservableEmitter<FishBill> e) throws Exception {
                mSession.getFishBillDao().insert(bill);
                e.onNext(bill);
                e.onComplete();
            }
        });
    }

    /**
     * 批量添加账单
     *
     * @param fishBills
     */
    public void saveFishBills(List<FishBill> fishBills) {
        mSession.getFishBillDao().insertInTx(fishBills);
    }

    public Long saveFishPay(FishPay pay) {
        return mSession.getFishPayDao().insert(pay);
    }

    public Long saveFishSort(FishSort sort) {
        return mSession.getFishSortDao().insert(sort);
    }

    /**
     * 批量添加支付方式
     *
     * @param pays
     */
    public void saveFishPays(List<FishPay> pays) {
        for (FishPay pay : pays)
            saveFishPay(pay);
    }

    /**
     * 批量添加账单分类
     *
     * @param sorts
     */
    public void saveFishsorts(List<FishSort> sorts) {
        for (FishSort sort : sorts)
            saveFishSort(sort);
    }

    /******************************get**************************************/
    public FishBill getFishBillById(int id) {
        return mSession.getFishBillDao().queryBuilder()
                .where(FishBillDao.Properties.Id.eq(id)).unique();
    }

    public List<FishBill> getFishBills() {
        return mSession.getFishBillDao().queryBuilder().list();
    }

    public Observable<List<FishBill>> getFishBillByUserId(int id) {
        QueryBuilder<FishBill> queryBuilder = mSession.getFishBillDao()
                .queryBuilder()
                .where(FishBillDao.Properties.Userid.eq(id));
        return queryListToRx(queryBuilder);
    }

    public Observable<List<FishBill>> getFishBillByUserIdWithYM(int id, String year, String month) {
        String startStr = year + "-" + month + "-00 00:00:00";
        Date date = DateUtils.str2Date(startStr);
        Date endDate = DateUtils.addMonth(date, 1);
        QueryBuilder<FishBill> queryBuilder = mSession.getFishBillDao()
                .queryBuilder()
                .where(FishBillDao.Properties.Crdate.between(DateUtils.getMillis(date), DateUtils.getMillis(endDate)))
                .where(FishBillDao.Properties.Version.ge(0))
                .orderDesc(FishBillDao.Properties.Crdate);
        return queryListToRx(queryBuilder);
    }

    public Observable<List<FishSort>> getFishSort(boolean income) {
        QueryBuilder<FishSort> queryBuilder = mSession.getFishSortDao()
                .queryBuilder()
                .where(FishSortDao.Properties.Income.eq(income));
        return queryListToRx(queryBuilder);
    }

    public Observable<List<FishSort>> getFishSort() {
        QueryBuilder<FishSort> queryBuilder = mSession.getFishSortDao()
                .queryBuilder();
        return queryListToRx(queryBuilder);
    }

    public Observable<List<FishPay>> getFishPay() {
        QueryBuilder<FishPay> queryBuilder = mSession.getFishPayDao()
                .queryBuilder();
        return queryListToRx(queryBuilder);
    }


    /******************************update**************************************/

    /**
     * 更新账单（用于同步）
     *
     * @param bill
     */
    public void updateFishBillByBmob(FishBill bill) {
        mSession.getFishBillDao().update(bill);
    }

    /**
     * 更新账单
     *
     * @param bill
     * @return
     */
    public Observable<FishBill> updateFishBill(final FishBill bill) {

        return Observable.create(new ObservableOnSubscribe<FishBill>() {
            @Override
            public void subscribe(ObservableEmitter<FishBill> e) throws Exception {
                mSession.getFishBillDao().update(bill);
                e.onNext(bill);
                e.onComplete();
            }
        });
    }

    /******************************delete**************************************/
    /**
     * 删除账单分类
     *
     * @param id
     */
    public void deleteFishSortById(Long id) {
        mSession.getFishSortDao().deleteByKey(id);
    }

    /**
     * 删除账单支出方式
     *
     * @param id
     */
    public void deleteFishPayById(Long id) {
        mSession.getFishPayDao().deleteByKey(id);
    }

    /**
     * 批量删除账单（便于账单同步）
     *
     * @param fishBills
     */
    public void deleteBills(List<FishBill> fishBills) {
        mSession.getFishBillDao().deleteInTx(fishBills);
    }

    /**
     * 删除本地所有账单
     */
    public void deleteAllBills() {
        deleteBills(getFishBills());
    }

    public Observable<Long> deleteFishBillById(Long id) {
        mSession.getFishBillDao().deleteByKey(id);
        return Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> e) throws Exception {
                e.onNext(new Long(0));
                e.onComplete();
            }
        });
    }

    /******************************Rx**************************************/
    private <T> Observable<T> queryToRx(final QueryBuilder<T> builder) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                T data = builder.list().get(0);
                e.onNext(data);
                e.onComplete();
            }
        });
    }

    private <T> Observable<List<T>> queryListToRx(final QueryBuilder<T> builder) {
        return Observable.create(new ObservableOnSubscribe<List<T>>() {
            @Override
            public void subscribe(ObservableEmitter<List<T>> e) throws Exception {
                List<T> data = builder.list();
                e.onNext(data);
                e.onComplete();
            }
        });
    }

}
