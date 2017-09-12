package sky.kr.co.hichina;

import android.os.Bundle;
import android.widget.EditText;

import co.kr.sky.AccumThread;
import sky.kr.co.hichina.common.ActivityEx;

public class PersonStudyDetailActivity extends ActivityEx {

    private EditText id_edit , email_edit;
    private AccumThread mThread;
    @Override
    public void onResume() {

        super.onResume();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personstudydetail);

    }
}
