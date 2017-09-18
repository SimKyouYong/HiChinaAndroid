package sky.kr.co.hichina;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import co.kr.sky.AccumThread;
import sky.kr.co.hichina.common.ActivityEx;
import sky.kr.co.hichina.common.Check_Preferences;
import sky.kr.co.hichina.common.DEFINE;

public class MyPageActivity extends ActivityEx {
    private AccumThread mThread;
    private Map<String, String> map = new HashMap<String, String>();

    private TextView txt1_right , txt2_right , txt3_right;
    private TextView my_id , my_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        txt1_right = (TextView)findViewById(R.id.txt1_right);
        txt2_right = (TextView)findViewById(R.id.txt2_right);
        txt3_right = (TextView)findViewById(R.id.txt3_right);
        my_id = (TextView)findViewById(R.id.my_id);
        my_email = (TextView)findViewById(R.id.my_email);

        my_id.setText("" +Check_Preferences.getAppPreferences(this , "MEMBER_ID"));
        my_email.setText("" +Check_Preferences.getAppPreferences(this , "MEMBER_EMAIL"));

        customProgressPop();
        map.put("url", DEFINE.SERVER_URL + "MYPAGE_SELECT.php");
        map.put("MEMBER_KEY_INDEX", Check_Preferences.getAppPreferences(this , "KEY_INDEX"));
        //스레드 생성
        mThread = new AccumThread(MyPageActivity.this , mAfterAccum , map , 0 , 0 , null);
        mThread.start();		//스레드 시작!!


        findViewById(R.id.btn1_btn).setOnClickListener(btnListener);
        findViewById(R.id.btn2_btn).setOnClickListener(btnListener);
        findViewById(R.id.btn3_btn).setOnClickListener(btnListener);

    }
    View.OnClickListener btnListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.btn1_btn:
                    Log.e("SKY"  , "--btn1_btn--");
                    break;
                case R.id.btn2_btn:
                    Log.e("SKY"  , "--btn2_btn--");
                    break;
                case R.id.btn3_btn:
                    Log.e("SKY"  , "--btn3_btn--");
                    break;

            }
        }
    };
    Handler mAfterAccum = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            customProgressClose();
            if (msg.arg1  == 0 ) {
                String res = (String)msg.obj;
                Log.e("CHECK" , "RESULT  -> " + res);
                String val[] = res.split(",");

                txt1_right.setText("[" + val[0] + "]");
                txt2_right.setText("[" + val[1] + "]");
                txt3_right.setText("[" + val[2] + "]");

            }
        }
    };
}
