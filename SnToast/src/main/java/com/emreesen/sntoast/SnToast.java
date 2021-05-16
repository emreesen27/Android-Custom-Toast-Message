package com.emreesen.sntoast;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

/**
 * @author Aydın Emre ESEN
 * @version 1.0.0
 * @since 2020-05-16
 */
public class SnToast {
    private static Dialog dialog;
    private static final int DURATION = 3000;
    private static final boolean ANIMATION = true;

    public enum ToastType {
        Success, Error, Info, Warning
    }

    /**
     * @param type    indicates the message typ
     * @param context for example: MainActivity.this
     */
    public static void standard(
            @NonNull Context context,
            @NonNull String msg,
            @NonNull ToastType type) {

        draw(context, msg, type, DURATION, ANIMATION);
    }

    /**
     * @param duration Indicates the period of time the message will be shown
     */
    public static void standard(
            @NonNull Context context,
            @NonNull String msg,
            @NonNull ToastType type,
            int duration) {

        draw(context, msg, type, duration, ANIMATION);
    }

    /**
     * @param animation You can cancel the animation. animation = false
     */
    public static void standard(
            @NonNull Context context,
            @NonNull String msg,
            @NonNull ToastType type,
            boolean animation) {

        draw(context, msg, type, DURATION, animation);
    }

    public static void standard(
            @NonNull Context context,
            @NonNull String msg,
            @NonNull ToastType type,
            int duration,
            boolean animation) {

        draw(context, msg, type, duration, animation);
    }

    /**
     * Use to create a message type other than enum.
     */
    public static void custom(
            @NonNull Context context,
            @NonNull String msg,
            int backgroundColor,
            int textColor,
            int icon) {

        customDraw(context, msg, backgroundColor, textColor, icon, DURATION, ANIMATION);
    }

    public static void custom(
            @NonNull Context context,
            @NonNull String msg,
            int backgroundColor,
            int textColor,
            int icon,
            int duration) {

        customDraw(context, msg, backgroundColor, textColor, icon, duration, ANIMATION);
    }

    public static void custom(
            @NonNull Context context,
            @NonNull String msg,
            int backgroundColor,
            int textColor,
            int icon,
            boolean animation) {

        customDraw(context, msg, backgroundColor, textColor, icon, DURATION, animation);
    }

    public static void custom(
            @NonNull Context context,
            @NonNull String msg,
            int backgroundColor,
            int textColor,
            int icon,
            int duration,
            boolean animation) {

        customDraw(context, msg, backgroundColor, textColor, icon, duration, animation);
    }

    private static void draw(
            @NonNull Context context,
            @NonNull String msg,
            @NonNull ToastType type,
            int duration,
            boolean animation) {

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.toast_layout);
        TextView tvMsg = dialog.findViewById(R.id.tv_msg);
        tvMsg.setText(msg);

        setDesign(type, context);
        setWindowProperties();
        dialog.show();

        if (animation) {
            startAnimation(dialog.findViewById(R.id.image));
        }

        final Handler handler = new Handler();
        handler.postDelayed(dialog::dismiss, duration);

    }

    private static void customDraw(
            @NonNull Context context,
            @NonNull String msg,
            int backgroundColor,
            int textColor,
            int icon,
            int duration,
            boolean animation) {

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.toast_layout);
        TextView tvMsg = dialog.findViewById(R.id.tv_msg);
        tvMsg.setText(msg);

        setCustomDesign(backgroundColor, textColor, icon, context);
        setWindowProperties();
        dialog.show();

        if (animation) {
            startAnimation(dialog.findViewById(R.id.image));
        }

        final Handler handler = new Handler();
        handler.postDelayed(dialog::dismiss, duration);
    }

    private static void startAnimation(View view) {
        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                view,
                PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.2f));
        scaleDown.setDuration(500);
        scaleDown.setRepeatCount(ObjectAnimator.INFINITE);
        scaleDown.setRepeatMode(ObjectAnimator.REVERSE);
        scaleDown.start();
    }

    private static void setWindowProperties() {
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        window.setAttributes(wlp);
    }

    private static void setDesign(@NonNull ToastType type, @NonNull Context context) {
        LinearLayout mainLayout = dialog.findViewById(R.id.main_layout);
        ImageView image = dialog.findViewById(R.id.image);
        if (ToastType.Info == type) {
            mainLayout.setBackgroundColor(context.getColor(R.color.infoColor));
            image.setImageResource(R.drawable.ic_information);
        } else if (ToastType.Error == type) {
            mainLayout.setBackgroundColor(context.getColor(R.color.errorColor));
            image.setImageResource(R.drawable.ic_error);
        } else if (ToastType.Success == type) {
            mainLayout.setBackgroundColor(context.getColor(R.color.successColor));
            image.setImageResource(R.drawable.ic_success);
        } else if (ToastType.Warning == type) {
            mainLayout.setBackgroundColor(context.getColor(R.color.warningColor));
            image.setImageResource(R.drawable.ic_warning);
        }
    }

    private static void setCustomDesign(int backgroundColor, int textColor, int icon, Context context) {
        LinearLayout mainLayout = dialog.findViewById(R.id.main_layout);
        ImageView image = dialog.findViewById(R.id.image);
        TextView tvMsg = dialog.findViewById(R.id.tv_msg);
        tvMsg.setTextColor(context.getColor(textColor));
        mainLayout.setBackgroundColor(context.getColor(backgroundColor));
        image.setImageResource(icon);
    }
}
