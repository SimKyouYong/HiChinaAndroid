package sky.kr.co.hichina;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import co.kr.sky.AccumThread;
import sky.kr.co.hichina.common.ActivityEx;
import sky.kr.co.hichina.common.DEFINE;

public class JoinActivity extends ActivityEx {

    private EditText id_edit , pw_edit , email_edit;
    private AccumThread mThread;
    private Map<String, String> map = new HashMap<String, String>();

    private Button job_btn;
    private String JOB = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);


        job_btn = (Button)findViewById(R.id.job_btn);
        id_edit = (EditText)findViewById(R.id.id_edit);
        pw_edit = (EditText)findViewById(R.id.pw_edit);
        email_edit = (EditText)findViewById(R.id.email_edit);


        id_edit.setText("snap40");
        pw_edit.setText("rbdyd3174");
        email_edit.setText("snap0425@gmail.com");

        findViewById(R.id.job_btn).setOnClickListener(btnListener);
        findViewById(R.id.join_btn).setOnClickListener(btnListener);

    }
    View.OnClickListener btnListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.job_btn:
                    Log.e("SKY"  , "--job_btn--");
                    final CharSequence[] items = {"학생(어학연수)", "학생(본과생)", "학생(석사/박사)" , "학부모" , "직장인" , "사업자" , "공무원"};

                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                    builder.setTitle("집업을 선택하세요")
                            .setItems(items, new DialogInterface.OnClickListener(){    // 목록 클릭시 설정
                                public void onClick(DialogInterface dialog, int index){
                                    JOB = (String) items[index];
                                    job_btn.setText(""+JOB);
                                }
                            });

                    AlertDialog dialog = builder.create();    // 알림창 객체 생성
                    dialog.show();    // 알림창 띄우기
                    break;
                case R.id.join_btn:
                    Log.e("SKY"  , "--join_btn--");
                    if(JOB.equals("")){
                        Toast.makeText(JoinActivity.this, "직업을 선택해주세요", Toast.LENGTH_SHORT).show();
                        return;
                    }else if(id_edit.getText().toString().equals("") || pw_edit.getText().toString().equals("")){
                        Toast.makeText(JoinActivity.this, "모든 항목을 입력해주세요", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    map.put("url", DEFINE.SERVER_URL + "MEMBER_JOIN.php");
                    map.put("MEMBER_ID",    id_edit.getText().toString());
                    map.put("MEMBER_PW",    pw_edit.getText().toString());
                    map.put("MEMBER_EMAIL", email_edit.getText().toString());
                    map.put("MEMBER_JOB",   JOB);
                    //스레드 생성
                    mThread = new AccumThread(JoinActivity.this , mAfterAccum , map , 0 , 0 , null);
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

                Toast.makeText(getApplicationContext() , "가입완료 되었습니다" , Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    };
}
