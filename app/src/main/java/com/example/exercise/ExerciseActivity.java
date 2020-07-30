package com.example.exercise;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.exercise.helper.CountDownTimerWithPause;
import com.example.exercise.widget.LeaveDialogFragment;

import java.util.Locale;

public class ExerciseActivity extends AppCompatActivity {
    private TextView timer;
    private Button controlButton;
    private boolean curr = false;
    private ExerciseConstant.ControlStatus controlStatus = ExerciseConstant.ControlStatus.PLAY;
    private int leftTimes;
    private boolean up = false;
    private ExerciseConstant.Status status = ExerciseConstant.Status.SQUEEZE;
    private CountDownTimerWithPause countDownTimer;
    private Handler handler;
    private LeaveDialogFragment leaveDialogFragment;

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
        controlButton = findViewById(R.id.control_button);
        controlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean status = countDownTimer.ismPaused();
                if (status) {
                    countDownTimer.resume();
                    controlButton.setText(R.string.pause);
                } else {
                    countDownTimer.pause();
                    controlButton.setText(R.string.play);
                }
            }
        });
        // 开始计时
        setCountDownTimer(ExerciseConstant.TotalTimeInQuick, false, ExerciseConstant.TotalQuickCount);
        handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what == 0) {
                    final ScaleAnimation scaleAnimation = new ScaleAnimation(1, 1.3f, 1, 1.3f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    scaleAnimation.setDuration(1000);
                    scaleAnimation.setFillAfter(true);
                    timer.startAnimation(scaleAnimation);
                } else {
                    final ScaleAnimation scaleAnimationReverse = new ScaleAnimation(1.3f, 1, 1.3f, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    scaleAnimationReverse.setDuration(1000);
                    scaleAnimationReverse.setFillAfter(true);
                    timer.startAnimation(scaleAnimationReverse);
                }
                return true;
            }
        });
        // 离开的对话框
        leaveDialogFragment = new LeaveDialogFragment();
    }

    public void setCountDownTimer(final int duration, final boolean up, final int leftTimes) {
        if (this.countDownTimer != null) {
            return;
        }
        this.up = up;
        this.leftTimes = leftTimes;
        this.countDownTimer = new CountDownTimerWithPause(duration, 500) {;
            @Override
            public void onTick(long mills) {
                if (mills == 0) {
                    timer.setText(String.format(Locale.getDefault(), "%d", duration));
                    return;
                }
                if (up) {
                    timer.setText(String.format(Locale.getDefault(), "%.1f", (double) (duration - mills) / 1000));
                } else {
                    timer.setText(String.format(Locale.getDefault(), "%.1f", (double) mills / 1000));
                }
            }

//            @Override
//            public void onFinish() {
//                Message message = new Message();
//                if (status == ExerciseConstant.Status.SQUEEZE) {
//                    status = ExerciseConstant.Status.REST;
//                    message.what = 0;
//                    handler.sendMessage(message);
//                } else {
//                    status = ExerciseConstant.Status.SQUEEZE;
//                    message.what = 1;
//                    handler.sendMessage(message);
//                }
//                countDownTimer = null;
//                if (leftTimes > 0) {
//                    setCountDownTimer(duration, !up, leftTimes - 1);
//                }
//            }

            @Override
            public void onFinish() {
                if (!up) {
                    final ScaleAnimation scaleAnimation = new ScaleAnimation(1, 1.3f, 1, 1.3f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    scaleAnimation.setDuration(1000);
                    scaleAnimation.setFillAfter(true);
                    timer.startAnimation(scaleAnimation);
                } else {
                    final ScaleAnimation scaleAnimationReverse = new ScaleAnimation(1.3f, 1, 1.3f, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    scaleAnimationReverse.setDuration(1000);
                    scaleAnimationReverse.setFillAfter(true);
                    timer.startAnimation(scaleAnimationReverse);
                }
                countDownTimer = null;
                if (leftTimes > 0) {
                    setCountDownTimer(duration, !up, leftTimes - 1);
                }
            }
        };
        this.countDownTimer.start();
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
                leaveDialogFragment.show();
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}