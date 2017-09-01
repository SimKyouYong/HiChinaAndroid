package sky.kr.co.hichina.obj;

import android.os.Parcel;
import android.os.Parcelable;

public class SchoolInfoObj implements Parcelable{
	public static Creator<SchoolInfoObj> getCreator() {
		return CREATOR;
	}	

	String KEY_INDEX;
	String PARENT_KEYINDEX;
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

    public SchoolInfoObj(String KEY_INDEX, String PARENT_KEYINDEX, String BODY, String SELF_ID, String GOOD_EA, String COMMENT_EA, String DATE, String IMG_1, String IMG_2, String IMG_3, String IMG_4, String IMG_5, String IMG_6, String IMG_7, String IMG_8, String IMG_9, String SELF_ID_KEY_INDEX , String CATEGORY_1) {
        this.KEY_INDEX = KEY_INDEX;
        this.PARENT_KEYINDEX = PARENT_KEYINDEX;
        this.BODY = BODY;
        this.SELF_ID = SELF_ID;
        this.GOOD_EA = GOOD_EA;
        this.COMMENT_EA = COMMENT_EA;
        this.DATE = DATE;
        this.IMG_1 = IMG_1;
        this.IMG_2 = IMG_2;
        this.IMG_3 = IMG_3;
        this.IMG_4 = IMG_4;
        this.IMG_5 = IMG_5;
        this.IMG_6 = IMG_6;
        this.IMG_7 = IMG_7;
        this.IMG_8 = IMG_8;
        this.IMG_9 = IMG_9;
        this.SELF_ID_KEY_INDEX = SELF_ID_KEY_INDEX;
        this.CATEGORY_1 = CATEGORY_1;
    }

    public String getKEY_INDEX() {
        return KEY_INDEX;
    }

    public void setKEY_INDEX(String KEY_INDEX) {
        this.KEY_INDEX = KEY_INDEX;
    }

    public String getTITLE() {
        return PARENT_KEYINDEX;
    }

    public void setPARENT_KEYINDEX(String PARENT_KEYINDEX) {
        this.PARENT_KEYINDEX = PARENT_KEYINDEX;
    }

    public String getBODY() {
        return BODY;
    }

    public void setBODY(String BODY) {
        this.BODY = BODY;
    }

    public String getSELF_ID() {
        return SELF_ID;
    }

    public void setSELF_ID(String SELF_ID) {
        this.SELF_ID = SELF_ID;
    }

    public String getGOOD_EA() {
        return GOOD_EA;
    }

    public void setGOOD_EA(String GOOD_EA) {
        this.GOOD_EA = GOOD_EA;
    }

    public String getCOMMENT_EA() {
        return COMMENT_EA;
    }

    public void setCOMMENT_EA(String COMMENT_EA) {
        this.COMMENT_EA = COMMENT_EA;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getIMG_1() {
        return IMG_1;
    }

    public void setIMG_1(String IMG_1) {
        this.IMG_1 = IMG_1;
    }

    public String getIMG_2() {
        return IMG_2;
    }

    public void setIMG_2(String IMG_2) {
        this.IMG_2 = IMG_2;
    }

    public String getIMG_3() {
        return IMG_3;
    }

    public void setIMG_3(String IMG_3) {
        this.IMG_3 = IMG_3;
    }

    public String getIMG_4() {
        return IMG_4;
    }

    public void setIMG_4(String IMG_4) {
        this.IMG_4 = IMG_4;
    }

    public String getIMG_5() {
        return IMG_5;
    }

    public void setIMG_5(String IMG_5) {
        this.IMG_5 = IMG_5;
    }

    public String getIMG_6() {
        return IMG_6;
    }

    public void setIMG_6(String IMG_6) {
        this.IMG_6 = IMG_6;
    }

    public String getIMG_7() {
        return IMG_7;
    }

    public void setIMG_7(String IMG_7) {
        this.IMG_7 = IMG_7;
    }

    public String getIMG_8() {
        return IMG_8;
    }

    public void setIMG_8(String IMG_8) {
        this.IMG_8 = IMG_8;
    }

    public String getIMG_9() {
        return IMG_9;
    }

    public void setIMG_9(String IMG_9) {
        this.IMG_9 = IMG_9;
    }

    public String getSELF_ID_KEY_INDEX() {
        return SELF_ID_KEY_INDEX;
    }

    public void setSELF_ID_KEY_INDEX(String SELF_ID_KEY_INDEX) {
        this.SELF_ID_KEY_INDEX = SELF_ID_KEY_INDEX;
    }

    public SchoolInfoObj(Parcel in) {
		readFromParcel(in);
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {


		dest.writeString(KEY_INDEX);
		dest.writeString(PARENT_KEYINDEX);
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
        PARENT_KEYINDEX = in.readString();
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
