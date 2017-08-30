package sky.kr.co.hichina;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;

import co.kr.sky.AccumThread;
import sky.kr.co.hichina.adapter.SchoolInfo_Adapter;
import sky.kr.co.hichina.common.ActivityEx;

public class SchoolInfoActivity extends ActivityEx {

    private EditText id_edit , email_edit;
    private AccumThread mThread;
    private Map<String, String> map = new HashMap<String, String>();
    private ListView list_number;
    private SchoolInfo_Adapter m_Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schoolinfo);
        list_number = (ListView)findViewById(R.id.list_number);

//
//        id_edit = (EditText)findViewById(R.id.id_edit);
//        email_edit = (EditText)findViewById(R.id.email_edit);
//
//        id_edit.setText("snap40");
//        email_edit.setText("snap0425@gmail.com");

        findViewById(R.id.top_left_btn).setOnClickListener(btnListener);
        findViewById(R.id.top_right_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab1_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab2_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab3_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab4_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab5_btn).setOnClickListener(btnListener);

//        m_Adapter = new SchoolInfo_Adapter( SchoolInfoActivity.this , arr , mAfterAccum);
//        list_number.setAdapter(m_Adapter);
    }
    View.OnClickListener btnListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.top_left_btn:
                    Log.e("SKY"  , "--top_left_btn--");
                    break;
                case R.id.top_right_btn:
                    Log.e("SKY"  , "--top_right_btn--");
                    Intent intent = new Intent(SchoolInfoActivity.this, WriteActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tab1_btn:
                    Log.e("SKY"  , "--tab1_btn--");
                    break;
                case R.id.tab2_btn:
                    Log.e("SKY"  , "--tab2_btn--");
                    break;
                case R.id.tab3_btn:
                    Log.e("SKY"  , "--tab3_btn--");
                    break;
                case R.id.tab4_btn:
                    Log.e("SKY"  , "--tab4_btn--");
                    break;
                case R.id.tab5_btn:
                    Log.e("SKY"  , "--tab5_btn--");
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
