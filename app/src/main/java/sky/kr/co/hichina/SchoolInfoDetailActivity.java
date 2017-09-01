package sky.kr.co.hichina;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import co.kr.sky.AccumThread;
import sky.kr.co.hichina.adapter.SchoolInfo_Adapter;
import sky.kr.co.hichina.common.ActivityEx;
import sky.kr.co.hichina.obj.SchoolInfoObj;

public class SchoolInfoDetailActivity extends ActivityEx {

    private EditText id_edit , email_edit;
    private AccumThread mThread;
    private Map<String, String> map = new HashMap<String, String>();
    private ListView list_number;
    private SchoolInfo_Adapter m_Adapter;
    private ArrayList<SchoolInfoObj> arr;
    private String [][] Object_Array;
    private int tab_position = 0;
    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schoolinfodetail);

    }
}
