package com.emreesen.sntoast;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
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
 * @version 1.0.4
 * @since 2021-05-18 update: 2021-11-15
 */

public class SnToast {
    // Main layout of toast message
    private LinearLayout toastLayout;

    // Unless customized, it is determined by type value.
    private ImageView toastIcon;

    // It must be set manually for both custom and standard.
    private TextView toastMessage;

    /**
     * @param context         It should be strictly set for both custom and standard.
     * @param message         It should be strictly set for both custom and standard.
     * @param type            Type value is set only for standard. For custom, this value is set to null.
     * @param typeface        Optional in both cases. Default: Sans-serif-condensed"
     * @param animation       Optional in both cases. Default: True
     * @param duration        Optional in both cases. Default: 3000ms
     * @param textSize        Optional in both cases. Default: 18sp
     * @param iconSize        optional in both cases. Default: 34dp
     * @param backgroundColor This value is set for custom only. For standard, this value is set to null.
     * @param textColor       This value is set for custom only. For standard, this value is set to null.
     * @param icon            This value is set for custom only. For standard, this value is set to null.
     */
    private void init(
            @NonNull Context context,
            @NonNull String message,
            @Nullable Type type,
            @Nullable Typeface typeface,
            boolean animation,
            boolean cancelable,
            int duration,
            int textSize,
            int iconSize,
            int backgroundColor,
            int textColor,
            int icon
    ) {

        // Creates dialog and set its settings
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(cancelable);
        dialog.setContentView(R.layout.toast_layout);
        toastLayout = dialog.findViewById(R.id.toast_layout);
        toastIcon = dialog.findViewById(R.id.toast_icon);

        // Set icon size
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) toastIcon.getLayoutParams();
        params.width = dpToPx(iconSize, context);
        params.height = dpToPx(iconSize, context);
        toastIcon.setLayoutParams(params);

        // Set message
        toastMessage = dialog.findViewById(R.id.toast_msg);
        toastMessage.setText(message);

        // Set Text size
        toastMessage.setTextSize(textSize);

        //Set Typeface
        toastMessage.setTypeface(typeface);

        // Set window configurations
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        window.setAttributes(wlp);

        // Set toast design
        if (type == null)
            setCustomDesign(backgroundColor, textColor, icon, context);
        else
            setDesign(type, context);

        // Set animation
        if (animation)
            startAnimation();

        dialog.show();

