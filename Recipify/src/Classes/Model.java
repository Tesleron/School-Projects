package Classes;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import StrategyClasses.*;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class Model {

	private DataBase theDatabase;
	private Context cont;
	private ArrayList<Dish> allRecipes;
	public Dish currentDish;
	private String userName;
	private HashMap<String, String> Favorites;
	List<Dish> resultList;
    TableView stringTable;
    TableView table;
    List<Favorite> resultListFav;
	
	public Model() throws ClassNotFoundException {
		theDatabase = DataBase.getInstance();
		allRecipes = new ArrayList<Dish>();
		Favorites = new HashMap<String, String>();
		resultListFav = new ArrayList<Favorite>();
	}
	
	public void addDishToDB(Dish theDish) throws SQLException {
	//	Dish theDish = new Dish(name , list,desc,author);
		
		theDatabase.updateByQuery("INSERT INTO Recipe VALUES"
				+ " ( " + null +  ", "
						+ "'" + theDish.getName()
				+"', '"+theDish.getCategory()+"','"+theDish.getAuthor() + "','"+theDish.getDesc()+"'"+ "); ");
		
//		theDatabase.updateByQuery("INSERT INTO Category VALUES"
//				+ " ( '" + categoryName +  "', '" + theDish.getName()
//				+ "' ); ");
		
		allRecipes.add(theDish);
	}
	
	public void addIngToDB(Ingredient ing,String name) throws SQLException{
		theDatabase.updateByQuery("INSERT INTO Ingredient VALUES"
				+ " ( " + null +  ", '" + ing.getName()
				+"','"+name+ "' ); ");
	}
	
	public String getUserName() {
		return userName;
	}

	public List<Dish> getResultList() {
		return resultList;
	}

	public void setResultList(List<Dish> resultList) {
		this.resultList = resultList;
	}
	
	public void setResultListFav(List<Favorite> lst) {
		this.resultListFav = lst;
	}

	public TableView getStringTable() {
		return stringTable;
	}
	
	public void setFavTable(TableView table){
		
		this.table = table;
		//return table;
	}
	
	public  TableView getFavTable(){
		return table;
	}

	public void setStringTable(TableView stringTable) {
		this.stringTable = stringTable;
	}

	public void setUserName(String name) {
		this.userName = name;
	}
	
	public boolean checkExistingUser(String user) throws SQLException {
		if(theDatabase.searchUser(user) == true)
			return true;
		else {
			String query="INSERT INTO Users VALUES('"+user+"'" +");";
			theDatabase.updateByQuery(query);
			return true;
		}
		
	}
	
	public void addIngredientToDish(Dish theDish , Ingredient ing,boolean readFromSQL) throws SQLException {
		cont = new Context(new OperationAdd());
		
		cont.executeStrategy(theDish, ing);
		
		
	}
	
    public HashMap<String, String> getFavorites() {
		return Favorites;
	}

	public void setFavorites(HashMap<String, String> favorites) {
		Favorites = favorites;
	}
	
	public void setDish(Dish d) {
    	currentDish = d;
    }
	public Dish getCurrDish() {
		return currentDish;
	}
	
	public void removeIngredientFromDish(int indexOfDish , Ingredient ing) {
		cont = new Context(new OperationRemove());
		
		cont.executeStrategy(allRecipes.get(indexOfDish), ing);
		//add query to remove from ingredient table
	}

	public DataBase getTheDatabase() {
		return theDatabase;
	}

	public ArrayList<Dish> getAllRecipes() {
		return allRecipes;
	}
	
	public List<Favorite> getResultListFav(){
		return resultListFav;
	}
	
	
	public void loadData() throws SQLException {
		theDatabase.loadFromSQL(this);		
	}
	
	
	public int searchDishIndexWithGivenID(String dishName) {
		for(int i = 0; i < allRecipes.size(); i++) {
			if(allRecipes.get(i).getName().equals(dishName))
				return i;
		}
		return -1;
	}
	
	
	
	
}
