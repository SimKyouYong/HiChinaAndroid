package sky.kr.co.hichina.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import sky.kr.co.hichina.R;
import sky.kr.co.hichina.common.CommonUtil;
import sky.kr.co.hichina.common.DEFINE;
import sky.kr.co.hichina.common.H5ImageLoader;
import sky.kr.co.hichina.obj.SchoolInfoObj;

public class SchoolInfo_Adapter extends BaseAdapter {
    CommonUtil dataSet = CommonUtil.getInstance();

	private Activity activity;
	private static LayoutInflater inflater=null;
	ArrayList<SchoolInfoObj> items;
	private Handler mAfterAccum;

	public SchoolInfo_Adapter(Activity a, ArrayList<SchoolInfoObj> m_board, Handler mAfterAccum_) {
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
		TextView body_tv , date_tv ,comment_tv,good_tv , name_tv;
        NetworkImageView img1;

	}
	public View getView(final int position, View convertView, ViewGroup parent) {
		final SchoolInfoObj board = items.get(position);
		ViewHolder vh = new ViewHolder();
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.activity_schoolinfo_item,null);
            vh.name_tv = (TextView) convertView.findViewById(R.id.name_tv);
            vh.body_tv = (TextView) convertView.findViewById(R.id.body_tv);
			vh.date_tv = (TextView) convertView.findViewById(R.id.date_tv);
			vh.comment_tv = (TextView) convertView.findViewById(R.id.comment_tv);
			vh.good_tv = (TextView) convertView.findViewById(R.id.good_tv);
            vh.img1 = (NetworkImageView) convertView.findViewById(R.id.img1);

			convertView.setTag(vh);
		}else {
			vh = (ViewHolder) convertView.getTag();
		}
        vh.name_tv.setText(board.getSELF_ID() + "(" +board.getCATEGORY_1() + ")");
        vh.body_tv.setText(board.getBODY());
		vh.date_tv.setText(board.getDATE());
		vh.comment_tv.setText("댓글:" + board.getCOMMENT_EA());
		vh.good_tv.setText("좋아요:" + board.getGOOD_EA());
        H5ImageLoader.getInstance(activity).set(DEFINE.SERVER_IMG_URL + board.getIMG_1(), vh.img1);

        return convertView;
	}

}