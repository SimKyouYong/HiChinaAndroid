package sky.kr.co.hichina;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

import co.kr.sky.AccumThread;
import sky.kr.co.hichina.common.ActivityEx;

public class MainActivity extends ActivityEx {

    private EditText id_edit , pw_edit;
    private AccumThread mThread;
    private Map<String, String> map = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        id_edit = (EditText)findViewById(R.id.id_edit);
        pw_edit = (EditText)findViewById(R.id.pw_edit);

        findViewById(R.id.pw_find_btn).setOnClickListener(btnListener);
        findViewById(R.id.login_btn).setOnClickListener(btnListener);
        findViewById(R.id.join_btn).setOnClickListener(btnListener);

    }
    View.OnClickListener btnListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.join_btn:
                    Log.e("SKY"  , "--join_btn--");
                    startActivity(new Intent(MainActivity.this, JoinActivity.class));
                    break;
                case R.id.pw_find_btn:
                    Log.e("SKY"  , "--pw_find_btn--");
                    startActivity(new Intent(MainActivity.this, PwFindActivity.class));

                    break;
                case R.id.login_btn:
                    Log.e("SKY"  , "--login_btn--");
                    //map.put("url", "http://sc-group.kr/collraboration/login/login.ajax");
//                    map.put("_JSON_DATA",json);
//                    //스레드 생성
//                    mThread = new AccumThread(LoginActivity.this , mAfterAccum , map , 0 , 0 , null);
//                    mThread.start();		//스레드 시작!!
                    break;
            }
        }
    };
    Handler mAfterAccum = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            if (msg.arg1  == 0 ) {
                String res = (String)msg.obj;
                Log.e("CHECK" , "RESULT  -> " + res);

            }
        }
    };
}
