package Model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class WriteObject {

	public WriteObject(Company c) throws FileNotFoundException, IOException {
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("company.dat"));
		outFile.writeObject(c);
		Alert a = new Alert(AlertType.NONE);
		a.setAlertType(AlertType.INFORMATION);
		a.setTitle("SUCCESS");
		a.setHeaderText("Company has been saved to a file.");
		a.setContentText("Company object has been written to company.dat successfully.");
		a.show();
		outFile.close();

	}
}
