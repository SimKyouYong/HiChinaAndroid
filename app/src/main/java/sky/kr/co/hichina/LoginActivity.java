package sky.kr.co.hichina;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import co.kr.sky.AccumThread;
import sky.kr.co.hichina.common.ActivityEx;
import sky.kr.co.hichina.common.DEFINE;

public class LoginActivity extends ActivityEx {

    private EditText id_edit , pw_edit;
    private AccumThread mThread;
    private Map<String, String> map = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


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
                    startActivity(new Intent(LoginActivity.this, JoinActivity.class));
                    break;
                case R.id.pw_find_btn:
                    Log.e("SKY"  , "--pw_find_btn--");
                    startActivity(new Intent(LoginActivity.this, PwFindActivity.class));
                    break;
                case R.id.login_btn:
                    Log.e("SKY"  , "--login_btn--");
                    if (id_edit.getText().toString().trim().length() == 0 ){
                        Toast.makeText(getApplicationContext() , "아이디를 입력해주세요." , Toast.LENGTH_SHORT).show();
                        return;
                    }else if(pw_edit.getText().toString().trim().length() == 0 ){
                        Toast.makeText(getApplicationContext() , "비밀번호를 입력해주세요." , Toast.LENGTH_SHORT).show();
                        return;
                    }

                    map.put("url", DEFINE.SERVER_URL + "");
                    map.put("MEMBER_ID",id_edit.getText().toString());
                    map.put("MEMBER_PW",pw_edit.getText().toString());
                    //스레드 생성
                    mThread = new AccumThread(LoginActivity.this , mAfterAccum , map , 0 , 0 , null);
                    mThread.start();		//스레드 시작!!
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
                startActivity(new Intent(LoginActivity.this, MainActivity.class));

            }
        }
    };
}
