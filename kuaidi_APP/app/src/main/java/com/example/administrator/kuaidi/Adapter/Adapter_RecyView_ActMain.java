package com.example.administrator.kuaidi.Adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.kuaidi.ModelBean.Model_RecyView_ActMain;
import com.example.administrator.kuaidi.R;

import java.util.List;

public class Adapter_RecyView_ActMain extends BaseQuickAdapter<Model_RecyView_ActMain,BaseViewHolder> {
    public Adapter_RecyView_ActMain(@LayoutRes int layourResId, @Nullable List<Model_RecyView_ActMain> data){
        super(layourResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Model_RecyView_ActMain item) {
        helper.setText(R.id.tv_title,item.getTitle())
                .setText(R.id.tv_content,item.getContent())
                .addOnClickListener(R.id.tv_content)
                .setImageResource(R.id.iv_img, R.mipmap.me)
                .addOnClickListener(R.id.iv_img);
    }
}
