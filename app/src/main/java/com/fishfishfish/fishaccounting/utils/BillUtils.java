package com.fishfishfish.fishaccounting.utils;

import com.fishfishfish.fishaccounting.model.bean.local.FishBill;
import com.fishfishfish.fishaccounting.model.bean.local.MonthAccountBean;
import com.fishfishfish.fishaccounting.model.bean.local.MonthChartBean;
import com.fishfishfish.fishaccounting.model.bean.local.MonthDetailBean;
import com.fishfishfish.fishaccounting.model.bean.remote.FishAccounts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 包装账单展示列表工具类
 */
public class BillUtils {

    /**
     * 账单按天分类
     *
     * @param list
     * @return
     */
    public static MonthDetailBean packageDetailList(List<FishBill> list) {
        MonthDetailBean bean = new MonthDetailBean();
        float t_income = 0;
        float t_outcome = 0;
        List<MonthDetailBean.DaylistBean> daylist = new ArrayList<>();
        List<FishBill> beanList = new ArrayList<>();
        float income = 0;
        float outcome = 0;

        String preDay = "";  //记录前一天的时间
        for (int i = 0; i < list.size(); i++) {
            FishBill fishBill = list.get(i);
            //计算总收入支出
            if (fishBill.isIncome())
                t_income += fishBill.getCost();
            else
                t_outcome += fishBill.getCost();

            //判断后一个账单是否于前者为同一天
            if (i == 0 || preDay.equals(DateUtils.getDay(fishBill.getCrdate()))) {

                if (fishBill.isIncome())
                    income += fishBill.getCost();
                else
                    outcome += fishBill.getCost();
                beanList.add(fishBill);

                if (i == 0)
                    preDay = DateUtils.getDay(fishBill.getCrdate());
            } else {
                //局部变量防止引用冲突
                List<FishBill> tmpList = new ArrayList<>();
                tmpList.addAll(beanList);
                MonthDetailBean.DaylistBean tmpDay = new MonthDetailBean.DaylistBean();
                tmpDay.setList(tmpList);
                tmpDay.setMoney("支出：" + outcome + " 收入：" + income);
                tmpDay.setTime(preDay);
                daylist.add(tmpDay);

                //清空前一天的数据
                beanList.clear();
                income = 0;
                outcome = 0;

                //添加数据
                if (fishBill.isIncome())
                    income += fishBill.getCost();
                else
                    outcome += fishBill.getCost();
                beanList.add(fishBill);
                preDay = DateUtils.getDay(fishBill.getCrdate());
            }
        }

        if (beanList.size() > 0) {
            //局部变量防止引用冲突
            List<FishBill> tmpList = new ArrayList<>();
            tmpList.addAll(beanList);
            MonthDetailBean.DaylistBean tmpDay = new MonthDetailBean.DaylistBean();
            tmpDay.setList(tmpList);
            tmpDay.setMoney("支出：" + outcome + " 收入：" + income);
            tmpDay.setTime(DateUtils.getDay(beanList.get(0).getCrdate()));
            daylist.add(tmpDay);
        }

        bean.setT_income(String.valueOf(t_income));
        bean.setT_outcome(String.valueOf(t_outcome));
        bean.setDaylist(daylist);
        return bean;
    }

    /**
     * 账单按类型分类
     *
     * @param list
     * @return
     */
    public static MonthChartBean packageChartList(List<FishBill> list) {
        MonthChartBean bean = new MonthChartBean();
        float t_income = 0;
        float t_outcome = 0;

        Map<String, List<FishBill>> mapIn = new HashMap<>();
        Map<String, Float> moneyIn = new HashMap<>();
        Map<String, List<FishBill>> mapOut = new HashMap<>();
        Map<String, Float> moneyOut = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            FishBill fishBill = list.get(i);
            //计算总收入支出
            if (fishBill.isIncome()) t_income += fishBill.getCost();
            else t_outcome += fishBill.getCost();

            //账单分类
            String sort = fishBill.getSortName();
            List<FishBill> listBill;
            if (fishBill.isIncome()) {
                if (mapIn.containsKey(sort)) {
                    listBill = mapIn.get(sort);
                } else {
                    listBill = new ArrayList<>();
                }
                if (moneyIn.containsKey(sort))
                    moneyIn.put(sort, moneyIn.get(sort) + fishBill.getCost());
                else
                    moneyIn.put(sort, fishBill.getCost());
                listBill.add(fishBill);
                mapIn.put(sort, listBill);
            } else {
                if (mapOut.containsKey(sort)) {
                    listBill = mapOut.get(sort);
                } else {
                    listBill = new ArrayList<>();
                }
                if (moneyOut.containsKey(sort))
                    moneyOut.put(sort, moneyOut.get(sort) + fishBill.getCost());
                else
                    moneyOut.put(sort, fishBill.getCost());
                listBill.add(fishBill);
                mapOut.put(sort, listBill);
            }
        }

        List<MonthChartBean.SortTypeList> outSortlist = new ArrayList<>();    //账单分类统计支出
        List<MonthChartBean.SortTypeList> inSortlist = new ArrayList<>();    //账单分类统计收入

