package com.example.administrator.kuaidi.Adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.kuaidi.Http.HttpBean.FriendComments;
import com.example.administrator.kuaidi.ModelBean.UserInfoItem;
import com.example.administrator.kuaidi.R;

import java.util.List;

public class AdapterCommentsList extends BaseQuickAdapter<FriendComments,BaseViewHolder> {

    public AdapterCommentsList(@LayoutRes int layourResId, @Nullable List<FriendComments> data) {
        super(layourResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendComments item) {
        helper.setText(R.id.cts_name, item.getUsername())
                .setText(R.id.cts_contents, item.getContent())
                .setText(R.id.cts_time, item.getTime());
    }
}
