package com.example.exercise;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.exercise.helper.DensityUtil;

import java.util.Locale;

public class ExerciseActivity extends AppCompatActivity {
    private TextView timer;
    private ExerciseConstant.Status status = ExerciseConstant.Status.SQUEEZE;
    private CountDownTimer countDownTimer;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(R.string.timer);
        }
        setContentView(R.layout.activity_exercise);
    }

    @Override
    protected void onStart() {
        super.onStart();
        timer = findViewById(R.id.timer);
        setCountDownTimer(ExerciseConstant.TotalTimeInQuick);
//        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(DensityUtil.dp2px(getApplicationContext(), 100f), DensityUtil.dp2px(getApplicationContext(), 100f));
//        final ViewGroup.LayoutParams smallLayoutParams = new ViewGroup.LayoutParams(DensityUtil.dp2px(getApplicationContext(), 80f), DensityUtil.dp2px(getApplicationContext(), 80f));

        handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what == 0) {
                    ViewGroup.LayoutParams layoutParams =  timer.getLayoutParams();
                    layoutParams.height = DensityUtil.dp2px(getApplicationContext(), 100f);
                    layoutParams.width = DensityUtil.dp2px(getApplicationContext(), 100f);
                    timer.setLayoutParams(layoutParams);
                } else {
                    ViewGroup.LayoutParams layoutParams =  timer.getLayoutParams();
                    layoutParams.height = DensityUtil.dp2px(getApplicationContext(), 80f);
                    layoutParams.width = DensityUtil.dp2px(getApplicationContext(), 80f);
                    timer.setLayoutParams(layoutParams);
                }
                return true;
            }
        });
//        Looper.loop();
    }

    public void setCountDownTimer(final int duration) {
        if (this.countDownTimer != null) {
            return;
        }
        this.countDownTimer = new CountDownTimer(duration, 500) {;
            @Override
            public void onTick(long mills) {
                if (mills == 0) {
                    timer.setText(String.format(Locale.getDefault(), "%d", duration));
                    return;
                }
                timer.setText(String.format(Locale.getDefault(), "%.1f", (double) mills / 1000));
            }

            @Override
            public void onFinish() {
                Message message = new Message();
                if (status == ExerciseConstant.Status.SQUEEZE) {
                    status = ExerciseConstant.Status.REST;
                    message.what = 0;
//                    changeTimerSize(true);
                    handler.sendMessage(message);
                } else {
                    status = ExerciseConstant.Status.SQUEEZE;
                    message.what = 1;
//                    changeTimerSize(false);
                    handler.sendMessage(message);
                }
//                Looper.loop();
                countDownTimer = null;
                setCountDownTimer(duration);
            }
        };
        this.countDownTimer.start();
    }

    private void changeTimerSize(boolean isBigger) {
//        if (isBigger) {
//            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(DensityUtil.dp2px(getApplicationContext(), 100f), DensityUtil.dp2px(getApplicationContext(), 100f));
//            timer.setLayoutParams(layoutParams);
//        } else {
//            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(DensityUtil.dp2px(getApplicationContext(), 80f), DensityUtil.dp2px(getApplicationContext(), 80f));
//            timer.setLayoutParams(layoutParams);
//        }
//        timer.setPadding(0, 0, 0, 0);
        Message message = new Message();
        message.what = isBigger ? 0 : 1;
        handler.sendMessage(message);
    }

    @Override
    public void finish() {
        this.countDownTimer.cancel();
        super.finish();
        overridePendingTransition(R.anim.horizontal_slide_reverse_back, R.anim.horizontal_slide_reverse);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}