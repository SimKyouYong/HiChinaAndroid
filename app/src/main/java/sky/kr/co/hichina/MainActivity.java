package sky.kr.co.hichina;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

    private Button top_right_btn , main_btn1 , main_btn2 , main_btn3 , main_btn4 , location;
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
        location = (Button)findViewById(R.id.location);
//
        findViewById(R.id.top_right_btn).setOnClickListener(btnListener);

        findViewById(R.id.location).setOnClickListener(btnListener);
        findViewById(R.id.main_btn1).setOnClickListener(btnListener);
        findViewById(R.id.main_btn2).setOnClickListener(btnListener);
        findViewById(R.id.main_btn3).setOnClickListener(btnListener);
        findViewById(R.id.main_btn4).setOnClickListener(btnListener);

    }
    View.OnClickListener btnListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.location:
                    Log.e("SKY"  , "--location--");
                    final CharSequence[] items = {"지역","북경", "상", "천진", "선", "서안", "광저우"};

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("선택하세요")
                            .setItems(items, new DialogInterface.OnClickListener(){    // 목록 클릭시 설정
                                public void onClick(DialogInterface dialog, int index){
                                    location.setText((String) items[index]);
                                }
                            });

                    AlertDialog dialog = builder.create();    // 알림창 객체 생성
                    dialog.show();    // 알림창 띄우기
                    break;
                case R.id.top_right_btn:
                    Log.e("SKY"  , "--top_right_btn--");
                    Intent intent5 = new Intent(MainActivity.this, MyPageActivity.class);
                    startActivity(intent5);
                    break;
                case R.id.main_btn1:        //중국생활
                    Log.e("SKY"  , "--main_btn1--");
                    Intent intent3 = new Intent(MainActivity.this, ChinaLifeActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.main_btn2:        //사고팔기
                    Log.e("SKY"  , "--main_btn2--");
                    Intent intent4 = new Intent(MainActivity.this, BuySellActivity.class);
                    startActivity(intent4);
                    break;
                case R.id.main_btn3:        //개인교습(ok)
                    Log.e("SKY"  , "--main_btn3--");
                    Intent intent2 = new Intent(MainActivity.this, PersonStudyActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.main_btn4:        //학교생활정보(ok)
                    Log.e("SKY"  , "--main_btn4--");
                    Intent intent = new Intent(MainActivity.this, SchoolInfoActivity.class);
                    startActivity(intent);
                    break;

            }
        }
    };
}
