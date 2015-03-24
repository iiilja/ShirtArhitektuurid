package model;

public class Car {
	private int id ;
	private String type ;
	private int count ;
	private String desc;
	

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public void setType (String type) 
	{
		this.type = type;
	}

	public void setCount(int count) 
	{
		this.count = count;
	}


	public int getId() 
	{
		return this.id ;
	}

	public String getType () 
	{
		return this.type ;
	}

	public int getCount () 
	{
		return this.count ;
	}
	
	public String getDescription (){
		return this.desc;
	}

}
