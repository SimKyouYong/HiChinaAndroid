package sky.kr.co.hichina.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import sky.kr.co.hichina.GalleryActivity;
import sky.kr.co.hichina.R;
import sky.kr.co.hichina.common.LRUCache;
import sky.kr.co.hichina.obj.ThumbImageInfo;

public class ImageAdapter extends BaseAdapter
{
	private Context mContext;
	private int mCellLayout;
	private LayoutInflater mLiInflater;
	private ArrayList<ThumbImageInfo> mThumbImageInfoList;
	@SuppressWarnings("unchecked")
	private LRUCache mCache; // 캐쉬
	public ImageAdapter(Context c, int cellLayout, ArrayList<ThumbImageInfo> thumbImageInfoList)
	{
		mContext = c;
		mCellLayout = cellLayout;
		mThumbImageInfoList = thumbImageInfoList;
		mLiInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mCache = new LRUCache<String, Bitmap>(30);
	}

	public int getCount()
	{
		return mThumbImageInfoList.size();
	}

	public Object getItem(int position)
	{
		return mThumbImageInfoList.get(position);
	}

	public long getItemId(int position)
	{
		return position;
	}

	@SuppressWarnings("unchecked")
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if (convertView == null)
		{
			convertView = mLiInflater.inflate(mCellLayout, parent, false);
			ImageViewHolder holder = new ImageViewHolder();
			holder.ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
			holder.ivText = (TextView) convertView.findViewById(R.id.txt_id);
			convertView.setTag(holder);
		}

		final ImageViewHolder holder = (ImageViewHolder) convertView.getTag();
		ThumbImageInfo obj = (ThumbImageInfo) mThumbImageInfoList.get(position);
		if (obj.getIndex() == 999) {
			holder.ivText.setVisibility(View.GONE);
		}else{
			holder.ivText.setVisibility(View.VISIBLE);
			holder.ivText.setText("" + obj.getIndex());
		}
		if (!GalleryActivity.mBusy)
		//if (true)
		{
			try
			{
				String path = ((ThumbImageInfo) mThumbImageInfoList.get(position)).getData();
				Log.e("SKY" , "path  ::" + path);
				Bitmap bmp = (Bitmap) mCache.get(path);  
				if (bmp != null)
				{
					holder.ivImage.setImageBitmap(bmp);
				}
				else
				{
					BitmapFactory.Options option = new BitmapFactory.Options();
					option.inSampleSize = 4;
					bmp = BitmapFactory.decodeFile(path, option);
					holder.ivImage.setImageBitmap(bmp);  
					mCache.put(path, bmp); 
				}

				holder.ivImage.setVisibility(View.VISIBLE);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			holder.ivImage.setVisibility(View.INVISIBLE);
		}

		return convertView;
	}
	static class ImageViewHolder
	{
		ImageView ivImage;
		TextView ivText;
		CheckBox chkImage;
	}
}