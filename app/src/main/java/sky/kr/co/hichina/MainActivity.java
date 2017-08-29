package sky.kr.co.hichina;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

import co.kr.sky.AccumThread;
import sky.kr.co.hichina.common.ActivityEx;

public class MainActivity extends ActivityEx {

    private Button top_right_btn , main_btn1 , main_btn2 , main_btn3 , main_btn4;
    private AccumThread mThread;
    private Map<String, String> map = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        top_right_btn = (Button)findViewById(R.id.top_right_btn);
        main_btn1 = (Button)findViewById(R.id.main_btn1);
        main_btn2 = (Button)findViewById(R.id.main_btn2);
        main_btn3 = (Button)findViewById(R.id.main_btn3);
        main_btn4 = (Button)findViewById(R.id.main_btn4);
//
        findViewById(R.id.top_right_btn).setOnClickListener(btnListener);
        findViewById(R.id.main_btn1).setOnClickListener(btnListener);
        findViewById(R.id.main_btn2).setOnClickListener(btnListener);
        findViewById(R.id.main_btn3).setOnClickListener(btnListener);
        findViewById(R.id.main_btn4).setOnClickListener(btnListener);

    }
    View.OnClickListener btnListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.top_right_btn:
                    Log.e("SKY"  , "--top_right_btn--");
                    break;
                case R.id.main_btn1:        //중국생활
                    Log.e("SKY"  , "--main_btn1--");
                    break;
                case R.id.main_btn2:        //사고팔기
                    Log.e("SKY"  , "--main_btn2--");
                    break;
                case R.id.main_btn3:        //개인교습
                    Log.e("SKY"  , "--main_btn3--");
                    break;
                case R.id.main_btn4:        //학교생활정보
                    Log.e("SKY"  , "--main_btn4--");
                    Intent intent = new Intent(MainActivity.this, SchoolInfoActivity.class);
                    startActivity(intent);
                    break;

            }
        }
    };
}
