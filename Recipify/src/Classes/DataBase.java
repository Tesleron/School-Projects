package Classes;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


public class DataBase {
    private Connection conn;
    private static DataBase firstInstance = null;


    
        private DataBase() throws ClassNotFoundException{
             conn = null;    
             createConnection();
        }
        
        public static DataBase getInstance() throws ClassNotFoundException {
        	if(firstInstance == null)
        		firstInstance = new DataBase();
        	return firstInstance;
        	
        }
        
//        void selectByQuery (String query) throws SQLException {        
//            ResultSet rs = conn.createStatement().executeQuery(query);
//            while (rs.next()) {
//                System.out.println(rs.getInt("studentID") + "|" + rs.getString("studentLastName") + "|" + rs.getString("studentFirstName") + "|" + rs.getString("birthPlace")+ "|" + rs.getString("city"));
//            }
//        }
        
        void updateByQuery (String query) throws SQLException {
            int numAffectedRows = conn.createStatement().executeUpdate(query);
            //System.out.printf("Num affected rows = %d\n",numAffectedRows);
            
        }
        
        
        boolean searchUser(String name) throws SQLException {
        	 ResultSet rs = conn.createStatement().executeQuery("SELECT UserName FROM Users WHERE UserName = '"+name+"';");
        	 if (!rs.isBeforeFirst() ) {  
        		    return false;
        		} 
             return true;
             
             
        }
        
        void createConnection() throws ClassNotFoundException {
            try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String dbUrl = "jdbc:mysql://localhost:3306/recipeappdb";

            conn = DriverManager.getConnection(dbUrl, "root", "a2de9kclwbf");
            }
            catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        
        }
        
        public void loadFromSQL(Model m) throws SQLException {
    		HashMap<Integer, Integer> dishIng = new HashMap<Integer, Integer>();
    		int index = 0;
        	
        	
        	ResultSet recipeResultSet = conn.createStatement().executeQuery("SELECT * FROM recipe");

    		while (recipeResultSet.next()) {
    			int RID = (recipeResultSet.getInt("RID"));
    			String dishName = (recipeResultSet.getString("RName"));
    			String category = (recipeResultSet.getString("Category"));
    			String author = (recipeResultSet.getString("Author"));
    			String desc = (recipeResultSet.getString("Instructions"));
    			Dish tempDish = new Dish(dishName,desc,author,RID,category);
    			m.getAllRecipes().add(tempDish);
    		}
    		
    		ResultSet ingResultSet = conn.createStatement().executeQuery("SELECT * FROM ingredient");
    		while (ingResultSet.next()) {
    			int ingID = (ingResultSet.getInt("IngID"));
    			String ingName = (ingResultSet.getString("IngName"));
    			String dishName = (ingResultSet.getString("DishName"));
    			Ingredient tempIng = new Ingredient(ingName);
    			
    			index = m.searchDishIndexWithGivenID(dishName);
    			
    			
    			m.addIngredientToDish(m.getAllRecipes().get(index), tempIng, true);
    		}
        	
        	
        }
    	
        
        
}