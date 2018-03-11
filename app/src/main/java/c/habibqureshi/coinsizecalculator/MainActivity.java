package c.habibqureshi.coinsizecalculator;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import javax.security.auth.login.LoginException;


public class MainActivity extends AppCompatActivity {
    LinearLayout uper,lower;
    ImageView inc,dec;
    TextView inch;
    ImageView circle , sideCircle;
    int incVal=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics dm = getResources().getDisplayMetrics();

        double density = dm.density * 160;
        double x = Math.pow(dm.widthPixels / density, 2);
        double y = Math.pow(dm.heightPixels / density, 2);
        double screenInches = Math.sqrt(x + y);
        Log.e("inches: {}", screenInches+"");
        findViews();
        init();
        check();
        setdpToinch(100);
    }
    private void init(){
        inc.setOnTouchListener(touchListener);
        dec.setOnTouchListener(touchListener);
        inc.setOnClickListener(clickListener);
        dec.setOnClickListener(clickListener);



    }
    private void findViews(){
        sideCircle=findViewById(R.id.sideCircle);
        circle=findViewById(R.id.circle);
        uper=findViewById(R.id.uper);
        lower=findViewById(R.id.below);
        inc=findViewById(R.id.inc);
        dec=findViewById(R.id.dec);
        inch=findViewById(R.id.inch);
    }

    View.OnTouchListener touchListener=new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    ImageView imageView = (ImageView) view;
                    //overlay is black with transparency of 0x77 (119)
                    imageView.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                    imageView.invalidate();
                    break;
                }
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL: {
                    ImageView imageView = (ImageView) view;
                    //clear the overlay
                    imageView.getDrawable().clearColorFilter();
                    imageView.invalidate();
                    break;
                }
            }

            return false;
        }
    };
    View.OnClickListener clickListener =new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int margin=0;
            LinearLayout.LayoutParams params;
            RelativeLayout.LayoutParams circlePrams;
            switch (view.getId()){
                case R.id.inc:
                    circle.setVisibility(View.VISIBLE);
                    sideCircle.setVisibility(View.VISIBLE);
                    margin=Integer.parseInt(uper.getContentDescription().toString());
                    Log.e("margingot",margin+"");
                    margin+=incVal;
                    params=(LinearLayout.LayoutParams)uper.getLayoutParams();
                    params.setMargins(0,0,0,margin);
                    uper.requestLayout();
                    uper.setContentDescription(margin+"");
                    params= (LinearLayout.LayoutParams) lower.getLayoutParams();
                    params.setMargins(0,margin,0,0);
                    lower.requestLayout();
                    circle.getLayoutParams().height=circle.getLayoutParams().height+incVal+incVal;
                    circle.getLayoutParams().width=circle.getLayoutParams().width+incVal+incVal;
                    sideCircle.getLayoutParams().height=sideCircle.getLayoutParams().height+incVal+incVal;
                 //       sideCircle.getLayoutParams().width=sideCircle.getLayoutParams().width+5;
//                    circle.requestLayout();


                    break;
                case R.id.dec:
                    margin=Integer.parseInt(uper.getContentDescription().toString());

                    if(margin>0) {
                        margin-=incVal;
                        circle.setVisibility(View.VISIBLE);
                        sideCircle.setVisibility(View.VISIBLE);
                        params = (LinearLayout.LayoutParams) uper.getLayoutParams();
                        params.setMargins(0, 0, 0, margin);
                        uper.requestLayout();
                        uper.setContentDescription(margin + "");
                        params = (LinearLayout.LayoutParams) lower.getLayoutParams();
                        params.setMargins(0, margin, 0, 0);
                        lower.requestLayout();
                        circle.getLayoutParams().height = circle.getLayoutParams().height - incVal-incVal;
                        circle.getLayoutParams().width = circle.getLayoutParams().height - incVal-incVal;
                        sideCircle.getLayoutParams().height = sideCircle.getLayoutParams().height - incVal-incVal;
                     //   sideCircle.getLayoutParams().width = sideCircle.getLayoutParams().width - 5;
                        circle.requestLayout();
                    }
                    else
                    {
                        params = (LinearLayout.LayoutParams) uper.getLayoutParams();
                        params.setMargins(0, 0, 0, 0);
                        uper.requestLayout();
                        uper.setContentDescription(0 + "");
                        params = (LinearLayout.LayoutParams) lower.getLayoutParams();
                        params.setMargins(0, 0, 0, 0);
                        lower.requestLayout();
                        circle.setVisibility(View.GONE);
                        sideCircle.setVisibility(View.GONE);
                    }
                    break;
            }
            Log.e("Margin",margin+"");
            setdpToinch(margin*2);


        }
    };
    void setdpToinch(float px){

//        dp=dp*2;
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
    //    float density = metrics.density;
//        float px = dp * (metrics.densityDpi/160f);
        float result= px/metrics.ydpi;
        Log.e("result","inc "+result);
        result=(result) * 79.756f;
        Log.e("result","mm "+result);

        inch.setText((String.format("%.2f",result)));

    }
    void check(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Log.e("info ",metrics.heightPixels+"");
        Log.e("info ",metrics.widthPixels+"");
        Log.e("info ",metrics.xdpi+"");
        Log.e("info ",metrics.ydpi+"");

    }
}
