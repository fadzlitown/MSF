package my.edu.upm.msfapp.view;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import my.edu.upm.msfapp.R;


/**
 * Created by fadzlirazali on 17/10/2016.
 */

public class CustomTabView extends LinearLayout{

    private ImageView mImage;
    private TextView mText;

    public CustomTabView(Context context) {
        super(context);
        init();
    }

    public CustomTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        /* Same like LayoutInflater.inflate */
        View.inflate(getContext(), R.layout.view_custom, this);
        mImage = ((ImageView) findViewById(R.id.image));
        mText = ((TextView) findViewById(R.id.text));
    }

    public void setText(String msg) {
        mText.setText(msg);
    }

    public void updateTextColor(@ColorRes int color) {
        mText.setTextColor(getResources().getColor(color));
    }

    public void updateIcon(@DrawableRes int res) {
        mImage.setImageResource(res);
    }
}
