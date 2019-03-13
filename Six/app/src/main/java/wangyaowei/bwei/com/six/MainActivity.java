package wangyaowei.bwei.com.six;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import wangyaowei.bwei.com.six.fragment.YeMian_01;
import wangyaowei.bwei.com.six.fragment.YeMian_02;
import wangyaowei.bwei.com.six.fragment.YeMian_03;

public class MainActivity extends AppCompatActivity {
    private FragmentManager manager;
    private Handler handler=new Handler();

    private RadioGroup rg;
    private boolean netWorks;

    private YeMian_01 yeMain_01;
    private YeMian_02 yeMain_02;
    private YeMian_03 yeMain_03;
    private DrawerLayout dl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dl = findViewById(R.id.dl);

        dl.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {

            }

            @Override
            public void onDrawerClosed(@NonNull View view) {

            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });

        rg = findViewById(R.id.rg);
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        yeMain_01 = new YeMian_01();
        yeMain_02 = new YeMian_02();
        yeMain_03 = new YeMian_03();


        transaction.add(R.id.frame,yeMain_01 );
        transaction.add(R.id.frame,yeMain_02 );
        transaction.add(R.id.frame,yeMain_03 );

        transaction.show(yeMain_01).hide(yeMain_02).hide(yeMain_03);
        transaction.commit();

        rg.check(rg.getChildAt(0).getId());
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction1 = manager.beginTransaction();
                switch (checkedId){
                    case R.id.rb1:
                        transaction1.show(yeMain_01).hide(yeMain_02).hide(yeMain_03);
                        break;
                    case R.id.rb2:
                        transaction1.show(yeMain_02).hide(yeMain_01).hide(yeMain_03);
                        break;
                    case R.id.rb3:
                        transaction1.show(yeMain_03).hide(yeMain_01).hide(yeMain_02);
                        break;

                }
                transaction1.commit();
            }
        });
    }
}
