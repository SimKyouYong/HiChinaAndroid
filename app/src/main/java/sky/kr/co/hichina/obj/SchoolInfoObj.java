package sky.kr.co.hichina.obj;

import android.os.Parcel;
import android.os.Parcelable;

public class SchoolInfoObj implements Parcelable{
	public static Creator<SchoolInfoObj> getCreator() {
		return CREATOR;
	}	
	String key_index;
	String phone;		
	String body;		
	String date;		
	
	
	
	public SchoolInfoObj(String key_index, String phone, String body, String date) {
		super();
		this.key_index = key_index;
		this.phone = phone;
		this.body = body;
		this.date = date;
	}
	public String getKey_index() {
		return key_index;
	}
	public void setKey_index(String key_index) {
		this.key_index = key_index;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public SchoolInfoObj(Parcel in) {
		readFromParcel(in);
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(key_index);
		dest.writeString(phone);
		dest.writeString(body);
		dest.writeString(date);
		
	}
	private void readFromParcel(Parcel in){
		key_index = in.readString();
		phone = in.readString();
		body = in.readString();
		date = in.readString();

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
