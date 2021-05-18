package com.emreesen.sntoast;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author Aydın Emre ESEN
 * @version 1.0.0
 * @since 2020-05-16
 */

public class SnToast {
    private LinearLayout toastLayout;
    private ImageView toastIcon;
    private TextView toastMessage;


    private void init(
            @NonNull Context context,
            @NonNull String message,
            @Nullable Type type,
            boolean animation,
            int duration,
            int backgroundColor,
            int textColor,
            int icon
    ) {

        //creates dialog and set its settings
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.toast_layout);
        toastLayout = dialog.findViewById(R.id.toast_layout);
        toastIcon = dialog.findViewById(R.id.toast_icon);

        //Set message
        toastMessage = dialog.findViewById(R.id.toast_msg);
        toastMessage.setText(message);

        //Set window configurations
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        window.setAttributes(wlp);

        //set toast design
        if (type == null)
            setCustomDesign(backgroundColor, textColor, icon, context);
        else
            setDesign(type, context);

        //set animation
        if (animation)
            startAnimation();

        dialog.show();

        //Set duration
        final Handler handler = new Handler();
        handler.postDelayed(dialog::dismiss, duration);
    }

    private void setDesign(@NonNull Type type, @NonNull Context context) {
        if (Type.INFORMATION == type) {
            toastLayout.setBackgroundColor(context.getColor(R.color.infoColor));
            toastIcon.setImageResource(R.drawable.ic_information);
        } else if (Type.ERROR == type) {
            toastLayout.setBackgroundColor(context.getColor(R.color.errorColor));
            toastIcon.setImageResource(R.drawable.ic_error);
        } else if (Type.SUCCESS == type) {
            toastLayout.setBackgroundColor(context.getColor(R.color.successColor));
            toastIcon.setImageResource(R.drawable.ic_success);
        } else if (Type.WARNING == type) {
            toastLayout.setBackgroundColor(context.getColor(R.color.warningColor));
            toastIcon.setImageResource(R.drawable.ic_warning);
        }
    }

    private void setCustomDesign(int backgroundColor, int textColor, int icon, Context context) {
        toastMessage.setTextColor(context.getColor(textColor));
        toastLayout.setBackgroundColor(context.getColor(backgroundColor));
        toastIcon.setImageResource(icon);
    }

    private void startAnimation() {
        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                toastIcon,
                PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.2f));
        scaleDown.setDuration(500);
        scaleDown.setRepeatCount(ObjectAnimator.INFINITE);
        scaleDown.setRepeatMode(ObjectAnimator.REVERSE);
        scaleDown.start();
    }


    public static class Standard {
        private boolean animation = false;
        private int duration = 3000;
        private Context context;
        private String message;
        private Type type;

        public Standard() {
        }

        public Standard context(Context context) {
            this.context = context;
            if (context == null)
                throw new AssertionError("SnToast - Context cannot be null !!!");
            else
                return this;
        }

        public Standard message(String message) {
            this.message = message;
            if (message == null)
                throw new AssertionError("SnToast - Message cannot be null !!!");
            else
                return this;
        }

        public Standard type(Type type) {
            this.type = type;
            if (type == null)
                throw new AssertionError("SnToast - Type cannot be null !!!");
            else
                return this;
        }

        public Standard animation(boolean animation) {
            this.animation = animation;
            return this;
        }

        public Standard duration(int duration) {
            this.duration = duration;
            return this;
        }

        public void build() {
            if (context == null)
                throw new AssertionError("Context assignment is required.");
            if (message == null)
                throw new AssertionError("Message assignment is required.");
            if (type == null)
                throw new AssertionError("Type assignment is required.");

            SnToast snToast = new SnToast();
            snToast.init(context, message, type, animation, duration, 0, 0, 0);
        }
    }

    public static class Custom {
        private boolean animation = false;
        private int duration = 3000;
        private int backgroundColor = 0;
        private int textColor = 0;
        private int icon = 0;
        private Context context;
        private String message;

        public Custom() {
        }

        public Custom context(Context context) {
            this.context = context;
            if (context == null)
                throw new AssertionError("SnToast - Context cannot be null !!!");
            return this;
        }

        public Custom message(String message) {
            this.message = message;
            if (message == null)
                throw new AssertionError("SnToast - Message cannot be null !!!");
            else
                return this;
        }

        public Custom backgroundColor(int backgroundColor) {
            this.backgroundColor = backgroundColor;
            if (backgroundColor == 0)
                throw new AssertionError("SnToast - BackgroundColor cannot be null !!!");
            else
                return this;
        }

        public Custom textColor(int textColor) {
            this.textColor = textColor;
            if (textColor == 0)
                throw new AssertionError("SnToast - TextColor cannot be null !!!");
            else
                return this;
        }

        public Custom icon(int icon) {
            this.icon = icon;
            if (icon == 0)
                throw new AssertionError("SnToast - Icon cannot be null !!!");
            else
                return this;
        }

        public void build() {
            if (context == null)
                throw new AssertionError("Context assignment is required.");
            if (message == null)
                throw new AssertionError("Message assignment is required.");
            if (backgroundColor == 0)
                throw new AssertionError("BackgroundColor assignment is required.");
            if (textColor == 0)
                throw new AssertionError("TextColor assignment is required.");
            if (icon == 0)
                throw new AssertionError("Icon assignment is required.");

            SnToast snToast = new SnToast();
            snToast.init(context, message, null, animation, duration, backgroundColor, textColor, icon);
        }
    }
}
