package Classes;

import java.util.ArrayList;

public class Dish {
	private String name;
	private ArrayList<Ingredient> ingredients;
	private String description;
	private String author;
	private String category;
	int RID;
	
	public Dish(String name, ArrayList<Ingredient> ingredients,String desc,String author) {
		this.name = name;
		this.ingredients = ingredients;
		description = desc;
		this.author = author;
	}
	
	public Dish(String name,String desc,String author,int idTables, String cate) {
		this.name = name;
		ingredients = new ArrayList<Ingredient>();
		description = desc;
		this.author = author;
		RID = idTables;
		category = cate;
	}
	
	public Dish() {
		ingredients = new ArrayList<Ingredient>();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAuthor(String name) {
		author = name;
	}

	public String getName() {
		return name;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}
	
	public int getID() {//TEMPORARY
		return 0;
	}
	public String getDesc() {
		return description;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String c) {
		category = c;
	}
	
	public StringBuffer myIngredeints() {
		String str = "";
		for(int i = 0; i < ingredients.size();i++) {
			str += ingredients.get(i).getName() + ",";
		}
		StringBuffer sb = new StringBuffer(str);
		//sb.deleteCharAt(str.length()-1);
		String dot = ".";
		//sb.append(dot);
		//sb.replace(str.length()-1, str.length()-1, dot);
	//	str.charAt(str.length()-1) = dot;
		return sb;
	}

	@Override
	public String toString() {
		return "Dish [name=" + name + ", ingredients=" + ingredients + "]";
	}

}
