package com.example.administrator.kuaidi.Widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.kuaidi.ModelBean.ImagePiece;
import com.example.administrator.kuaidi.ModelBean.ImageSplitter;
import com.example.administrator.kuaidi.R;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GamePintuLayout extends RelativeLayout implements View.OnClickListener{

    private int mColumn = 3;
    private int mWidth;
    private int mPadding;
    private int mMargin = 3;
    private int mItemWidth;

    private Bitmap mBitmap;
    private List<ImagePiece> mItemBitmaps;
    private ImageView[] mGamePintuItems;

    private boolean once;

    public GamePintuLayout(Context context){
        this(context, null);
    }

    public GamePintuLayout(Context context, AttributeSet attrs){
        this(context, attrs, 0);
    }

    public GamePintuLayout(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        mMargin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mMargin, getResources().getDisplayMetrics());
        mPadding = min(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = Math.min(getMeasuredHeight(), getMeasuredWidth());

        if(!once){

        }
    }

    private void initBitmap(){
        if(mBitmap == null){
            mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.back);

            mItemBitmaps = ImageSplitter.split(mBitmap, mColumn);

            Collections.sort(mItemBitmaps, new Comparator<ImagePiece>() {
                @Override
                public int compare(ImagePiece imagePiece, ImagePiece t1) {
                    return Math.random() > 0.5 ? 1 : -1;
                }
            });
        }
    }

    private void initItem(){
        int childWidth = (mWidth - mPadding*2 - mMargin * (mColumn - 1))/mColumn;
        mItemWidth = childWidth;

        mGamePintuItems = new ImageView[mColumn * mColumn];
        for(int i = 0; i < mGamePintuItems.length; i++){
            ImageView item = new ImageView(getContext());
            item.setOnClickListener(this);

            item.setImageBitmap(mItemBitmaps.get(i).bitmap);
            mGamePintuItems[i] = item;
            item.setId(i + 1);
            item.setTag(i + "_" + mItemBitmaps.get(i).index);

            RelativeLayout.LayoutParams lp = new LayoutParams(mItemWidth, mItemWidth);

            if((i + 1) % mColumn != 0){
                lp.rightMargin = mMargin;
            }

            if(i % mColumn != 0){
                lp.addRule(RelativeLayout.RIGHT_OF, mGamePintuItems[i - 1].getId());
            }

            if(i + 1 > mColumn){
                lp.topMargin = mMargin;
                lp.addRule(RelativeLayout.BELOW, mGamePintuItems[i - mColumn].getId());
            }
            addView(item, lp);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private int min(int ...params){
        int minValue = params[0];
        for(int param : params){
            if(minValue > param){
                minValue = param;
            }
        }
        return minValue;
    }

    @Override
    public void onClick(View view) {

    }
}
