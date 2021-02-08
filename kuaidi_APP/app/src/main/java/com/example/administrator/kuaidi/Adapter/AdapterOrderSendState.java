package com.example.administrator.kuaidi.Adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.kuaidi.Http.HttpBean.RepMykuaidi;
import com.example.administrator.kuaidi.ModelBean.OrderSendStateBean;
import com.example.administrator.kuaidi.ModelBean.UserInfoItem;
import com.example.administrator.kuaidi.R;

import java.util.List;

public class AdapterOrderSendState extends BaseQuickAdapter<RepMykuaidi.ListState,BaseViewHolder> {

    public AdapterOrderSendState(@LayoutRes int layourResId, @Nullable List<RepMykuaidi.ListState> data){
        super(layourResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RepMykuaidi.ListState item) {

        helper.setText(R.id.orderstate_msg,item.getMessage())
                .setText(R.id.orderstate_time,item.getTime());
    }
}