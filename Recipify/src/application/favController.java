package application;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;



import Classes.Controller;
import Classes.Dish;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class favController implements Initializable{

	//@FXML private TableView<Map.Entry<String,String>> table;
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			Controller theController = Controller.getInstance();
			theController.getModel().getFavorites();
			//theController.getModel().setResultListFav(new ArrayList<String>());
			
			
			
	        Map<String, String> map = new HashMap<>();
	        map.put("one", "One");
	        map.put("two", "Two");
	        map.put("three", "Three");


	        // use fully detailed type for Map.Entry<String, String> 
	        TableColumn<Map.Entry<String, String>, String> column1 = new TableColumn<>("Key");
	        column1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, String>, String>, ObservableValue<String>>() {

	            @Override
	            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, String>, String> p) {
	                // this callback returns property for just one cell, you can't use a loop here
	                // for first column we use key
	                return new SimpleStringProperty(p.getValue().getKey());
	            }
	        });

	        TableColumn<Map.Entry<String, String>, String> column2 = new TableColumn<>("Value");
	        column2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, String>, String>, ObservableValue<String>>() {

	            @Override
	            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, String>, String> p) {
	                // for second column we use value
	                return new SimpleStringProperty(p.getValue().getValue());
	            }
	        });

//	        ObservableList<Map.Entry<String, String>> items = FXCollections.observableArrayList(map.entrySet());
//	        theController.getModel().setFavTable(items);

	   //     theController.getModel().getFavTable().getColumns().addAll(column1, column2);
	       // theController.getModel().getFavTable().refresh();
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	public TableView<Map.Entry<String,String>> getTable(){
//		return table;
//	}
	
}