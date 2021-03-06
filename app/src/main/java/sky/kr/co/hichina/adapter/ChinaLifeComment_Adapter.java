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
import sky.kr.co.hichina.obj.ChinaLifeObj;

public class ChinaLifeComment_Adapter extends BaseAdapter {
    CommonUtil dataSet = CommonUtil.getInstance();

	private Activity activity;
	private static LayoutInflater inflater=null;
	ArrayList<ChinaLifeObj> items;
	private Handler mAfterAccum;

	public ChinaLifeComment_Adapter(Activity a, ArrayList<ChinaLifeObj> m_board, Handler mAfterAccum_) {
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
		TextView body_tv , date_tv, name_tv;

	}
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ChinaLifeObj board = items.get(position);
		ViewHolder vh = new ViewHolder();
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.activity_chinalifecomment_item,null);
            vh.name_tv = (TextView) convertView.findViewById(R.id.name_tv);
            vh.body_tv = (TextView) convertView.findViewById(R.id.body_tv);
			vh.date_tv = (TextView) convertView.findViewById(R.id.date_tv);

			convertView.setTag(vh);
		}else {
			vh = (ViewHolder) convertView.getTag();
		}
        vh.name_tv.setText(board.getSELF_ID() );
        vh.body_tv.setText(board.getBODY());
		vh.date_tv.setText(board.getDATE());

        return convertView;
	}

}