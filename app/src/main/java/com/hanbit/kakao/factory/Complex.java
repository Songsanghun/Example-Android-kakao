package com.hanbit.kakao.factory;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Map;

/**
 * Created by hb2002 on 2017-03-16.
 */

public class Complex {
    /* LayoutParams */
    public static class LayoutParamsFactory {
        public static LinearLayout.LayoutParams create(String flag) {
            LinearLayout.LayoutParams rs = null;
            switch(flag) {
                case "mm":
                    rs = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                    );
                    break;
                case "mw":
                    rs = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
                    );
                    break;
                case "ww":
                    rs = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT
                    );
                    break;
                case "wm":
                    rs = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
                    );
                    break;
            }
            return rs;
        }
        public static LinearLayout.LayoutParams create(String flag, int weight) {
            LinearLayout.LayoutParams rs = null;
            switch(flag) {
                case "mm":
                    rs = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, weight
                    );
                    break;
                case "mw":
                    rs = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, weight
                    );
                    break;
                case "ww":
                    rs = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, weight
                    );
                    break;
                case "wm":
                    rs = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, weight
                    );
            }
            return rs;
        }
    }

    /* Button */
    public static class ButtonFactory {
        public static Button create(Context context, LinearLayout.LayoutParams layoutParams, String text) {
            Button btn = new Button(context);
            btn.setLayoutParams(layoutParams);
            btn.setText(text);
            return btn;
        }

        public static Button create(Context context, LinearLayout.LayoutParams layoutParams, String text, int textSize) {
            Button btn = new Button(context);
            btn.setLayoutParams(layoutParams);
            btn.setText(text);
            btn.setTextSize(textSize);
            return btn;
        }

        public static Button create(Context context, LinearLayout.LayoutParams layoutParams, String text, String bgColor) {
            Button btn = new Button(context);
            btn.setLayoutParams(layoutParams);
            btn.setText(text);
            btn.setBackgroundColor(Color.parseColor(bgColor));
            return btn;
        }

        public static Button create(Context context, LinearLayout.LayoutParams layoutParams, String text, String bgColor, int[] margin) {
            Button btn = new Button(context);
            ViewGroup.MarginLayoutParams btnMargin = new ViewGroup.MarginLayoutParams(layoutParams);
            btn.setText(text);
            btn.setBackgroundColor(Color.parseColor(bgColor));
            btnMargin.setMargins(margin[0], margin[1], margin[2], margin[3]);
            btn.setLayoutParams(btnMargin);
            return btn;
        }
    }

    /* EditText*/
    public static class EditTextFactory {
        public static EditText create(Context context, LinearLayout.LayoutParams layoutParams, String hint, int textSize) {
            EditText et = new EditText(context);
            et.setLayoutParams(layoutParams);
            et.setHint(hint);
            et.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
            return et;
        }
    }

    /* LinearLayout */
    public static class LinearLayoutFactory {
        public static LinearLayout create(Context context, Map<String, String> map) {
            LinearLayout ll = new LinearLayout(context);
            if(map.get("type")!=null)
                switch(map.get("type")) {
                    case "vertical" :
                        ll.setOrientation(LinearLayout.VERTICAL);
                        ll.setLayoutParams(Complex.LayoutParamsFactory.create(map.get("layoutParams")));
                        break;
                }
            if(map.get("margin")!=null) {
                ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(Complex.LayoutParamsFactory.create(map.get("layoutParams")));
                int[] marginStr = new int[4];
                marginStr[0] = Integer.parseInt(map.get("margin").split(",")[0]);
                marginStr[1] = Integer.parseInt(map.get("margin").split(",")[1]);
                marginStr[2] = Integer.parseInt(map.get("margin").split(",")[2]);
                marginStr[3] = Integer.parseInt(map.get("margin").split(",")[3]);
                margin.setMargins(marginStr[0],marginStr[1],marginStr[2],marginStr[3]);
                ll.setLayoutParams(margin);
            }

            return ll;
        }

        public static LinearLayout create(Context context, LinearLayout.LayoutParams layoutParams) {
            LinearLayout ll = new LinearLayout(context);
            ll.setLayoutParams(layoutParams);
            return ll;
        }

        public static LinearLayout create(Context context, LinearLayout.LayoutParams layoutParams, String orientation) {
            LinearLayout ll = new LinearLayout(context);
            ll.setLayoutParams(layoutParams);
            switch (orientation) {
                case "v":case "vertical":
                    ll.setOrientation(LinearLayout.VERTICAL);
                    break;
                case "h":case "horizontal":
                    ll.setOrientation(LinearLayout.HORIZONTAL);
                    break;
            }
            return ll;
        }

        public static LinearLayout create(Context context, LinearLayout.LayoutParams layoutParams, String orientation, int[] margin) {
            LinearLayout ll = new LinearLayout(context);
            ViewGroup.MarginLayoutParams llMargin = new ViewGroup.MarginLayoutParams(layoutParams);
            llMargin.setMargins(margin[0], margin[1], margin[2], margin[3]);
            ll.setLayoutParams(llMargin);
            switch (orientation) {
                case "v":case "vertical":
                    ll.setOrientation(LinearLayout.VERTICAL);
                    break;
                case "h":case "horizontal":
                    ll.setOrientation(LinearLayout.HORIZONTAL);
                    break;
            }
            return ll;
        }
    }

    /* TextView */
    public static class TextViewFactory {
        public static TextView create(Context context, LinearLayout.LayoutParams layoutParams, String text, int textSize) {
            TextView tv = new TextView(context);
            tv.setLayoutParams(layoutParams);
            tv.setText(text);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
            return tv;
        }

        public static TextView create(Context context, LinearLayout.LayoutParams layoutParams, String text, int textSize, String gravity) {
            TextView tv = new TextView(context);
            tv.setLayoutParams(layoutParams);
            tv.setText(text);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
            switch (gravity) {
                case "left":
                    tv.setGravity(0);
                    break;
                case "center":
                    tv.setGravity(1);
                    break;
                case "right":
                    tv.setGravity(2);
                    break;
            }
            return tv;
        }

        public static TextView create(Context context, LinearLayout.LayoutParams layoutParams, String text, String typeFace, int textSize) {
            TextView tv = new TextView(context);
            tv.setLayoutParams(layoutParams);
            tv.setText(text);
            switch (typeFace) {
                case "B":case "BOLD":
                    tv.setTypeface(null, Typeface.BOLD);
                    break;
            }
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
            return tv;
        }
    }
}