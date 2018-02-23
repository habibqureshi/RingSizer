package c.habibqureshi.coinsizecalculator;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
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
    Button inc,dec;
    TextView inch;
    ImageView circle , sideCircle;

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
    View.OnClickListener clickListener =new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int margin=0;
            LinearLayout.LayoutParams params;
            RelativeLayout.LayoutParams circlePrams;
            switch (view.getId()){
                case R.id.inc:
                    margin=Integer.parseInt(uper.getContentDescription().toString());
                    Log.e("margingot",margin+"");
                    margin+=5;
                    params=(LinearLayout.LayoutParams)view.getLayoutParams();
                    params.setMargins(0,0,0,margin);
                    uper.requestLayout();
                    uper.setContentDescription(margin+"");
                    params= (LinearLayout.LayoutParams) lower.getLayoutParams();
                    params.setMargins(0,margin,0,0);
                    lower.requestLayout();
//                    circlePrams=new RelativeLayout.LayoutParams(margin*2,margin*2);
//                    circlePrams.addRule(RelativeLayout.CENTER_IN_PARENT);
//                    circle.setLayoutParams(circlePrams);
                      circle.getLayoutParams().height=circle.getLayoutParams().height+5;
                      circle.getLayoutParams().width=circle.getLayoutParams().width+5;
                        sideCircle.getLayoutParams().height=sideCircle.getLayoutParams().height+5;
                 //       sideCircle.getLayoutParams().width=sideCircle.getLayoutParams().width+5;
//                    circle.requestLayout();


                    break;
                case R.id.dec:
                    margin=Integer.parseInt(uper.getContentDescription().toString());
                    margin-=5;
                    if(margin>0) {
                        params = (LinearLayout.LayoutParams) view.getLayoutParams();
                        params.setMargins(0, 0, 0, margin);
                        uper.requestLayout();
                        uper.setContentDescription(margin + "");
                        params = (LinearLayout.LayoutParams) lower.getLayoutParams();
                        params.setMargins(0, margin, 0, 0);
                        lower.requestLayout();
                        circle.getLayoutParams().height = circle.getLayoutParams().height - 5;
                        circle.getLayoutParams().width = circle.getLayoutParams().height - 5;
                        sideCircle.getLayoutParams().height = sideCircle.getLayoutParams().height - 5;
                     //   sideCircle.getLayoutParams().width = sideCircle.getLayoutParams().width - 5;
                        circle.requestLayout();
                    }
                    break;
            }
            Log.e("Margin",margin+"");
            setdpToinch(margin);


        }
    };
    void setdpToinch(float px){

//        dp=dp*2;
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
    //    float density = metrics.density;
//        float px = dp * (metrics.densityDpi/160f);
        float result= px/metrics.ydpi;
        result=(result * 25.4f) * 3.14f;
        String.format("%.1f",result);
        inch.setText("Ring Size: "+(result));

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
