package Classes;

import java.sql.SQLException;

import javafx.scene.control.TableView;

public class Controller {
    private static Controller firstInstance = null;
    public Model theModel;
    
    public static Controller getInstance() throws ClassNotFoundException {
    	if(firstInstance == null)
    		firstInstance = new Controller();
    	return firstInstance;
    }
    
    public void setModel(Model m) throws SQLException {
    	theModel = m;
    	theModel.loadData();
    }
    
    
    public Model getModel() throws SQLException {
    	return theModel;
    }
    
}