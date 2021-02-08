package com.example.administrator.kuaidi.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.kuaidi.Activity.CommentsListActivity;
import com.example.administrator.kuaidi.Activity.SetCommentsActivity;
import com.example.administrator.kuaidi.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyFragment extends Fragment {

    private final String TAG = "++";
    @BindView(R.id.qyFriendsComment)
    Button qyFriendsComment;
    @BindView(R.id.sendInfo)
    Button sendInfo;

    Unbinder unbinder;

    public MyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my, container, false);

        unbinder = ButterKnife.bind(this, view);

        qyFriendsComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CommentsListActivity.class);
                startActivity(intent);
            }
        });

        sendInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SetCommentsActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.e(TAG, "setUserVisibleHint:my true");
        } else {
            Log.e(TAG, "setUserVisibleHint:my false");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG, "onAttach: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, "onDetach: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "onDestroyView: ");
        unbinder.unbind();
    }

}
