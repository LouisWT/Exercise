package com.example.exercise.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.exercise.R;

public class TextImageButton extends LinearLayout {
    private ImageView mButtonImage;
    private int mImageSource;
    private TextView mButtonText;
    private int mTextContent;
    private float textSize = 18.0f;

    public TextImageButton(Context context, AttributeSet attrs) {
        super(context);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TextImageButton, 0, 0);

        this.textSize = a.getDimension(R.styleable.TextImageButton_tib_btn_text_size, 0);
        this.mImageSource = a.getResourceId(R.styleable.TextImageButton_tib_btn_image, 0);
        this.mTextContent = a.getResourceId(R.styleable.TextImageButton_tib_btn_text, 0);
        this.mButtonImage = new ImageView(context);
        this.mButtonText = new TextView(context);
        initView();

        a.recycle();
    }

    public void initView() {
        mButtonImage.setImageResource(mImageSource);
        mButtonText.setText(mTextContent);
        mButtonText.setTextSize(textSize);

        mButtonImage.setScaleType(ImageView.ScaleType.FIT_END);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, 0, 0.5f);
        // Set ImageView LayoutParams : default half
        mButtonImage.setLayoutParams(layoutParams);
        // Set TextView LayoutParams : default half
        mButtonText.setLayoutParams(layoutParams);
        // Set Text Gravity : default center
        mButtonText.setGravity(Gravity.START);
        // Set Text Color : default white
        mButtonText.setTextColor(getResources().getColor(R.color.white));

        /** Set Father View(LinearLayout) properties */
        setClickable(true);
        setFocusable(true);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT, 1);
//		params.gravity = Gravity.CENTER;
        // Set Father View Orientation : default fulfill
        super.setLayoutParams(params);

        // Set Father View Orientation : default VERTICAL
        super.setOrientation(LinearLayout.HORIZONTAL);

        addView(mButtonImage);
        addView(mButtonText);
    }
}
