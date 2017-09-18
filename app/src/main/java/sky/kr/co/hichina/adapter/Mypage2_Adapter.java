package sky.kr.co.hichina.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import sky.kr.co.hichina.R;
import sky.kr.co.hichina.common.CommonUtil;
import sky.kr.co.hichina.obj.Mypag1eObj;

public class Mypage2_Adapter extends BaseAdapter {
    CommonUtil dataSet = CommonUtil.getInstance();

	private Activity activity;
	private static LayoutInflater inflater=null;
	ArrayList<Mypag1eObj> items;
	private Handler mAfterAccum;

	public Mypage2_Adapter(Activity a, ArrayList<Mypag1eObj> m_board, Handler mAfterAccum_) {
		activity = a;

		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		items = m_board;
		mAfterAccum = mAfterAccum_;

	}

	public int getCount() {
		return items.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	static class ViewHolder {
		TextView category , count ;

	}
	public View getView(final int position, View convertView, ViewGroup parent) {
		final Mypag1eObj board = items.get(position);
		ViewHolder vh = new ViewHolder();
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.activity_mypage1_item,null);
			vh.category = (TextView) convertView.findViewById(R.id.category);
			vh.count = (TextView) convertView.findViewById(R.id.count);

			convertView.setTag(vh);
		}else {
			vh = (ViewHolder) convertView.getTag();
		}
		vh.category.setText(board.getCATEGORY());
		vh.count.setText("[" + board.getCOUNT() + "]");

        return convertView;
	}

}