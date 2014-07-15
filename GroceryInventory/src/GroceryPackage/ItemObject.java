package GroceryPackage;

public class ItemObject {
	
	private String GroceryItem;
	private Double ItemPrice;
	private String ExpDate;
	private int Quantity;
	
	//constructor with no initial variables
	public ItemObject()
	{
		this("",0.0,"",0);
	}
	
	public ItemObject (String i,double p, String d, int q)
	{
		
		GroceryItem = i;	//set item name
		ItemPrice = p;		//set price
		ExpDate = d;		//set expiration date
		Quantity = q;		//set quantity
	}
	
	//methods
	
	//method to set Grocery Name
	public void setGroceryName(String name)
	{
		GroceryItem = name;
	}
	
	//method to get Item Name
	public String getGroceryName()
	{
		return GroceryItem;
	}
	
	//method to set Item Price
	public void setItemPrice(Double price)
	{
		ItemPrice = price;
	}
	
	//method to get Item Price
	public double getItemPrice()
	{
		return ItemPrice;
	}
	
	//method to set Expiration Date
	public void setExpDate(String date)
	{
		ExpDate = date;
	}
	
	//method to get Expiration Date
	public String getExpDate()
	{
		return ExpDate;
	}
	
	//method to set Quantity
	public void setItemQuantity(int q)
	{
		Quantity = q;
	}
	
	//method to get Quantity
	public int getItemQuantity()
	{
		return Quantity;
	}
	
	
	
}
