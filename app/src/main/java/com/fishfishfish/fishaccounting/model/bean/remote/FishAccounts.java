package com.fishfishfish.fishaccounting.model.bean.remote;

import com.fishfishfish.fishaccounting.model.bean.local.FishBill;

import cn.bmob.v3.BmobObject;

/**
 * 服务器端账单bean
 */
public class FishAccounts extends BmobObject {

    private float cost;  //金额
    private String content;  //内容
    private String userid;  //用户id
    private String payName;  //支付方式
    private String payImg;  //
    private String sortName;  //账单分类
    private String sortImg;  //
    private long crdate;  //创建时间
    private boolean income;  //收入支出
    private int version;  //版本

    public FishAccounts() {

    }

    public FishAccounts(FishBill fishBill) {
        this.cost = fishBill.getCost();
        this.content = fishBill.getContent();
        this.userid = fishBill.getUserid();
        this.payName = fishBill.getPayName();
        this.payImg = fishBill.getPayImg();
        this.sortName = fishBill.getSortName();
        this.sortImg = fishBill.getSortImg();
        this.crdate = fishBill.getCrdate();
        this.income = fishBill.getIncome();
        this.version = fishBill.getVersion();
        //不要忘记设置服务器ObjectId
        if (fishBill.getRid() != null)
            setObjectId(fishBill.getRid());
    }

    public FishAccounts(float cost, String content, String userid, String payName, String payImg,
                        String sortName, String sortImg, long crdate, boolean income, int version) {
        this.cost = cost;
        this.content = content;
        this.userid = userid;
        this.payName = payName;
        this.payImg = payImg;
        this.sortName = sortName;
        this.sortImg = sortImg;
        this.crdate = crdate;
        this.income = income;
        this.version = version;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getPayImg() {
        return payImg;
    }

    public void setPayImg(String payImg) {
        this.payImg = payImg;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortImg() {
        return sortImg;
    }

    public void setSortImg(String sortImg) {
        this.sortImg = sortImg;
    }

    public long getCrdate() {
        return crdate;
    }

    public void setCrdate(long crdate) {
        this.crdate = crdate;
    }

    public boolean isIncome() {
        return income;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean getIncome() {
        return this.income;
    }

    public void setIncome(boolean income) {
        this.income = income;
    }
}
