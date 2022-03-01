package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class ReadObject {

    public Company readCompany() throws FileNotFoundException, IOException, ClassNotFoundException {
        try {
            ObjectInputStream inFile = new ObjectInputStream(new FileInputStream("company.dat"));
            Company currentCompany = (Company) inFile.readObject();
            inFile.close();
    		Alert a = new Alert(AlertType.NONE);
    		a.setAlertType(AlertType.INFORMATION);
    		a.setTitle("LOADING SUCCEED");
    		a.setHeaderText("Company has been loaded from file.");
    		a.setContentText("You can use the menu.");
    		a.show();	
            return currentCompany;
        } catch (Exception err) {
    		Alert a = new Alert(AlertType.NONE);
    		a.setAlertType(AlertType.INFORMATION);
    		a.setTitle("LOADING FAILED");
    		a.setHeaderText("Can not load company file.");
    		a.setContentText("Company will be started from scratch.");
    		a.show();	         
        }
		return null;
    }

}
