package sky.kr.co.hichina.obj;

public class ThumbImageInfo
{
	private String id;
	private String data;
	private int index;

	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getData()
	{
		return data;
	}
	public void setData(String data)
	{
		this.data = data;
	}
	public ThumbImageInfo(String id, String data, int index) {
		super();
		this.id = id;
		this.data = data;
		this.index = index;
	}
	
	
}
