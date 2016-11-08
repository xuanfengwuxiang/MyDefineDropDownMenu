package com.itheima.mydefinedropdownmenu;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.tv_mingren)
    TextView mTvMingren;
    @InjectView(R.id.tv_zuozhu)
    TextView mTvZuozhu;
    @InjectView(R.id.tv_xiaoying)
    TextView mTvXiaoying;
    @InjectView(R.id.activity_main)
    HorizontalScrollView mActivityMain;

    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mTvMingren.setOnClickListener(new click());
        mTvZuozhu.setOnClickListener(new click());
        mTvXiaoying.setOnClickListener(new click());
        mPopupWindow = new PopupWindow(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(),(Bitmap) null));
    }

    private class click implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_mingren:
                    View popupViewMingren = View.inflate(getBaseContext(), R.layout.item_mingren,null);
                    loadPopupWindow(popupViewMingren,mTvMingren);
                    break;
                case R.id.tv_zuozhu:
                    View popupViewZuozhu = View.inflate(getBaseContext(),R.layout.item_zuozhu,null);
                    loadPopupWindow(popupViewZuozhu,mTvZuozhu);
                    break;
                case R.id.tv_xiaoying:
                    View popupViewXiaoying = View.inflate(getBaseContext(),R.layout.item_xiaoying,null);
                    loadPopupWindow(popupViewXiaoying,mTvXiaoying);
                    break;
            }
        }
    }

    private void loadPopupWindow(View popupView,View button) {
        int[] screen_pos = new int[2];
        button.getLocationOnScreen(screen_pos);
        //被点击的按钮矩形范围。
        Rect anchor_rect = new Rect(screen_pos[0], screen_pos[1], screen_pos[0] + button.getWidth(), screen_pos[1] + button.getHeight());
        int position_x = anchor_rect.centerX();
        int position_y = anchor_rect.bottom ;
        ScaleAnimation sa = new ScaleAnimation(0.3f, 1.0f,0.3f,1.0f, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(300);
        popupView.setAnimation(sa);
        mPopupWindow.setContentView(popupView);
        mPopupWindow.showAtLocation(button,Gravity.NO_GRAVITY,position_x,position_y);

    }
}