        //Set duration
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (dialog.isShowing())
                dialog.dismiss();
        }, duration);

    }

    /**
     * Sets the design of the standard toast message.
     */
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

    /**
     * Sets the design of the custom toast message.
     */
    private void setCustomDesign(int backgroundColor, int textColor, int icon, @NonNull Context context) {
        toastMessage.setTextColor(context.getColor(textColor));
        toastLayout.setBackgroundColor(context.getColor(backgroundColor));
        toastIcon.setImageResource(icon);
    }

    /**
     * Starts animation for toast icon.
     */
    private void startAnimation() {
        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                toastIcon,
                PropertyValuesHolder.ofFloat("scaleX", 1.1f),
                PropertyValuesHolder.ofFloat("scaleY", 1.1f));
        scaleDown.setDuration(500);
        scaleDown.setRepeatCount(ObjectAnimator.INFINITE);
        scaleDown.setRepeatMode(ObjectAnimator.REVERSE);
        scaleDown.start();
    }

    /**
     * For icon sizing. (dp to px)
     */
    private int dpToPx(int dp, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    public static class Standard {
        private Context context;
        private String message;
        private Type type;
        private Typeface typeface = Typeface.create("sans-serif-condensed", Typeface.NORMAL);
        private boolean animation = true;
        private boolean cancelable = false;
        private int duration = 3000;
        private int textSize = 18;
        private int iconSize = 34;

        public Standard() {
        }

        // Required
        public Standard context(Context context) {
            this.context = context;
            if (context == null)
                throw new AssertionError("SnToast - Context cannot be null !!!");
            else
                return this;
        }

        // Required
        public Standard message(String message) {
            this.message = message;
            if (message == null)
                throw new AssertionError("SnToast - Message cannot be null !!!");
            else
                return this;
        }

        // Required
        public Standard type(Type type) {
            this.type = type;
            if (type == null)
                throw new AssertionError("SnToast - Type cannot be null !!!");
            else
                return this;
        }

        // Not Required Default: "Sans-serif-condensed"
        @SuppressWarnings("unused")
        public Standard typeface(Typeface typeface) {
            this.typeface = typeface;
            return this;
        }

        // Not Required Default: True
        @SuppressWarnings("unused")
        public Standard animation(boolean animation) {
            this.animation = animation;
            return this;
        }

        // Not Required Default: False
        @SuppressWarnings("unused")
        public Standard cancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        // Not Required Default: 3000 (ms)
        @SuppressWarnings("unused")
        public Standard duration(int duration) {
            this.duration = duration;
            return this;
        }

        // Not Required
        @SuppressWarnings("unused")
        public Standard textSize(int textSize) {
            this.textSize = textSize;
            return this;
        }

        // Not Required
        @SuppressWarnings("unused")
        public Standard iconSize(int iconSize) {
            this.iconSize = iconSize;
            return this;
        }

        /**
         * It should be called after the settings are set.
         */
        // Required
        public void build() {
            if (context == null)
                throw new AssertionError("Context assignment is required.");
            if (message == null)
                throw new AssertionError("Message assignment is required.");
            if (type == null)
                throw new AssertionError("Type assignment is required.");

            SnToast snToast = new SnToast();
            snToast.init(context, message, type, typeface, animation, cancelable,
                    duration, textSize, iconSize, 0, 0, 0);
        }
    }

    public static class Custom {
        private Context context;
        private String message;
        private int backgroundColor = 0;
        private int textColor = 0;
        private int icon = 0;
        private Typeface typeface = Typeface.create("sans-serif-condensed", Typeface.NORMAL);
        private boolean animation = true;
        private boolean cancelable = false;
        private int duration = 3000;
        private int textSize = 18;
        private int iconSize = 34;


        public Custom() {
        }

        // Required
        public Custom context(Context context) {
            this.context = context;
            if (context == null)
                throw new AssertionError("SnToast - Context cannot be null !!!");
            return this;
        }

        // Required
        public Custom message(String message) {
            this.message = message;
            if (message == null)
                throw new AssertionError("SnToast - Message cannot be null !!!");
            else
                return this;
        }

        // Required
        public Custom backgroundColor(int backgroundColor) {
            this.backgroundColor = backgroundColor;
            if (backgroundColor == 0)
                throw new AssertionError("SnToast - BackgroundColor cannot be null !!!");
            else
                return this;
        }

        // Required
        public Custom textColor(int textColor) {
            this.textColor = textColor;
            if (textColor == 0)
                throw new AssertionError("SnToast - TextColor cannot be null !!!");
            else
                return this;
        }

        // Required
        public Custom icon(int icon) {
            this.icon = icon;
            if (icon == 0)
                throw new AssertionError("SnToast - Icon cannot be null !!!");
            else
                return this;
        }

        // Not Required Default: "Sans-serif-condensed"
        @SuppressWarnings("unused")
        public Custom typeface(Typeface typeface) {
            this.typeface = typeface;
            return this;
        }

        // Not Required Default: True
        @SuppressWarnings("unused")
        public Custom animation(boolean animation) {
            this.animation = animation;
            return this;
        }

        // Not Required Default: False
        @SuppressWarnings("unused")
        public Custom cancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        // Not Required Default: 3000 (ms)
        @SuppressWarnings("unused")
        public Custom duration(int duration) {
            this.duration = duration;
            return this;
        }

        // Not Required Default: 18 sp
        @SuppressWarnings("unused")
        public Custom textSize(int textSize) {
            this.textSize = textSize;
            return this;
        }

        // Not Required Default: 34 dp
        @SuppressWarnings("unused")
        public Custom iconSize(int iconSize) {
            this.iconSize = iconSize;
            return this;
        }

        /**
         * It should be called after the settings are set.
         */
        // Required
        public void build() {
            if (context == null)
                throw new AssertionError("SnToast - Context assignment is required.");
            if (message == null)
                throw new AssertionError("SnToast - Message assignment is required.");
            if (backgroundColor == 0)
                throw new AssertionError("SnToast - BackgroundColor assignment is required.");
            if (textColor == 0)
                throw new AssertionError("SnToast - TextColor assignment is required.");
            if (icon == 0)
                throw new AssertionError("SnToast - Icon assignment is required.");

            SnToast snToast = new SnToast();
            snToast.init(context, message, null, typeface, animation, cancelable,
                    duration, textSize, iconSize, backgroundColor, textColor, icon);
        }
    }
}
