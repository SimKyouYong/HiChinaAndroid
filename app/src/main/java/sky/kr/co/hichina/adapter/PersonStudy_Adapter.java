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
import sky.kr.co.hichina.obj.PersonStudyObj;

public class PersonStudy_Adapter extends BaseAdapter {
    CommonUtil dataSet = CommonUtil.getInstance();

	private Activity activity;
	private static LayoutInflater inflater=null;
	ArrayList<PersonStudyObj> items;
	private Handler mAfterAccum;

	public PersonStudy_Adapter(Activity a, ArrayList<PersonStudyObj> m_board, Handler mAfterAccum_) {
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
		TextView title_tv , id_tv ,won_tv,good_tv;
        NetworkImageView img1;

	}
	public View getView(final int position, View convertView, ViewGroup parent) {
		final PersonStudyObj board = items.get(position);
		ViewHolder vh = new ViewHolder();
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.activity_personstudy_item,null);
            vh.title_tv = (TextView) convertView.findViewById(R.id.title_tv);
            vh.id_tv = (TextView) convertView.findViewById(R.id.id_tv);
			vh.won_tv = (TextView) convertView.findViewById(R.id.won_tv);
			vh.good_tv = (TextView) convertView.findViewById(R.id.good_tv);
            vh.img1 = (NetworkImageView) convertView.findViewById(R.id.img1);

			convertView.setTag(vh);
		}else {
			vh = (ViewHolder) convertView.getTag();
		}
        vh.title_tv.setText(board.getTITLE());
        vh.id_tv.setText(board.getSELF_ID());
		vh.won_tv.setText(board.getCATEGORY_4());
		vh.good_tv.setText("좋아요:" + board.getGOOD_EA());
        H5ImageLoader.getInstance(activity).set(DEFINE.SERVER_IMG_URL + board.getIMG_1(), vh.img1);

        return convertView;
	}

}