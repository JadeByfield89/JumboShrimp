package com.permoveo.apps.jumboshrimp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class WidthSquareImageView extends ImageView {

    public WidthSquareImageView(Context context) {
        super(context);
    }

    public WidthSquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WidthSquareImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
