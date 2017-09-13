package sky.kr.co.hichina;

import android.app.Activity;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import sky.kr.co.hichina.adapter.ImageAdapter;
import sky.kr.co.hichina.obj.ThumbImageInfo;

public class GalleryActivity extends Activity implements OnScrollListener, GridView.OnItemClickListener
{
	public static boolean mBusy = false;
	ProgressDialog mLoagindDialog;
	GridView mGvImageList;
	ImageAdapter mListAdapter;
	ArrayList<ThumbImageInfo> mThumbImageInfoList = new ArrayList<ThumbImageInfo>();;
    String tag;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_list_view);
        tag = getIntent().getStringExtra("tag");
		mGvImageList = (GridView) findViewById(R.id.gvImageList);
		mGvImageList.setOnScrollListener(this);
		mGvImageList.setOnItemClickListener(this);
		findViewById(R.id.btnSelectOk).setOnClickListener(mClickListener1);

		new DoFindImageList().execute();
	}
	Button.OnClickListener mClickListener1 = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btnSelectOk:
			    if (tag.equals("0")){
                    SchoolInfoWriteActivity.pic_flag = true;
                    SchoolInfoWriteActivity.mThumbImageInfoList_copy.clear();
                    for (int i = 0; i < mThumbImageInfoList.size(); i++) {
                        if (mThumbImageInfoList.get(i).getIndex() != 999) {
                            SchoolInfoWriteActivity.mThumbImageInfoList_copy.add(mThumbImageInfoList.get(i));
                        }
                    }
                    Collections.sort(SchoolInfoWriteActivity.mThumbImageInfoList_copy, new NoAscCompare());
                    finish();
                }else if(tag.equals("1")){
                    PersonStudyWriteActivity.pic_flag = true;
                    PersonStudyWriteActivity.mThumbImageInfoList_copy.clear();
                    for (int i = 0; i < mThumbImageInfoList.size(); i++) {
                        if (mThumbImageInfoList.get(i).getIndex() != 999) {
                            PersonStudyWriteActivity.mThumbImageInfoList_copy.add(mThumbImageInfoList.get(i));
                        }
                    }
                    Collections.sort(PersonStudyWriteActivity.mThumbImageInfoList_copy, new NoAscCompare());
                    finish();
                }else if(tag.equals("2")){
                    ChinaLifeWriteActivity.pic_flag = true;
                    ChinaLifeWriteActivity.mThumbImageInfoList_copy.clear();
                    for (int i = 0; i < mThumbImageInfoList.size(); i++) {
                        if (mThumbImageInfoList.get(i).getIndex() != 999) {
                            ChinaLifeWriteActivity.mThumbImageInfoList_copy.add(mThumbImageInfoList.get(i));
                        }
                    }
                    Collections.sort(ChinaLifeWriteActivity.mThumbImageInfoList_copy, new NoAscCompare());
                    finish();
                }

				break;
			}
		}
	};
	static class NoAscCompare implements Comparator<ThumbImageInfo> {
		/**
		 * 오름차순(ASC)
		 */
		@Override
		public int compare(ThumbImageInfo arg0, ThumbImageInfo arg1) {
			// TODO Auto-generated method stub
			return arg0.getIndex() < arg1.getIndex() ? -1 : arg0.getIndex() > arg1.getIndex() ? 1:0;
		}
 
	}
	private long findThumbList()
	{
		long returnValue = 0;

		// Select 하고자 하는 컬럼
		String[] projection = { MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA };

		// 쿼리 수행
		Cursor imageCursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, MediaStore.Images.Media.DATE_ADDED + " desc ");

		if (imageCursor != null && imageCursor.getCount() > 0)
		{
			// 컬럼 인덱스
			int imageIDCol = imageCursor.getColumnIndex(MediaStore.Images.Media._ID); 
			int imageDataCol = imageCursor.getColumnIndex(MediaStore.Images.Media.DATA);

			// 커서에서 이미지의 ID와 경로명을 가져와서 ThumbImageInfo 모델 클래스를 생성해서
			// 리스트에 더해준다.
			while (imageCursor.moveToNext())
			{
				mThumbImageInfoList.add(new ThumbImageInfo(imageCursor.getString(imageIDCol), imageCursor.getString(imageDataCol)
						, 999));
				returnValue++;
			}
		}
		imageCursor.close();
		return returnValue;
	}
	private void updateUI()
	{
		mListAdapter = new ImageAdapter(this, R.layout.image_cell, mThumbImageInfoList);
		mGvImageList.setAdapter(mListAdapter);
	}

	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
	{}
	public void onScrollStateChanged(AbsListView view, int scrollState)
	{
		switch (scrollState)
		{
		case OnScrollListener.SCROLL_STATE_IDLE:
			mBusy = false;
			mListAdapter.notifyDataSetChanged();
			break;
		case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
			mBusy = true;
			break;
		case OnScrollListener.SCROLL_STATE_FLING:
			mBusy = true;
			break;
		}
	}
	private int getSetpositon(){
		int Index = 1;
		for (int i = 0; i < mThumbImageInfoList.size(); i++) {
			if (mThumbImageInfoList.get(i).getIndex() != 999) {
				Index++;
			}
		}
		return Index;
	}
	private boolean sizeget(){
		int Index = 0;
		for (int i = 0; i < mThumbImageInfoList.size(); i++) {
			if (mThumbImageInfoList.get(i).getIndex() != 999) {
				Index++;
			}
		}
		if (Index == 10) {
			return false;
		}
		return true;
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
	{
		Log.e("SKY" , "position ::" + position);
		
		if (mThumbImageInfoList.get(position).getIndex() == 999) {
			if (!sizeget()) {
                Toast.makeText(getApplicationContext(), "최대 10장까지 등록이 가능합니다.", Toast.LENGTH_SHORT).show();
                return;
			}
			//999이면.. 클릭안했던거..
			mThumbImageInfoList.get(position).setIndex(getSetpositon());
		}else{
			//재정렬..
			int result = mThumbImageInfoList.get(position).getIndex();
			mThumbImageInfoList.get(position).setIndex(999);
			Log.e("SKY", "result :: " + result);
			for (int i = 0; i < mThumbImageInfoList.size(); i++) {
				if (mThumbImageInfoList.get(i).getIndex() == 999) {
					//Pass
					continue;
				}
				if (result < mThumbImageInfoList.get(i).getIndex()) {
					int a = mThumbImageInfoList.get(i).getIndex()-1;
					Log.e("SKY", "a :: " + a);
					mThumbImageInfoList.get(i).setIndex(a);
				}
			}
		}
		ImageAdapter adapter = (ImageAdapter) arg0.getAdapter();
		adapter.notifyDataSetChanged();
	}
	private class DoFindImageList extends AsyncTask<String, Integer, Long>
	{
		@Override
		protected void onPreExecute()
		{
			mLoagindDialog = ProgressDialog.show(GalleryActivity.this, null, "로딩중...", true, true);
			super.onPreExecute();
		}

		@Override
		protected Long doInBackground(String... arg0)
		{
			long returnValue = 0;
			returnValue = findThumbList();
			return returnValue;
		}

		@Override
		protected void onPostExecute(Long result)
		{
			updateUI();
			mLoagindDialog.dismiss();
		}

		@Override
		protected void onCancelled()
		{
			super.onCancelled();
		}
	}
    @Override
    protected void onDestroy(){
        SchoolInfoWriteActivity.pic_flag = true;
        SchoolInfoWriteActivity.mThumbImageInfoList_copy.clear();
        for (int i = 0; i < mThumbImageInfoList.size(); i++) {
            if (mThumbImageInfoList.get(i).getIndex() != 999) {
                SchoolInfoWriteActivity.mThumbImageInfoList_copy.add(mThumbImageInfoList.get(i));
            }
        }
        Collections.sort(SchoolInfoWriteActivity.mThumbImageInfoList_copy, new NoAscCompare());
        finish();
        super.onDestroy();
    }
}