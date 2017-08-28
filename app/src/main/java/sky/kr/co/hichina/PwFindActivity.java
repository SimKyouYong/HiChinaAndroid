package sky.kr.co.hichina;

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

public class PwFindActivity extends ActivityEx {

    private EditText id_edit , email_edit;
    private AccumThread mThread;
    private Map<String, String> map = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwfind);


        id_edit = (EditText)findViewById(R.id.id_edit);
        email_edit = (EditText)findViewById(R.id.email_edit);

        findViewById(R.id.find_btn).setOnClickListener(btnListener);

    }
    View.OnClickListener btnListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.find_btn:
                    Log.e("SKY"  , "--find_btn--");
                    if(id_edit.getText().toString().equals("") || email_edit.getText().toString().equals("")){
                        Toast.makeText(PwFindActivity.this, "모든 항목을 입력해주세요", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    map.put("url", DEFINE.SERVER_URL + "MEMBER_FIND");
                    map.put("MEMBER_ID",    id_edit.getText().toString());
                    map.put("MEMBER_EMAIL", email_edit.getText().toString());

                    //스레드 생성
                    mThread = new AccumThread(PwFindActivity.this , mAfterAccum , map , 0 , 0 , null);
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
                if (res.trim().equals("true")){
                    Toast.makeText(PwFindActivity.this, "이메일로 비밀번호를 전송하였습니다", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(PwFindActivity.this, "아이디 혹은 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                }

            }
        }
    };
}
