package com.fishfishfish.fishaccounting.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fishfishfish.fishaccounting.R;
import com.fishfishfish.fishaccounting.model.bean.local.FishSort;
import com.fishfishfish.fishaccounting.ui.activity.BillAddActivity;
import com.fishfishfish.fishaccounting.ui.activity.BillEditActivity;
import com.fishfishfish.fishaccounting.utils.ImageUtils;

import java.util.List;

/**
 * 账单分类Adapter（AddBillAdapter)
 */
public class BookNoteAdapter extends RecyclerView.Adapter<BookNoteAdapter.ViewHolder> {

    private BillAddActivity mContext;
    private BillEditActivity eContext;
    private LayoutInflater mInflater;
    private List<FishSort> mDatas;

    private OnBookNoteClickListener onBookNoteClickListener;

    public BookNoteAdapter(BillAddActivity context, List<FishSort> datas) {
        this.mContext = context;
        this.eContext = null;
        this.mInflater = LayoutInflater.from(context);
        this.mDatas = datas;

    }

    public BookNoteAdapter(BillEditActivity context, List<FishSort> datas) {
        this.mContext = null;
        this.eContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mDatas = datas;

    }

    public void setmDatas(List<FishSort> mDatas) {
        this.mDatas = mDatas;
    }

    public void setOnBookNoteClickListener(OnBookNoteClickListener listener) {
        if (onBookNoteClickListener == null)
            this.onBookNoteClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return (mDatas == null) ? 0 : mDatas.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_tb_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.title.setText(mDatas.get(position).getSortName());
        holder.img.setImageDrawable(ImageUtils.getDrawable(mDatas.get(position).getSortImg()));
    }

    /**
     * 自定义分类选择接口
     */
    public interface OnBookNoteClickListener {
        void OnClick(int index);

        void OnLongClick(int index);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private TextView title;
        private ImageView img;

        public ViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.item_tb_type_tv);
            img = (ImageView) view.findViewById(R.id.item_tb_type_img);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onBookNoteClickListener.OnClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            onBookNoteClickListener.OnLongClick(getAdapterPosition());
            return false;
        }
    }

}
