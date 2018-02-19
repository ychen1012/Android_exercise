package com.example.yangchen.downupview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import org.jetbrains.annotations.Nullable;

/**
 * Created by yangchen on 2018/2/6.
 */

public class DropDownmenu extends LinearLayout {
    //顶部菜单布局
    private LinearLayout tabMenuView;
    //容器布局 整体中下部分布局 内容遮罩，
    private FrameLayout containerViewFrameLayout;
    //内容区域
    private View contentView;
    //遮罩区域
    private View maskView;
    //菜单弹出区域
    private FrameLayout popupMenuViews;



    //分割线颜色；
    private  int divideColor = 0xffcccccc;
    //文本选中颜色；
    private int textselectedColor =0x890c85;
    //文本未被选中颜色；
    private int textUnselectedColor =0xff111111;
    //遮罩颜色；
    private int maskColor =0x88888888;
    //菜单背景颜色；
    private int menuBackGroundColor =0xffffffff;
    //水平分割线颜色；
    private int underlineColor = 0xffcccccc;
    //字体大小；
    private int  textSize = 14;
    //tab选中图标
    private int menuSelectedIcon;
    //tab未选中
    private int menuUnSelectedIcon;





    public DropDownmenu(Context context) {
        super(context);
    }

    public DropDownmenu(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    public DropDownmenu(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.dropDownMenu);
        underlineColor = a.getColor(R.styleable.dropDownMenu_underlineColor, underlineColor);
        divideColor = a.getColor(R.styleable.dropDownMenu_dividerColor, divideColor);
        textselectedColor = a.getColor(R.styleable.dropDownMenu_textSelectedColor, textselectedColor);
        textUnselectedColor = a.getColor(R.styleable.dropDownMenu_textUnselectedColor, textUnselectedColor);
        menuBackGroundColor = a.getColor(R.styleable.dropDownMenu_menuBackGroudColor, menuBackGroundColor);
        maskColor = a.getColor(R.styleable.dropDownMenu_maskColor, maskColor);
        textSize = a.getDimensionPixelSize(R.styleable.dropDownMenu_menuTextSize, textSize);
        menuSelectedIcon = a.getResourceId(R.styleable.dropDownMenu_menuSelectedIcon, menuSelectedIcon);
        menuUnSelectedIcon = a.getResourceId(R.styleable.dropDownMenu_menuSelectedIcon, menuUnSelectedIcon);
        a.recycle();

        initViews();

    }
        private void initViews(){

    }

    }

