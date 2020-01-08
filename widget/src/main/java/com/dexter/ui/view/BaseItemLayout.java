package com.dexter.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.dexter.ui.R;
import com.dexter.ui.util.ButtonUtils;
import com.dexter.ui.util.ViewUtils;

/**
 * Created by dexter on 2019/8/13.
 */
@SuppressWarnings("UnusedReturnValue")
@SuppressLint("Recycle")
public class BaseItemLayout extends LinearLayout {

    View lineView;
    AppCompatTextView textTv;
    AppCompatTextView labelTv;
    AppCompatImageView arrowImg;
    LinearLayout bodyLayout;

    Context ctx;

    String text;
    String label;
    int textColor = ColorUtils.getColor(R.color.colorText);
    int labelColor = ColorUtils.getColor(R.color.colorLabelText);
    boolean hasArrow;
    boolean isLineFull;
    boolean isClickable;

    OnClickListener onClickListener;


    public BaseItemLayout(Context context) {
        this(context, null);
    }

    public BaseItemLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseItemLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public BaseItemLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        ctx = context;
        inflate(ctx, R.layout.layout_base_item, this);
        bindView();
        initAttr(attrs);
        initView();
    }

    private void bindView() {
        lineView = findViewById(R.id.line);
        textTv = findViewById(R.id.tv_text);
        labelTv = findViewById(R.id.tv_label);
        arrowImg = findViewById(R.id.img_arrow);
        bodyLayout = findViewById(R.id.layout_base_item_body);
    }

    @SuppressLint("CheckResult")
    private void initView() {
        textTv.setText(text == null ? "null" : text);
        labelTv.setText(label == null ? "null" : label);
        textTv.setTextColor(textColor);
        labelTv.setTextColor(labelColor);
        arrowImg.setVisibility(hasArrow ? View.VISIBLE : View.GONE);
        setLineLeadingMargin();
        if (isClickable) {
            bodyLayout.setOnClickListener(v -> {
                if (ButtonUtils.isFastDoubleClick(R.id.layout_base_item_body)) return;
                if (onClickListener != null) {
                    onClickListener.onClick(v);
                }
            });
        }
    }

    public AppCompatTextView getTextView() {
        return textTv;
    }

    public BaseItemLayout setText(String text) {
        if (text == null) return this;
        this.text = text;
        textTv.setText(text);
        return this;
    }

    public BaseItemLayout setText(CharSequence charSequence) {
        if (charSequence == null) return this;
        textTv.setText(charSequence);
        this.text = textTv.getText().toString();
        return this;
    }

    public BaseItemLayout setIsLineFull(boolean isLineFull) {
        this.isLineFull = isLineFull;
        setLineLeadingMargin();
        return this;
    }

    public BaseItemLayout setLabel(String label) {
        if (label == null) return this;
        this.label = label;
        labelTv.setText(label);
        return this;
    }

    public BaseItemLayout setTextColor(@ColorRes int textColorRes) {
        this.textColor = ColorUtils.getColor(textColorRes);
        textTv.setTextColor(this.textColor);
        return this;
    }

    public BaseItemLayout setLabelColor(@ColorRes int labelColorRes) {
        this.labelColor = ColorUtils.getColor(labelColorRes);
        labelTv.setTextColor(this.labelColor);
        return this;
    }

    public void setClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    private void setLineLeadingMargin() {
        ViewUtils.setMargins(lineView, isLineFull ? 0 : ConvertUtils.dp2px(15), 0, 0, 0);
    }

    private void initAttr(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.BaseItemLayout);
        text = typedArray.getString(R.styleable.BaseItemLayout_base_item_text);
        label = typedArray.getString(R.styleable.BaseItemLayout_base_item_label);
        hasArrow = typedArray.getBoolean(R.styleable.BaseItemLayout_base_item_has_arrow, true);
        isLineFull = typedArray.getBoolean(R.styleable.BaseItemLayout_base_item_line_full, true);
        isClickable = typedArray.getBoolean(R.styleable.BaseItemLayout_base_item_clickable, true);
        textColor = typedArray.getColor(R.styleable.BaseItemLayout_base_item_text_color, textColor);
        labelColor = typedArray.getColor(R.styleable.BaseItemLayout_base_item_label_color, labelColor);
    }
}
