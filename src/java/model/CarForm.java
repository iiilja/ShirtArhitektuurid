package model;

public class CarForm {
	private String id ;
	private String type ;
	private String count ;
	private String desc;
	

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public void setType (String type) 
	{
		this.type = type;
	}

	public void setCount(String count) 
	{
		this.count = count;
	}


	public String getId() 
	{
		return this.id ;
	}

	public String getType () 
	{
		return this.type ;
	}

	public String getCount () 
	{
		return this.count ;
	}
	
	public String getDescription (){
		return this.desc;
	}
	
	public static CarForm getCF(Car c) {
		CarForm form = new CarForm();
		
		form.id = c.getId()+"";
		form.type = c.getType();
		form.count = c.getCount() + "";
		form.desc = c.getDescription();
		
		return form;
	}

}
