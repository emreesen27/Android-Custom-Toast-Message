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

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;

/**
 * @author Aydın Emre ESEN
 * @version 1.0.5
 * @since 2021-05-18 update: 2024-05-01
 */

public class SnToast {
    private LinearLayout toastLayout;

    // Unless customized, it is determined by type value.
    private ImageView toastIcon;

    private TextView toastMessage;

    /**
     * @param context         Required Default: -
     * @param message         Required Default: -
     * @param type            Required Default: -
     * @param typeface        Optional Default: Sans-serif-condensed"
     * @param animation       Optional Default: True
     * @param duration        Optional Default: 3000ms
     * @param textSize        Optional Default: 18sp
     * @param iconSize        Optional Default: 34dp
     * @param backgroundColor Optional Default: It is filled according to the type. If an assignment is made, the assigned value is used
     * @param textColor       Optional Default: It is filled according to the type. If an assignment is made, the assigned value is used
     * @param icon            Optional Default: It is filled according to the type. If an assignment is made, the assigned value is used
     */
    private void init(
            @NonNull Context context,
            @NonNull String message,
            @NonNull Type type,
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

        // Set Typeface
        toastMessage.setTypeface(typeface);

        // Set Text Color
        toastMessage.setTextColor(context.getColor(textColor));

        // Set window configurations
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        window.setAttributes(wlp);

        setDesign(type, context, backgroundColor, icon);

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
     * Sets the design
     */
    private void setDesign(@NonNull Type type, @NonNull Context context, int backgroundColor, int icon) {
        Pair<Integer, Integer> defaultValues = getDefaultDesignValues(type);

        int defaultColor = defaultValues.first;
        int defaultIcon = defaultValues.second;

        toastLayout.setBackgroundColor(context.getColor(backgroundColor == 0 ? defaultColor : backgroundColor));
        toastIcon.setImageResource(icon == 0 ? defaultIcon : icon);
    }

    /**
     * Returns default values by type
     */
    private Pair<Integer, Integer> getDefaultDesignValues(@NonNull Type type) {
        int defaultColor = 0;
        int defaultIcon = 0;

        if (type == Type.INFORMATION) {
            defaultColor = R.color.infoColor;
            defaultIcon = R.drawable.ic_information;
        } else if (type == Type.ERROR) {
            defaultColor = R.color.errorColor;
            defaultIcon = R.drawable.ic_error;
        } else if (type == Type.SUCCESS) {
            defaultColor = R.color.successColor;
            defaultIcon = R.drawable.ic_success;
        } else if (type == Type.WARNING) {
            defaultColor = R.color.warningColor;
            defaultIcon = R.drawable.ic_warning;
        }
        return new Pair<>(defaultColor, defaultIcon);
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

    public static class Builder {
        private Context context;
        private String message;
        private Type type;
        private Typeface typeface = Typeface.create("sans-serif-condensed", Typeface.NORMAL);
        private boolean animation = true;
        private boolean cancelable = false;
        private int duration = 3000;
        private int textSize = 18;
        private int iconSize = 34;
        private @ColorRes int backgroundColor = 0;
        private @ColorRes int textColor = R.color.white;
        private @DrawableRes int icon = 0;

        public Builder() {
        }

        // Required
        public Builder context(Context context) {
            this.context = context;
            if (context == null)
                throw new AssertionError("SnToast - Context cannot be null !!!");
            else
                return this;
        }

        // Required
        public Builder message(String message) {
            this.message = message;
            if (message == null)
                throw new AssertionError("SnToast - Message cannot be null !!!");
            else
                return this;
        }

        // Required
        public Builder type(Type type) {
            this.type = type;
            if (type == null)
                throw new AssertionError("SnToast - Type cannot be null !!!");
            else
                return this;
        }

        // Not Required Default: "Sans-serif-condensed"
        @SuppressWarnings("unused")
        public Builder typeface(Typeface typeface) {
            this.typeface = typeface;
            return this;
        }

        // Not Required Default: True
        @SuppressWarnings("unused")
        public Builder animation(boolean animation) {
            this.animation = animation;
            return this;
        }

        // Not Required Default: False
        @SuppressWarnings("unused")
        public Builder cancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        // Not Required Default: 3000 (ms)
        @SuppressWarnings("unused")
        public Builder duration(int duration) {
            this.duration = duration;
            return this;
        }

        // Not Required
        @SuppressWarnings("unused")
        public Builder textSize(int textSize) {
            this.textSize = textSize;
            return this;
        }

        // Not Required
        @SuppressWarnings("unused")
        public Builder iconSize(int iconSize) {
            this.iconSize = iconSize;
            return this;
        }

        // Not Required
        @SuppressWarnings("unused")
        public Builder backgroundColor(int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        // Not Required
        @SuppressWarnings("unused")
        public Builder textColor(int textColor) {
            this.textColor = textColor;
            return this;
        }

        // Not Required
        @SuppressWarnings("unused")
        public Builder icon(int icon) {
            this.icon = icon;
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
                    duration, textSize, iconSize, backgroundColor, textColor, icon);
        }
    }
}