        for (Map.Entry<String, List<FishBill>> entry : mapOut.entrySet()) {
            MonthChartBean.SortTypeList sortTypeList = new MonthChartBean.SortTypeList();
            sortTypeList.setList(entry.getValue());
            sortTypeList.setSortName(entry.getKey());
            sortTypeList.setSortImg(entry.getValue().get(0).getSortImg());
            sortTypeList.setMoney(moneyOut.get(entry.getKey()));
            sortTypeList.setBack_color(StringUtils.randomColor());
            outSortlist.add(sortTypeList);
        }
        for (Map.Entry<String, List<FishBill>> entry : mapIn.entrySet()) {
            MonthChartBean.SortTypeList sortTypeList = new MonthChartBean.SortTypeList();
            sortTypeList.setList(entry.getValue());
            sortTypeList.setSortName(entry.getKey());
            sortTypeList.setSortImg(entry.getValue().get(0).getSortImg());
            sortTypeList.setMoney(moneyIn.get(entry.getKey()));
            sortTypeList.setBack_color(StringUtils.randomColor());
            inSortlist.add(sortTypeList);
        }

        bean.setOutSortlist(outSortlist);
        bean.setInSortlist(inSortlist);
        bean.setTotalIn(t_income);
        bean.setTotalOut(t_outcome);
        return bean;
    }

    /**
     * 账单按支付方式分类
     *
     * @param list
     * @return
     */
    public static MonthAccountBean packageAccountList(List<FishBill> list) {

        MonthAccountBean bean = new MonthAccountBean();
        float t_income = 0;
        float t_outcome = 0;

        Map<String, List<FishBill>> mapAccount = new HashMap<>();
        Map<String, Float> mapMoneyIn = new HashMap<>();
        Map<String, Float> mapMoneyOut = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            FishBill fishBill = list.get(i);
            //计算总收入支出
            if (fishBill.isIncome()) t_income += fishBill.getCost();
            else t_outcome += fishBill.getCost();

            String pay = fishBill.getPayName();

            if (mapAccount.containsKey(pay)) {
                List<FishBill> fishBills = mapAccount.get(pay);
                fishBills.add(fishBill);
                mapAccount.put(pay, fishBills);
            } else {
                List<FishBill> fishBills = new ArrayList<>();
                fishBills.add(fishBill);
                mapAccount.put(pay, fishBills);
            }

            if (fishBill.isIncome()) {
                if (mapMoneyIn.containsKey(pay)) {
                    mapMoneyIn.put(pay, mapMoneyIn.get(pay) + fishBill.getCost());
                } else {
                    mapMoneyIn.put(pay, fishBill.getCost());
                }
            } else {
                if (mapMoneyOut.containsKey(pay)) {
                    mapMoneyOut.put(pay, mapMoneyOut.get(pay) + fishBill.getCost());
                } else {
                    mapMoneyOut.put(pay, fishBill.getCost());
                }
            }
        }

        List<MonthAccountBean.PayTypeListBean> payTypeListBeans = new ArrayList<>();    //账单分类统计支出
        for (Map.Entry<String, List<FishBill>> entry : mapAccount.entrySet()) {
            MonthAccountBean.PayTypeListBean payTypeListBean = new MonthAccountBean.PayTypeListBean();
            payTypeListBean.setBills(entry.getValue());
            //先判断当前支付方式是否有输入或支出
            //因为有可能只有支出或收入
            if (mapMoneyIn.containsKey(entry.getKey()))
                payTypeListBean.setIncome(mapMoneyIn.get(entry.getKey()));
            if (mapMoneyOut.containsKey(entry.getKey()))
                payTypeListBean.setOutcome(mapMoneyOut.get(entry.getKey()));
            payTypeListBean.setPayImg(entry.getValue().get(0).getPayImg());
            payTypeListBean.setPayName(entry.getValue().get(0).getPayName());
            payTypeListBeans.add(payTypeListBean);
        }

        bean.setTotalIn(t_income);
        bean.setTotalOut(t_outcome);
        bean.setList(payTypeListBeans);
        return bean;
    }

    /**
     * FishAccounts=>FishBill
     *
     * @param fishAccounts
     * @return
     */
    public static FishBill toBBill(FishAccounts fishAccounts) {
        FishBill fishBill = new FishBill();
        fishBill.setRid(fishAccounts.getObjectId());
        fishBill.setVersion(fishAccounts.getVersion());
        fishBill.setIncome(fishAccounts.getIncome());
        fishBill.setCrdate(fishAccounts.getCrdate());
        fishBill.setSortImg(fishAccounts.getSortImg());
        fishBill.setSortName(fishAccounts.getSortName());
        fishBill.setPayImg(fishAccounts.getPayImg());
        fishBill.setPayName(fishAccounts.getPayName());
        fishBill.setUserid(fishAccounts.getUserid());
        fishBill.setContent(fishAccounts.getContent());
        fishBill.setCost(fishAccounts.getCost());

        return fishBill;
    }
}
