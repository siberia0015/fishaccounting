package com.fishfishfish.fishaccounting.ui.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.fishfishfish.fishaccounting.R;
import com.fishfishfish.fishaccounting.model.bean.local.FishBill;
import com.fishfishfish.fishaccounting.model.bean.local.FishPay;
import com.fishfishfish.fishaccounting.model.bean.local.FishSort;
import com.fishfishfish.fishaccounting.model.bean.local.NoteBean;
import com.fishfishfish.fishaccounting.mvp.presenter.Imp.BillPresenterImp;
import com.fishfishfish.fishaccounting.mvp.view.BillView;
import com.fishfishfish.fishaccounting.utils.DateUtils;
import com.fishfishfish.fishaccounting.utils.ProgressUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.fishfishfish.fishaccounting.utils.DateUtils.FORMAT_M;
import static com.fishfishfish.fishaccounting.utils.DateUtils.FORMAT_Y;
import static com.fishfishfish.fishaccounting.utils.DateUtils.FORMAT_YMD;

/**
 * 修改账单
 */
public class BillEditActivity extends BillAddActivity implements BillView {

    //old Bill
    private Bundle bundle;

    @Override
    protected int getLayout() {
        return R.layout.activity_add;
    }

    @Override
    protected void initEventAndData() {

        presenter = new BillPresenterImp(this);
        //获取旧数据
        setOldBill();

        //初始化分类数据
        initSortView();

        //设置日期选择器初始日期
        mYear = Integer.parseInt(DateUtils.getCurYear(FORMAT_Y));
        mMonth = Integer.parseInt(DateUtils.getCurMonth(FORMAT_M));

    }

    /**
     * 获取旧数据
     */
    private void setOldBill() {

        bundle = getIntent().getBundleExtra("bundle");
        if (bundle == null)
            return;
        //设置账单日期
        days = DateUtils.long2Str(bundle.getLong("date"), FORMAT_YMD);
        dateTv.setText(days);
        isOutcome = !bundle.getBoolean("income");
        remarkInput = bundle.getString("content");
        DecimalFormat df = new DecimalFormat("######0.00");
        String money = df.format(bundle.getDouble("cost"));
        //小数取整
        num = money.split("\\.")[0];
        //截取小数部分
        dotNum = "." + money.split("\\.")[1];

        //设置金额
        moneyTv.setText(num + dotNum);
    }


    /**
     * 通过name查询分类信息
     *
     * @param name
     * @return
     */
    private FishSort findSortByName(String name) {
        if (isOutcome) {
            for (FishSort e : noteBean.getOutSortlis()) {
                if (e.getSortName() == name)
                    return e;
            }
        } else {
            for (FishSort e : noteBean.getInSortlis()) {
                if (e.getSortName() == name)
                    return e;
            }
        }
        return null;
    }

    @Override
    public void loadDataSuccess(NoteBean tData) {
        noteBean = tData;
        //成功后加载布局
        setTitleStatus();
    }

    /**
     * 通过name查找支付方式，返回其数组中的序号
     *
     * @param name
     * @return
     */
    private int findPayByName(String name) {
        List<FishPay> list = noteBean.getPayinfo();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPayName() == name)
                return i;
        }
        return 0;
    }

    /**
     * 设置状态
     */
    protected void setTitleStatus() {

        //设置类型
        setTitle();

        lastBean = findSortByName(bundle.getString("sortName"));
        //当前分类未查询到次账单的分类
        //存在该分类被删除的情况，以及切换账单收入支出类型
        if (lastBean == null)
            lastBean = mDatas.get(0);
        sortTv.setText(lastBean.getSortName());

        cardItem = new ArrayList<>();
        for (int i = 0; i < noteBean.getPayinfo().size(); i++) {
            String itemStr = noteBean.getPayinfo().get(i).getPayName();
            cardItem.add(itemStr);
        }
        //设置支付方式
        selectedPayinfoIndex = findPayByName(bundle.getString("payName"));
        cashTv.setText(cardItem.get(selectedPayinfoIndex));

        initViewPager();
    }

    /**
     * 提交账单
     */
    public void doCommit() {
        final SimpleDateFormat sdf = new SimpleDateFormat(" HH:mm:ss");
        final String crDate = days + sdf.format(new Date());
        if ((num + dotNum).equals("0.00")) {
            Toast.makeText(this, "您还没有输入金额", Toast.LENGTH_SHORT).show();
            return;
        }

        ProgressUtils.show(mContext, "正在提交...");
        presenter.update(new FishBill(bundle.getLong("id"), bundle.getString("rid"),
                Float.valueOf(num + dotNum), remarkInput, currentUser.getObjectId(),
                noteBean.getPayinfo().get(selectedPayinfoIndex).getPayName(),
                noteBean.getPayinfo().get(selectedPayinfoIndex).getPayImg(),
                lastBean.getSortName(), lastBean.getSortImg(),
                DateUtils.getMillis(crDate), !isOutcome, bundle.getInt("version") + 1));
    }


}
