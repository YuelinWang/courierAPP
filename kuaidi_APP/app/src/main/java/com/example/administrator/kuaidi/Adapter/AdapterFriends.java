package com.example.administrator.kuaidi.Adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.kuaidi.Http.HttpBean.FriendComments;
import com.example.administrator.kuaidi.ModelBean.Model_RecyView_ActMain;
import com.example.administrator.kuaidi.ModelBean.UserInfoItem;
import com.example.administrator.kuaidi.R;

import java.util.List;

public class AdapterFriends extends BaseQuickAdapter<UserInfoItem,BaseViewHolder> {

    private String TAG = "++";
    public AdapterFriends(@LayoutRes int layourResId, @Nullable List<UserInfoItem> data){
        super(layourResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserInfoItem item) {
        int status = item.getStatus();
        Log.e(TAG, "convert: "+ status );
        if(status == 0){
            helper.setVisible(R.id.fd_ckbox,false);
            helper.setVisible(R.id.fd_addbtn,true);
        }else if(status == 1){
            helper.setVisible(R.id.fd_ckbox,false);
            helper.setVisible(R.id.fd_addbtn,false);
        }else{
            helper.setVisible(R.id.fd_ckbox,true);
            helper.setVisible(R.id.fd_addbtn,false);
        }
        helper.setText(R.id.fd_title,item.getRealname())
                .addOnClickListener(R.id.fd_addbtn)
                .addOnClickListener(R.id.fd_rl);

    }
}
