package application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent; 
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.Initializable;
import Classes.Controller;
import Classes.DataBase;
import Classes.Dish;
import Classes.Favorite;
import Classes.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
 
public class FxmlController implements Initializable
{
    
        @FXML private Button loginButton;
        @FXML private Button addRecipeButton;
        @FXML MenuBar myMenuBar;
        @FXML private TextField userNameTF;
        
    private Stage stage;
    private Scene scene;
    private VBox root;
    public Model theModel;
    private String name;
    
    @FXML
    public void switchToHero(ActionEvent event) throws IOException, ClassNotFoundException, SQLException  {
    	
   	Controller theController = Controller.getInstance();
    	
    	   if(userNameTF.getText() == null || userNameTF.getText().isEmpty()) {
       	    JOptionPane.showMessageDialog(null, "Username can not be blank!", "Warning",
       	            JOptionPane.WARNING_MESSAGE);
       		return;
           }
     
        
        
        if(userNameTF.getText().trim().equals(""))
        {
      	    JOptionPane.showMessageDialog(null, "Username can not be blank!", "Warning",
    	            JOptionPane.WARNING_MESSAGE);
    		return;
        }
        
    	
        theController.getModel().checkExistingUser(theController.getModel().getUserName());
    	VBox root = (VBox)FXMLLoader.load(getClass().getResource("Hero.fxml"));
    	
    	

    	    ScrollPane sideBarScroller = new ScrollPane(root);
    	    sideBarScroller.setFitToWidth(true);
    	    theController.getModel().setResultList(  new ArrayList<Dish>());
    	    
            for (int i = 0; i < theModel.getAllRecipes().size(); i++) {
            	theController.getModel().getResultList().add((Dish) theModel.getAllRecipes().get(i));                
            }
            
            theController.getModel().setStringTable(new TableView<Dish>());
            
            ObservableList<Dish> civs = FXCollections.observableArrayList(theController.getModel().getResultList());
            theController.getModel().getStringTable().setItems(civs);
            
    	    Scene scene = new Scene(new Group(), 525, 500);
    	    theController.getModel().getStringTable().setEditable(false);
    	    
            TableColumn<Dish, String> nameCol = new TableColumn<>("Dish name");
            nameCol.setCellValueFactory(
                    Dish -> new SimpleStringProperty(Dish.getValue().getName()));
            
            
           TableColumn<Dish, String> categoryCol = new TableColumn<>("Category");
           categoryCol.setCellValueFactory(
                   Dish -> new SimpleStringProperty(Dish.getValue().getCategory()));
            
            
            TableColumn<Dish, String> ingCol = new TableColumn<>("Ingredients");
            ingCol.setCellValueFactory(Dish -> new SimpleStringProperty(String.valueOf(Dish.getValue().myIngredeints())));
            TableColumn<Dish, String> authorCol = new TableColumn("Author");
            authorCol.setCellValueFactory(
            		Dish -> new SimpleStringProperty((String.valueOf(Dish.getValue().getAuthor()))));
            TableColumn<Dish, String> desCol = new TableColumn("Preperation");
            desCol.setCellValueFactory(
                    Dish -> new SimpleStringProperty(String.valueOf(Dish.getValue().getDesc())));
            theController.getModel().getStringTable().getColumns().addAll(nameCol, categoryCol, ingCol, authorCol, desCol);
            final VBox vbox = new VBox();
            
            
            root.getChildren().addAll(theController.getModel().getStringTable());

    	stage = (Stage)((Node) event.getSource()).getScene().getWindow();
    	scene = new Scene(root,600,400);
    	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	stage.setScene(scene);
    	stage.setResizable(false);
    	stage.show();
    }
    
    
    public void userTyped(StringProperty property, String oldValue, String newValue) throws ClassNotFoundException, SQLException {
    	Controller theController = Controller.getInstance();
    	theController.getModel().setUserName(newValue);
    	
    }
    
    
    public void printGal() 
    {

    }
     
    // Add a public no-args constructor
   public  FxmlController() throws SQLException 
  {

   }
    
   @FXML
    public void setModel(Model m) throws SQLException {
    	theModel = m;
    	//theModel.loadData();
    }

    public String getName() {
    	return name;
    }
    
    
    @FXML
    public void openAddWindow(ActionEvent event) throws IOException, SQLException, ClassNotFoundException 
    {
    	Controller theController = Controller.getInstance();
		
    	Stage stage = new Stage();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AddRecipe.fxml"));
		Parent root = loader.load();
		
		addController addRecipeController = loader.getController();
		addRecipeController.setModel(theController.getModel());
		
		
		
		Dish theDish = new Dish();
		addRecipeController.getModel().setDish(theDish);
		
		Scene scene = new Scene(root);
        stage.setTitle("Add New Recipe");
        stage.setScene(scene);
        stage.show();		
    }
    
    @FXML
    public void openSearchWindow(ActionEvent event) throws IOException, SQLException, ClassNotFoundException 
    {
    	Controller theController = Controller.getInstance();
		
    	Stage stage = new Stage();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("WebView.fxml"));
		Parent root = loader.load();
		
		webController webController = loader.getController();
		//addRecipeController.setModel(theController.getModel());
		
		//Dish theDish = new Dish();
		//addRecipeController.getModel().setDish(theDish);
		
		Scene scene = new Scene(root);
        stage.setTitle("Search For Recipe");
        stage.setScene(scene);
        stage.show();		
    }
    
    public void openFavorites(ActionEvent event) throws IOException, SQLException, ClassNotFoundException 
    {
    	Controller theController = Controller.getInstance();
    	
		
    	Stage stage = new Stage();
		
		// loader = new FXMLLoader(getClass().getResource("Favorites.fxml"));
		VBox root = (VBox)FXMLLoader.load(getClass().getResource("Favorites.fxml"));
		
		theController.getModel().setFavTable(new TableView<String>());
		
		ObservableList<Favorite> items = FXCollections.observableArrayList(theController.getModel().getResultListFav());
        theController.getModel().getFavTable().setItems(items);
        
        TableColumn<Favorite, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(
                Favorite -> new SimpleStringProperty(Favorite.getValue().getTitle()));
        
        
       TableColumn<Favorite, String> urlCol = new TableColumn<>("URL");
       urlCol.setCellValueFactory(
               Favorite -> new SimpleStringProperty(Favorite.getValue().getUrl()));
		
		//favController favController = loader.getController();
       theController.getModel().getFavTable().getColumns().addAll(titleCol, urlCol);
		
       
		
		root.getChildren().addAll(theController.getModel().getFavTable());
		
		//addRecipeController.setModel(theController.getModel());
		
		//Dish theDish = new Dish();
		//addRecipeController.getModel().setDish(theDish);
		
		//System.out.println(Arrays.asList(theController.getModel().getFavorites())); // method 1
		
		Scene scene = new Scene(root);
        stage.setTitle("Search For Recipe");
        stage.setScene(scene);
        stage.show();		
    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//comboBox.setItems(options);
		
	}

}