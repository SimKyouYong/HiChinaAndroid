package sky.kr.co.hichina.obj;

import android.os.Parcel;
import android.os.Parcelable;

public class SchoolInfoObj implements Parcelable{
	public static Creator<SchoolInfoObj> getCreator() {
		return CREATOR;
	}	
	String KEY_INDEX;
	String TITLE;
	String BODY;
    String SELF_ID;
    String GOOD_EA;
    String COMMENT_EA;
    String DATE;
    String IMG_1;
    String IMG_2;
    String IMG_3;
    String IMG_4;
    String IMG_5;
    String IMG_6;
    String IMG_7;
    String IMG_8;
    String IMG_9;
    String SELF_ID_KEY_INDEX;
    String CATEGORY_1;


	public SchoolInfoObj(Parcel in) {
		readFromParcel(in);
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {


		dest.writeString(KEY_INDEX);
		dest.writeString(TITLE);
		dest.writeString(BODY);
        dest.writeString(SELF_ID);
        dest.writeString(GOOD_EA);
        dest.writeString(COMMENT_EA);
        dest.writeString(DATE);
        dest.writeString(IMG_1);
        dest.writeString(IMG_2);
        dest.writeString(IMG_3);
        dest.writeString(IMG_4);
        dest.writeString(IMG_5);
        dest.writeString(IMG_6);
        dest.writeString(IMG_7);
        dest.writeString(IMG_8);
        dest.writeString(IMG_9);
        dest.writeString(SELF_ID_KEY_INDEX);
        dest.writeString(CATEGORY_1);

	}
	private void readFromParcel(Parcel in){

        KEY_INDEX = in.readString();
        TITLE = in.readString();
        BODY = in.readString();
        SELF_ID = in.readString();
        GOOD_EA = in.readString();
        COMMENT_EA = in.readString();
        DATE = in.readString();
        IMG_1 = in.readString();
        IMG_2 = in.readString();
        IMG_3 = in.readString();
        IMG_4 = in.readString();
        IMG_5 = in.readString();
        IMG_6 = in.readString();
        IMG_7 = in.readString();
        IMG_8 = in.readString();
        IMG_9 = in.readString();
        SELF_ID_KEY_INDEX = in.readString();
        CATEGORY_1 = in.readString();

	}
	@SuppressWarnings("rawtypes")
	public static final Creator<SchoolInfoObj> CREATOR = new Creator() {
		public Object createFromParcel(Parcel in) {
			return new SchoolInfoObj(in);
		}

		public Object[] newArray(int size) {
			return new SchoolInfoObj[size];
		}
	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
}
