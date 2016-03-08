package com.mytime.exercise.view;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class MuseoSans300TextView extends TextView{

    private static final String ASSET_PATH = "fonts/MuseoSans-300.otf";

    public MuseoSans300TextView(Context context) {
        super(context);
        setFont();
    }

    public MuseoSans300TextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont();
    }

    public MuseoSans300TextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont();
    }

    private void setFont(){
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), ASSET_PATH), Typeface.NORMAL);
    }
}
