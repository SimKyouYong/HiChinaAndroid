package sky.kr.co.hichina.obj;

import android.os.Parcel;
import android.os.Parcelable;

public class Mypag1eObj implements Parcelable{
	public static Creator<Mypag1eObj> getCreator() {
		return CREATOR;
	}

	String CATEGORY;
	String COUNT;

    public Mypag1eObj(String CATEGORY, String COUNT) {
        this.CATEGORY = CATEGORY;
        this.COUNT = COUNT;
    }

    public String getCATEGORY() {
        return CATEGORY;
    }

    public void setCATEGORY(String CATEGORY) {
        this.CATEGORY = CATEGORY;
    }

    public String getCOUNT() {
        return COUNT;
    }

    public void setCOUNT(String COUNT) {
        this.COUNT = COUNT;
    }

    public Mypag1eObj(Parcel in) {
		readFromParcel(in);
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeString(CATEGORY);
		dest.writeString(COUNT);


    }
	private void readFromParcel(Parcel in){

        CATEGORY = in.readString();
        COUNT = in.readString();


    }
	@SuppressWarnings("rawtypes")
	public static final Creator<Mypag1eObj> CREATOR = new Creator() {
		public Object createFromParcel(Parcel in) {
			return new Mypag1eObj(in);
		}

		public Object[] newArray(int size) {
			return new Mypag1eObj[size];
		}
	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
}
