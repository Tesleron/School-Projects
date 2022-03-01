package application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent; 
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.*;

import javafx.fxml.Initializable;
import Classes.Controller;
import Classes.Dish;
import Classes.Ingredient;
import Classes.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
 
public class addController implements Initializable
{
		@FXML private ComboBox<String> comboBox;
		@FXML private ComboBox<String> categoryComboBox;
		@FXML private TextField dishNameTxtFld;
		@FXML private TextArea dishDescTxtArea;
        @FXML private Button loginButton;
        @FXML private Button submitButton;
        	
          
        ObservableList<String> options = 
        	    FXCollections.observableArrayList(
        	        "Milk",
        	        "Salt",
        	        "Red beans",
        	        "Sugar",
        	        "Eggs",
        	        "Butter",
        	        "Olive Oil"
        	    );
        
        
        ObservableList<String> categoryOptions = 
        	    FXCollections.observableArrayList(
       "Italian","Israeli","American", "Asian", "French", "Greek"
        	    );
        
    private Stage stage;
    private Scene scene;
    private VBox root;
    public Model theModel;
    private String name;
    private FXMLLoader loader;
    private String desc;
 
    
    // Add a public no-args constructor
   public addController() throws SQLException 
  {
//    this.theModel = m;
//  	theModel.loadData();
   }
    
    public void setModel(Model m) throws SQLException {
    	theModel = m;
    	//System.out.println(("addController:" + theModel.getAllRecipes().size()));
    }
     
    public void initialize(URL location, ResourceBundle resources) {
    	dishDescTxtArea.textProperty().addListener((observable, oldValue, newValue) -> {
    		desc = newValue;
    	});
    		comboBox.setItems(options);
    		categoryComboBox.setItems(categoryOptions);
    }
    
    
    
    @FXML
    public void addIngToCurrentDish(ActionEvent event) throws ClassNotFoundException, SQLException {
    	
    	if(comboBox.getSelectionModel().isEmpty())
    	{
    		
    	    JOptionPane.showMessageDialog(null, "Please add at least one ingredient", "Warning",
    	            JOptionPane.WARNING_MESSAGE);
    	    return;
    	}
    	Controller theController = Controller.getInstance();
    	
    	String name = dishNameTxtFld.getText();
    	String selectedIng = comboBox.getValue();
    	Ingredient ing = new Ingredient(selectedIng);
    	
    	theController.getModel().addIngToDB(ing, name);
    	theController.getModel().addIngredientToDish(theController.getModel().getCurrDish(), ing, false);
    	theController.getModel().getCurrDish().setName(name);
    	
    //	System.out.println(theController.getModel().getCurrDish().getName());
    	//System.out.println(theController.getModel().getCurrDish().myIngredeints());
    	
    }
    
    @FXML
    public void submitDishToModel(ActionEvent event) throws ClassNotFoundException, SQLException {

    		
    	Controller theController = Controller.getInstance();
    	String category = categoryComboBox.getValue();
    	String name = dishNameTxtFld.getText();
    	theController.getModel().getCurrDish().setName(name);
    	theController.getModel().getCurrDish().setDescription(desc);
    	theController.getModel().getCurrDish().setAuthor(theController.getModel().getUserName());
    	theController.getModel().getCurrDish().setCategory(category);
    	theController.getModel().getAllRecipes().add(theController.getModel().getCurrDish());
    	
    	
 	   if(name == null || name.trim().equals("")) {
      	    JOptionPane.showMessageDialog(null, "Dish name can not be blank!", "Warning",
      	            JOptionPane.WARNING_MESSAGE);
      		return;
          }
    
       
       
       if(desc == null || desc.trim().equals(""))
       {
     	    JOptionPane.showMessageDialog(null, "Description can not be blank!", "Warning",
   	            JOptionPane.WARNING_MESSAGE);
   		return;
       }
       
       
    	
    	for(int i=0; i< theController.getModel().getCurrDish().getIngredients().size(); i++)
    	{
    		theController.getModel().addIngToDB(theController.getModel().getCurrDish().getIngredients().get(i), name);
    	}
    	theController.getModel().addDishToDB(theController.getModel().getCurrDish());
    	
		theController.getModel().getResultList().add(theController.getModel().getCurrDish());
	    ObservableList<Dish> civs = FXCollections.observableArrayList(theController.getModel().getResultList());
	    theController.getModel().getStringTable().setItems(civs);     
	    
	    Stage stage = (Stage) submitButton.getScene().getWindow();
	    
	    
	    stage.close();

    	
    	
    }
    
    @FXML
    public void addOwnIngPopup(ActionEvent event) throws ClassNotFoundException, SQLException {
    	Controller theController = Controller.getInstance();
    	String newIngName = JOptionPane.showInputDialog("Enter ingredient name");

    	if(newIngName == null || newIngName.isEmpty()) return;
    		Ingredient ing = new Ingredient(newIngName);
    		theController.getModel().addIngredientToDish(theController.getModel().getCurrDish(), ing, false);
    
  
    	
    	
    	
      //  System.out.println(m);
    	
    	//System.out.println("Clicked on add own ing!");
    }
    
    
    
    public Model getModel() {
    	return theModel;
    }
     
    

}