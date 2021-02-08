package com.example.administrator.kuaidi.Adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.kuaidi.Http.HttpBean.RepMykuaidi;
import com.example.administrator.kuaidi.ModelBean.UserInfoItem;
import com.example.administrator.kuaidi.R;

import java.util.List;

public class AdapterMyOrder extends BaseQuickAdapter<RepMykuaidi,BaseViewHolder> {

    public AdapterMyOrder(@LayoutRes int layourResId, @Nullable List<RepMykuaidi> data) {
        super(layourResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RepMykuaidi item) {

        helper.setText(R.id.order_numval, item.getNumber())
                .setText(R.id.order_rnameval, item.getReceiver())
                //.addOnClickListener(R.id.fd_addbtn)
                .addOnClickListener(R.id.order_rl);

    }
}
