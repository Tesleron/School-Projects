package View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;

import Model.Company;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;


public class View implements Viewable {
	private Stage primaryStage;
	private Scene primaryScene;
	private Group root;
	private Text cmpNameTxt;
	private ArrayList<RadioButton> depPrefArray;
	private ArrayList<RadioButton> rolePrefArray;
	private TextArea DepListTA;
	private Button[] menuButtons;
	private Button addDepartment;
	private Button addRole;
	private Button addEmployee;
	private Button submitEmployee;
	private Button addAnotherEmpButton;
	private Button addNewRoleButton;
	private Button addNewDepButton;
	private Button finishButton;
	private Button nextStepDepPref;
	private Button nextStepRolePref;
	private Button editButton;
	private Button editRoleButton;
	private TextField cmpNametf;
	private Spinner<Integer> earlyHourDelta;
	private Spinner<Integer> laterHourDelta;
	private Spinner<Integer> earlyDepHourDelta;
	private Spinner<Integer> laterDepHourDelta;
	private Spinner<Integer> earlyRoleHourDelta;
	private Spinner<Integer> laterRoleHourDelta;
	private Spinner<Integer> perHour;
	private Spinner<Integer> salesLastMonth;
	private Spinner<Integer> baseSalary;
	private Stage insertEntitiesStage;
	private Scene insertEntitiesScene;
	private Scene depScene;
	private Scene roleScene;
	private Scene empScene;
	private Group companyWindow;
	private Group depWindow;
	private Group roleWindow;
	private Group employeeWindow;
	private Group afterSubmitEmpWindow;
	private ToggleGroup group;
	private ToggleGroup depGroup;
	private ToggleGroup roleGroup;
	private ToggleGroup employeeType;
	private CheckBox cbChangeable;
	private CheckBox cbSynchronizable;
	private CheckBox cbDepChangeable;
	private CheckBox cbDepSynchronizable;
	private CheckBox cbRoleChangeable;
	private CheckBox cbRoleSynchronizable;
	private int depIndexToChange;
	private int roleIndexToChange;

	public View(Stage primaryStage) {
		this.primaryStage = primaryStage;
		root = new Group();
		primaryScene = new Scene(root, 640, 400);

		for (int i = 0; i < 400; i++) {
			Line l = new Line(0, i, 640, i);
			l.setStroke(Color.rgb(i / 2, 200, 255));
			root.getChildren().add(l);
		}

		Text title = new Text("Choose one of the following");
		DropShadow ds = new DropShadow();
		ds.setOffsetY(3.0f);
		ds.setColor(Color.color(0.4f, 0.4f, 0.4f));;
		title.setEffect(ds);

		title.setTranslateX(62);
		title.setTranslateY(40);
		title.setStyle("-fx-font: 25 arial;");
		title.setStroke(Color.GOLD);
		title.setFill(Color.BLACK);
		title.setStyle(""
				+ "    -fx-font: 40px Tahoma;\r\n"
				+ "    -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, white 50%);\r\n"
				+ "    -fx-stroke: black;\r\n"
				+ "    -fx-stroke-width: 1;\r\n"
				+ "");

		menuButtons = new Button[5];
		menuButtons[0] = new Button("Insert Entities");
		menuButtons[0].setPrefSize(121, 39);
		menuButtons[0].setTranslateX(156);
		menuButtons[0].setTranslateY(65);
		menuButtons[1] = new Button("Show Entities");
		menuButtons[1].setPrefSize(127, 39);
		menuButtons[1].setTranslateX(321);
		menuButtons[1].setTranslateY(65);
		menuButtons[2] = new Button("Change Role Preference");
		menuButtons[2].setPrefSize(242, 46);
		menuButtons[2].setTranslateX(33);
		menuButtons[2].setTranslateY(142);
		menuButtons[3] = new Button("Change Department Preference");
		menuButtons[3].setPrefSize(242, 46);
		menuButtons[3].setTranslateX(327);
		menuButtons[3].setTranslateY(142);
		menuButtons[4] = new Button("Show Experiment Result(profit/loss)");
		menuButtons[4].setPrefSize(282, 95);
		menuButtons[4].setTranslateX(159);
		menuButtons[4].setTranslateY(242);

		root.getChildren().addAll(menuButtons);
		root.getChildren().add(title);

		primaryStage.setTitle("Final project by Gal Tesler and Or Messing");
		primaryStage.setScene(primaryScene);
		primaryStage.show();

	}

	public void InsertEntitiesPopup() {
		insertEntitiesStage = new Stage();
		insertEntitiesStage.setTitle("Insert Entities Dialog (Company)");
		companyWindow = new Group();
		VBox vb = new VBox();

		for (int i = 0; i < 375; i++) {
			Line l = new Line(0, i, 640, i);
			l.setStroke(Color.rgb(i / 2, 62, 112));
			companyWindow.getChildren().add(l);
		}

		Text cmpName = new Text("Enter company name");
		cmpName.setTranslateX(203);
		cmpName.setTranslateY(35);
		cmpName.setStyle("-fx-font: 22 aharoni;");
		cmpName.setFill(Color.WHITE);
		cmpName.setStroke(Color.RED);

		cmpNametf = new TextField();
		cmpNametf.setTranslateX(203);
		cmpNametf.setTranslateY(120);

		addDepartment = new Button("Add department");
		addDepartment.setTranslateX(203);
		addDepartment.setTranslateY(155);
		addDepartment.setPrefSize(242, 46);

		addDepartment.disableProperty()
				.bind(cmpNametf.textProperty().isEmpty().or(cmpNametf.textProperty().isEqualTo("[a-zA-Z]+")));
		// qesem

		vb.getChildren().addAll(cmpName, cmpNametf, addDepartment);
		companyWindow.getChildren().add(vb);
		insertEntitiesScene = new Scene(companyWindow, 640, 375);
		insertEntitiesStage.setScene(insertEntitiesScene);
		insertEntitiesStage.show();

	}

	public void showEntitiesPopup(Company company) {
		insertEntitiesStage = new Stage();
		insertEntitiesStage.setTitle("Show Entities Dialog (Company)");
		Group showEntitesWindow = new Group();
		VBox vb = new VBox();

		for (int i = 0; i < 375; i++) {
			Line l = new Line(0, i, 640, i);
			l.setStroke(Color.rgb(i / 2, 101, 210));
			showEntitesWindow.getChildren().add(l);
		}
		vb.setTranslateX(20);
		vb.setTranslateY(70);

		if (company == null) {
			cmpNameTxt = new Text("*** PLEASE ENTER ENTITIES ***");
			DepListTA = new TextArea();
			DepListTA.setFont(new Font("Arial", 15));
			DepListTA.appendText("Please enter the company's information before trying to show entites.");
		} else {

			cmpNameTxt = new Text(company.getCompanyName());
			DepListTA = new TextArea();
			DepListTA.setFont(new Font("Arial", 15));
			DepListTA.appendText("Company name:" + company.getCompanyName() + "\n\n");
			DepListTA.setPrefSize(550, 270);
			for (int i = 0; i < company.getAllDepartments().size(); i++) {
				DepListTA.appendText(company.getAllDepartments().get(i).toString() + "\n\n");
			}

		}
		vb.getChildren().addAll(DepListTA);
		showEntitesWindow.getChildren().addAll(cmpNameTxt, vb);
		DepListTA.setEditable(false);
		cmpNameTxt.setTranslateX(190);
		cmpNameTxt.setTranslateY(55);
		cmpNameTxt.setStyle("-fx-font: 22 aharoni;");
		cmpNameTxt.setFill(Color.WHITE);
		cmpNameTxt.setStroke(Color.RED);

		insertEntitiesScene = new Scene(showEntitesWindow, 640, 375);
		insertEntitiesStage.setScene(insertEntitiesScene);
		insertEntitiesStage.show();
	}

	public void showChangeRolePref(Company company) {

		insertEntitiesStage = new Stage();
		insertEntitiesStage.setTitle("Choose role to change preference");

		Group showEntitesWindow = new Group();
		VBox vb = new VBox();
		VBox radioVb = new VBox();
		rolePrefArray = new ArrayList<RadioButton>();
		roleGroup = new ToggleGroup();

		for (int i = 0; i < 375; i++) {
			Line l = new Line(0, i, 640, i);
			l.setStroke(Color.rgb(i / 2, 101, 210));
			showEntitesWindow.getChildren().add(l);
		}

		for (int i = 0; i < company.getAllDepartments().size(); i++) {
			for (int j = 0; j < company.getAllDepartments().get(i).getRoles().size(); j++) {
				if (company.getAllDepartments().get(i).getRoles().get(j).getIsChangeAble() == true) {
					RadioButton tempRadioButton = new RadioButton(
							company.getAllDepartments().get(i).getRoles().get(j).getClass().getSimpleName() + " number "
									+ (j + 1) + " (of department number " + (i + 1) + ")");
					tempRadioButton.setToggleGroup(roleGroup);
					rolePrefArray.add(tempRadioButton);
				}

			}
		}

		if (rolePrefArray.size() == 0) {
			Alert a = new Alert(AlertType.NONE);
			a.setAlertType(AlertType.ERROR);
			a.setContentText("There are no changeable roles.");

			// show the dialog
			a.show();
			return;
		}

		nextStepRolePref = new Button("Next step");
		nextStepRolePref.setTranslateX(203);
		nextStepRolePref.setTranslateY(280);
		nextStepRolePref.setPrefSize(242, 46);

//		RadioButton[] array = rolePrefArray.toArray(new RadioButton[0]);

		rolePrefArray.get(0).setSelected(true);
		radioVb.getChildren().addAll(rolePrefArray);
		vb.getChildren().addAll(radioVb);
		showEntitesWindow.getChildren().addAll(nextStepRolePref, vb);

		insertEntitiesScene = new Scene(showEntitesWindow, 640, 375);
		insertEntitiesStage.setScene(insertEntitiesScene);
		insertEntitiesStage.show();
	}

	public void showChangeDepPref(Company company) {

		insertEntitiesStage = new Stage();
		insertEntitiesStage.setTitle("Choose department to change preference");

		Group showEntitesWindow = new Group();
		VBox vb = new VBox();
		VBox radioVb = new VBox();
		depPrefArray = new ArrayList<RadioButton>();
		depGroup = new ToggleGroup();

		for (int i = 0; i < 375; i++) {
			Line l = new Line(0, i, 640, i);
			l.setStroke(Color.rgb(i / 2, 101, 210));
			showEntitesWindow.getChildren().add(l);
		}

		for (int i = 0; i < company.getAllDepartments().size(); i++) {
			if (company.getAllDepartments().get(i).getIsChangeAble()) {
				RadioButton tempRadioButton = new RadioButton(
						company.getAllDepartments().get(i).getClass().getSimpleName() + " number " + (i + 1));
				tempRadioButton.setToggleGroup(depGroup);
				depPrefArray.add(tempRadioButton);

			}
		}

		if (depPrefArray.size() == 0) {
			Alert a = new Alert(AlertType.NONE);
			a.setAlertType(AlertType.ERROR);
			a.setContentText("There are no changeable departments");

			// show the dialog
			a.show();
			return;
		}

		nextStepDepPref = new Button("Next step");
		nextStepDepPref.setTranslateX(203);
		nextStepDepPref.setTranslateY(280);
		nextStepDepPref.setPrefSize(242, 46);

//		RadioButton[] array = depPrefArray.toArray(new RadioButton[0]);
		depPrefArray.get(0).setSelected(true);
		radioVb.getChildren().addAll(depPrefArray);
		vb.getChildren().addAll(radioVb);
		showEntitesWindow.getChildren().addAll(nextStepDepPref, vb);

		insertEntitiesScene = new Scene(showEntitesWindow, 640, 375);
		insertEntitiesStage.setScene(insertEntitiesScene);
		insertEntitiesStage.show();
	}

	public void addDepartmentWindow() {
//		rootEnt.getChildren().clear();
		depWindow = new Group();
		Text depFeatures = new Text("Enter department features");
		depFeatures.setTranslateX(195);
		depFeatures.setTranslateY(35);
		depFeatures.setStyle("-fx-font: 22 aharoni;");
		depFeatures.setFill(Color.WHITE);
		depFeatures.setStroke(Color.RED);

		insertEntitiesStage.setTitle("Insert Entites Dialog (Department)");
		HBox radioHb = new HBox();
		HBox checkBoxHb = new HBox();
		VBox vb = new VBox();

		checkBoxHb.setPadding(new Insets(20, 16, 20, 16));
		checkBoxHb.setSpacing(10);
		radioHb.setPadding(new Insets(20, 16, 20, 16));
		radioHb.setSpacing(10);

		for (int i = 0; i < 375; i++) {
			Line l = new Line(0, i, 640, i);
			l.setStroke(Color.rgb(i / 2, 101, 210));
			depWindow.getChildren().add(l);
		}

		vb.setTranslateX(150);
		vb.setTranslateY(70);
		radioHb.setTranslateX(20);

		depGroup = new ToggleGroup();

		RadioButton rbEarlier = new RadioButton("Earlier");
		rbEarlier.setToggleGroup(depGroup);

		RadioButton rbLater = new RadioButton("Later");
		rbLater.setToggleGroup(depGroup);

		RadioButton rbSame = new RadioButton("Same");
		rbSame.setToggleGroup(depGroup);

		RadioButton rbHome = new RadioButton("Home");
		rbHome.setToggleGroup(depGroup);
		rbHome.setSelected(true);
		Text txtEarlier = new Text("How much earlier?");
		Text txtLater = new Text("How much later?");

		laterDepHourDelta = new Spinner<>(1, 16, 1);
		earlyDepHourDelta = new Spinner<>(1, 8, 1);

		HBox early = new HBox();
		HBox later = new HBox();

		early.getChildren().addAll(txtEarlier, earlyDepHourDelta);
		later.getChildren().addAll(txtLater, laterDepHourDelta);

		rbEarlier.selectedProperty()
				.addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
					if (new_val == true)
						vb.getChildren().addAll(early);
					else {
						vb.getChildren().removeAll(early);
					}
				});

		rbLater.selectedProperty()
				.addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
					if (new_val == true)
						vb.getChildren().addAll(later);
					else {
						vb.getChildren().removeAll(later);
					}
				});

		cbDepChangeable = new CheckBox("Preference changing allowed?");
		cbDepSynchronizable = new CheckBox("Synchronized roles?");

		addRole = new Button("Add role");
		addRole.setTranslateX(203);
		addRole.setTranslateY(280);
		addRole.setPrefSize(242, 46);

		radioHb.getChildren().addAll(rbEarlier, rbLater, rbSame, rbHome);
		checkBoxHb.getChildren().addAll(cbDepChangeable, cbDepSynchronizable);
		vb.getChildren().addAll(radioHb, checkBoxHb);
		depWindow.getChildren().addAll(depFeatures, vb, addRole);
		depScene = new Scene(depWindow, 640, 375);
		insertEntitiesStage.setScene(depScene);
		insertEntitiesStage.show();

	}

	public void addRoleWindow() {
//		rootEnt.getChildren().clear();
		roleWindow = new Group();
		Text roleFeatures = new Text("Enter role features");
		roleFeatures.setTranslateX(195);
		roleFeatures.setTranslateY(35);
		roleFeatures.setStyle("-fx-font: 22 aharoni;");
		roleFeatures.setFill(Color.WHITE);
		roleFeatures.setStroke(Color.RED);

		insertEntitiesStage.setTitle("Insert Entites Dialog (Role)");
		HBox radioHb = new HBox();
		HBox checkBoxHb = new HBox();
		VBox vb = new VBox();

		checkBoxHb.setPadding(new Insets(20, 16, 20, 16));
		checkBoxHb.setSpacing(10);
		radioHb.setPadding(new Insets(20, 16, 20, 16));
		radioHb.setSpacing(10);

		for (int i = 0; i < 375; i++) {
			Line l = new Line(0, i, 640, i);
			l.setStroke(Color.rgb(i / 3, 53, 79));
			roleWindow.getChildren().add(l);
		}

		vb.setTranslateX(150);
		vb.setTranslateY(70);
		radioHb.setTranslateX(20);

		roleGroup = new ToggleGroup();

		RadioButton rbEarlier = new RadioButton("Earlier");
		rbEarlier.setToggleGroup(roleGroup);

		RadioButton rbLater = new RadioButton("Later");
		rbLater.setToggleGroup(roleGroup);

		RadioButton rbSame = new RadioButton("Same");
		rbSame.setToggleGroup(roleGroup);

		RadioButton rbHome = new RadioButton("Home");
		rbHome.setToggleGroup(roleGroup);
		rbHome.setSelected(true);

		cbRoleChangeable = new CheckBox("Preference changing allowed?");
		cbRoleSynchronizable = new CheckBox("Synchronized Employees?");
		checkBoxHb.getChildren().add(cbRoleChangeable);
		checkBoxHb.getChildren().add(cbRoleSynchronizable);

		Text txtEarlier = new Text("How much earlier?");
		Text txtLater = new Text("How much later?");

		earlyRoleHourDelta = new Spinner<>(1, 8, 1);
		laterRoleHourDelta = new Spinner<>(1, 16, 1);

		HBox early = new HBox();
		HBox later = new HBox();

		early.getChildren().addAll(txtEarlier, earlyRoleHourDelta);
		later.getChildren().addAll(txtLater, laterRoleHourDelta);

		rbEarlier.selectedProperty()
				.addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
					if (new_val == true)
						vb.getChildren().addAll(early);
					else {
						vb.getChildren().removeAll(early);
					}
				});

		rbLater.selectedProperty()
				.addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
					if (new_val == true)
						vb.getChildren().addAll(later);
					else {
						vb.getChildren().removeAll(later);
					}
				});

		addEmployee = new Button("Add employee");
		addEmployee.setTranslateX(203);
		addEmployee.setTranslateY(280);
		addEmployee.setPrefSize(242, 46);

		// checkBoxHb.getChildren().addAll(cbChangeable,cbSynchronizable);
		radioHb.getChildren().addAll(rbEarlier, rbLater, rbSame, rbHome);
		vb.getChildren().addAll(radioHb, checkBoxHb);
		roleWindow.getChildren().addAll(roleFeatures, vb, addEmployee);

		roleScene = new Scene(roleWindow, 640, 375);
		insertEntitiesStage.setScene(roleScene);
		insertEntitiesStage.show();
		// hb.setAlignment(Pos.CENTER);
		// A checkbox with a string caption

	}

	public void addEmployeeWindow() {

//		rootEnt.getChildren().clear();
		employeeWindow = new Group();
		Text employeeAttributes = new Text("Enter emplyoee attributes");
		employeeAttributes.setTranslateX(195);
		employeeAttributes.setTranslateY(35);
		employeeAttributes.setStyle("-fx-font: 22 aharoni;");
		employeeAttributes.setFill(Color.WHITE);
		employeeAttributes.setStroke(Color.CYAN);

		insertEntitiesStage.setTitle("Insert Entites Dialog (Employee)");
		HBox radioHb = new HBox();
		HBox employeeHb = new HBox();
//		HBox cbHb = new HBox();
		VBox vb = new VBox();

//		cbHb.setPadding(new Insets(20, 16, 20, 16));
//		cbHb.setSpacing(10);
		radioHb.setPadding(new Insets(20, 16, 20, 16));
		radioHb.setSpacing(10);
		employeeHb.setPadding(new Insets(20, 16, 20, 16));
		employeeHb.setSpacing(10);

		for (int i = 0; i < 375; i++) {
			Line l = new Line(0, i, 640, i);
			l.setStroke(Color.rgb(i / 2, 128, 12));
			employeeWindow.getChildren().add(l);
		}

		vb.setTranslateX(150);
		vb.setTranslateY(70);
		radioHb.setTranslateX(20);

		group = new ToggleGroup();
		employeeType = new ToggleGroup();

		RadioButton rbEarlier = new RadioButton("Earlier");
		rbEarlier.setToggleGroup(group);

		RadioButton rbLater = new RadioButton("Later");
		rbLater.setToggleGroup(group);

		RadioButton rbSame = new RadioButton("Same");
		rbSame.setToggleGroup(group);

		RadioButton rbHome = new RadioButton("Home");
		rbHome.setToggleGroup(group);
		rbHome.setSelected(true);

		RadioButton rbBase = new RadioButton("Base Employee");
		rbBase.setToggleGroup(employeeType);

		RadioButton rbBaseAndSales = new RadioButton("Base And Sales Employee");
		rbBaseAndSales.setToggleGroup(employeeType);
		rbBaseAndSales.setSelected(true);

		RadioButton rbHourly = new RadioButton("Hourly Employee");
		rbHourly.setToggleGroup(employeeType);

		Text txtEarlier = new Text("How much earlier?");
		Text txtLater = new Text("How much later?");
		Text txtBase = new Text("How much is the base salary?");
		Text txtSales = new Text("How much was the sales of the last month??");
		Text txtHourly = new Text("How much per hour?");

		laterHourDelta = new Spinner<>(1, 16, 1);
		earlyHourDelta = new Spinner<>(1, 8, 1);
		baseSalary = new Spinner<>(500, 1500, 1000, 100);
		salesLastMonth = new Spinner<>(500, 1500, 1000, 100);
		perHour = new Spinner<>(1, 1500, 1);

		HBox early = new HBox();
		HBox later = new HBox();
		HBox base = new HBox();
		HBox sales = new HBox();
		HBox hourly = new HBox();

		early.getChildren().addAll(txtEarlier, earlyHourDelta);
		later.getChildren().addAll(txtLater, laterHourDelta);
		base.getChildren().addAll(txtBase, baseSalary);
		sales.getChildren().addAll(txtSales, salesLastMonth);
		hourly.getChildren().addAll(txtHourly, perHour);

		rbEarlier.selectedProperty()
				.addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
					if (new_val == true)
						vb.getChildren().addAll(early);
					else {
						vb.getChildren().removeAll(early);
					}
				});

		rbLater.selectedProperty()
				.addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
					if (new_val == true)
						vb.getChildren().addAll(later);
					else {
						vb.getChildren().removeAll(later);
					}
				});

		rbBase.selectedProperty()
				.addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
					if (new_val == true)
						vb.getChildren().addAll(base);
					else {
						vb.getChildren().removeAll(base);
					}
				});

		rbBaseAndSales.selectedProperty()
				.addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
					if (new_val == true)
						vb.getChildren().addAll(sales, base);
					else {
						vb.getChildren().removeAll(sales, base);
					}
				});

		rbHourly.selectedProperty()
				.addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
					if (new_val == true)
						vb.getChildren().addAll(hourly);
					else {
						vb.getChildren().removeAll(hourly, sales, base);
					}
				});

//		cbChangeable = new CheckBox("Preference changing allowed?");

		submitEmployee = new Button("Submit");
		submitEmployee.setTranslateX(250);
		submitEmployee.setTranslateY(320);
		submitEmployee.setPrefSize(180, 46);

		radioHb.getChildren().addAll(rbEarlier, rbLater, rbHome, rbSame);
		employeeHb.getChildren().addAll(rbBase, rbBaseAndSales, rbHourly);
//		cbHb.getChildren().addAll(cbChangeable/* , cbSynchronizable */);
		vb.getChildren().addAll(radioHb, employeeHb/* , cbHb */);
		employeeWindow.getChildren().addAll(employeeAttributes, vb, submitEmployee);

		empScene = new Scene(employeeWindow, 640, 375);
		insertEntitiesStage.setScene(empScene);
		insertEntitiesStage.show();

	}

	public void afterSubmitEmpWindow() {
//		rootEnt.getChildren().clear();
		afterSubmitEmpWindow = new Group();
		Text addedSuccessfully = new Text("Employee added successfully , Please choose an option");
		addedSuccessfully.setTranslateX(5);
		addedSuccessfully.setTranslateY(35);
		addedSuccessfully.setStyle("-fx-font: 24 aharoni;");
		addedSuccessfully.setFill(Color.BLUE);
		addedSuccessfully.setStroke(Color.WHITE);

		insertEntitiesStage.setTitle("Select an option");

		for (int i = 0; i < 375; i++) {
			Line l = new Line(0, i, 640, i);
			l.setStroke(Color.rgb(i / 3, 150, 66));
			afterSubmitEmpWindow.getChildren().add(l);
		}

		HBox hb1 = new HBox();
		HBox hb2 = new HBox();
		VBox vb = new VBox();

		hb1.setPadding(new Insets(20, 16, 20, 16));
		hb1.setSpacing(13);
		hb2.setPadding(new Insets(20, 16, 20, 16));
		hb2.setSpacing(13);

		vb.setTranslateX(40);
		vb.setTranslateY(70);

		addAnotherEmpButton = new Button("Add another employee (this role)");
		addAnotherEmpButton.setPrefSize(250, 46);

		addNewRoleButton = new Button("Add another role (this department)");
		addNewRoleButton.setPrefSize(250, 46);

		addNewDepButton = new Button("Add a NEW department (this company)");
		addNewDepButton.setPrefSize(250, 46);

		finishButton = new Button("Finish and return to main menu");
		finishButton.setPrefSize(250, 46);

		hb1.getChildren().addAll(addAnotherEmpButton, addNewRoleButton);
		hb2.getChildren().addAll(addNewDepButton, finishButton);
		vb.getChildren().addAll(hb1, hb2);

		afterSubmitEmpWindow.getChildren().addAll(addedSuccessfully, vb);
		insertEntitiesScene = new Scene(afterSubmitEmpWindow, 640, 375);
		insertEntitiesStage.setScene(insertEntitiesScene);
		insertEntitiesStage.show();

	}

	public void prefEditorWindow(String selectedDepartment) {
		insertEntitiesStage.setTitle("Preference editor");
		Group root = new Group();

		editButton = new Button("Save");
		editButton.setPrefSize(250, 42);
		editButton.setTranslateX(200);
		editButton.setTranslateY(270);

		Text title = new Text("Preference editor for " + selectedDepartment);
		title.setTranslateX(120);
		title.setTranslateY(35);
		title.setStyle("-fx-font: 22 aharoni;");
		title.setFill(Color.BLACK);
		title.setStroke(Color.WHITE);

		depIndexToChange = Character.getNumericValue(selectedDepartment.charAt(18)) - 1;

		HBox radioHb = new HBox();
		HBox checkBoxHb = new HBox();
		VBox vb = new VBox();

		checkBoxHb.setPadding(new Insets(20, 16, 20, 16));
		checkBoxHb.setSpacing(10);
		radioHb.setPadding(new Insets(20, 16, 20, 16));
		radioHb.setSpacing(10);

		for (int i = 0; i < 375; i++) {
			Line l = new Line(0, i, 640, i);
			l.setStroke(Color.rgb(i / 2, 255, i / 3));
			root.getChildren().add(l);
		}

		vb.setTranslateX(150);
		vb.setTranslateY(70);
		radioHb.setTranslateX(20);

		depGroup = new ToggleGroup();

		RadioButton rbEarlier = new RadioButton("Earlier");
		rbEarlier.setToggleGroup(depGroup);

		RadioButton rbLater = new RadioButton("Later");
		rbLater.setToggleGroup(depGroup);

		RadioButton rbSame = new RadioButton("Same");
		rbSame.setToggleGroup(depGroup);

		RadioButton rbHome = new RadioButton("Home");
		rbHome.setToggleGroup(depGroup);
		rbHome.setSelected(true);
		Text txtEarlier = new Text("How much earlier?");
		Text txtLater = new Text("How much later?");

		laterDepHourDelta = new Spinner<>(1, 16, 1);
		earlyDepHourDelta = new Spinner<>(1, 8, 1);

		HBox early = new HBox();
		HBox later = new HBox();

		early.getChildren().addAll(txtEarlier, earlyDepHourDelta);
		later.getChildren().addAll(txtLater, laterDepHourDelta);

		rbEarlier.selectedProperty()
				.addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
					if (new_val == true)
						vb.getChildren().addAll(early);
					else {
						vb.getChildren().removeAll(early);
					}
				});

		rbLater.selectedProperty()
				.addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
					if (new_val == true)
						vb.getChildren().addAll(later);
					else {
						vb.getChildren().removeAll(later);
					}
				});

		cbDepChangeable = new CheckBox("Preference changing allowed?");
		cbDepSynchronizable = new CheckBox("Synchronized roles?");

		checkBoxHb.getChildren().addAll(cbDepChangeable, cbDepSynchronizable);
		radioHb.getChildren().addAll(rbEarlier, rbLater, rbSame, rbHome);
		vb.getChildren().addAll(checkBoxHb, radioHb);

		root.getChildren().addAll(title, editButton, vb);

		insertEntitiesScene = new Scene(root, 640, 375);
		insertEntitiesStage.setScene(insertEntitiesScene);
		insertEntitiesStage.show();

	}

	public void prefRoleEditorWindow(String selectedRole) {
		insertEntitiesStage.setTitle("Preference editor");
		Group root = new Group();

		editRoleButton = new Button("Save");
		editRoleButton.setPrefSize(250, 42);
		editRoleButton.setTranslateX(200);
		editRoleButton.setTranslateY(270);

		Text title = new Text("Preference editor for " + selectedRole);
		title.setTranslateX(0);
		title.setTranslateY(35);
		title.setStyle("-fx-font: 22 aharoni;");
		title.setFill(Color.BLACK);
		title.setStroke(Color.WHITE);

		roleIndexToChange = Character.getNumericValue(selectedRole.charAt(12)) - 1;
		depIndexToChange = Character.getNumericValue(selectedRole.charAt(36)) - 1;

		HBox radioHb = new HBox();
		HBox checkBoxHb = new HBox();
		VBox vb = new VBox();

		checkBoxHb.setPadding(new Insets(20, 16, 20, 16));
		checkBoxHb.setSpacing(10);
		radioHb.setPadding(new Insets(20, 16, 20, 16));
		radioHb.setSpacing(10);

		for (int i = 0; i < 375; i++) {
			Line l = new Line(0, i, 640, i);
			l.setStroke(Color.rgb(i / 2, 255, i / 3));
			root.getChildren().add(l);
		}

		vb.setTranslateX(150);
		vb.setTranslateY(70);
		radioHb.setTranslateX(20);

		roleGroup = new ToggleGroup();

		RadioButton rbEarlier = new RadioButton("Earlier");
		rbEarlier.setToggleGroup(roleGroup);

		RadioButton rbLater = new RadioButton("Later");
		rbLater.setToggleGroup(roleGroup);

		RadioButton rbSame = new RadioButton("Same");
		rbSame.setToggleGroup(roleGroup);

		RadioButton rbHome = new RadioButton("Home");
		rbHome.setToggleGroup(roleGroup);

		rbHome.setSelected(true);
		Text txtEarlier = new Text("How much earlier?");
		Text txtLater = new Text("How much later?");

		laterRoleHourDelta = new Spinner<>(1, 16, 1);
		earlyRoleHourDelta = new Spinner<>(1, 8, 1);

		HBox early = new HBox();
		HBox later = new HBox();

		early.getChildren().addAll(txtEarlier, earlyRoleHourDelta);
		later.getChildren().addAll(txtLater, laterRoleHourDelta);

		rbEarlier.selectedProperty()
				.addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
					if (new_val == true)
						vb.getChildren().addAll(early);
					else {
						vb.getChildren().removeAll(early);
					}
				});

		rbLater.selectedProperty()
				.addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
					if (new_val == true)
						vb.getChildren().addAll(later);
					else {
						vb.getChildren().removeAll(later);
					}
				});

		cbRoleChangeable = new CheckBox("Preference changing allowed?");
		cbRoleSynchronizable = new CheckBox("Synchronized Employees?");

		checkBoxHb.getChildren().addAll(cbRoleChangeable, cbRoleSynchronizable);
		radioHb.getChildren().addAll(rbEarlier, rbLater, rbSame, rbHome);
		vb.getChildren().addAll(checkBoxHb, radioHb);

		root.getChildren().addAll(title, editRoleButton, vb);

		insertEntitiesScene = new Scene(root, 640, 375);
		insertEntitiesStage.setScene(insertEntitiesScene);
		insertEntitiesStage.show();

	}

	public void alertCompanyNull(String s) {

		Alert a = new Alert(AlertType.NONE);
		a.setAlertType(AlertType.ERROR);
		a.setTitle("FAILED");
		a.setHeaderText("Something went wrong.");
		a.setContentText("Please create a company before trying to " + s);

		// show the dialog
		a.show();
	}

	public void alertEditedSuccessfuly() {
		Alert a = new Alert(AlertType.NONE);
		a.setAlertType(AlertType.INFORMATION);
		a.setTitle("SUCCESS");
		a.setHeaderText("Good news.");
		a.setContentText("Preference edited successfuly.");
		a.show();
	}

	public void showProfit(int res) {
		Alert a = new Alert(AlertType.NONE);
		a.setAlertType(AlertType.INFORMATION);
		a.setTitle("WIN");
		a.setHeaderText("Good news.");
		a.setContentText("The company is more efficient by " + res + " emp/hour.");
		a.show();
	}

	public void showLoss(int res) {
		Alert a = new Alert(AlertType.NONE);
		a.setAlertType(AlertType.INFORMATION);
		a.setTitle("LOSS");
		a.setHeaderText("Bad news.");
		a.setContentText("The company is less efficient by " + res + " emp/hour.");
		a.show();
	}

	public void showNoChange() {
		Alert a = new Alert(AlertType.NONE);
		a.setAlertType(AlertType.INFORMATION);
		a.setTitle("Same same");
		a.setHeaderText("The new state is the same as before the experiment (0 emp/hour efficiency).");
		a.show();
	}

	public boolean askBeforeInsertion() {
		Alert a = new Alert(AlertType.NONE);
		a.setAlertType(AlertType.CONFIRMATION);
		a.setTitle("Please choose an option");
		a.setHeaderText("Hold up!");
		a.setContentText("It seems you already have a company loaded, would you like to create a new company?");

		Optional<ButtonType> option = a.showAndWait();

		if (!option.isPresent()) {
			return false;
		} else if (option.get() == ButtonType.OK) {
			InsertEntitiesPopup();
			return true;
		} else if (option.get() == ButtonType.CANCEL) {
			a.close();
			return false;
		}
		return false;

	}
	// SET ON ACTIONS

	public void addEventToInsertEntities(EventHandler<ActionEvent> event) {
		menuButtons[0].setOnAction(event);
	}

	public void addEventToShowEntities(EventHandler<ActionEvent> event) {
		menuButtons[1].setOnAction(event);
	}

	public void addEventToShowChangeRolePref(EventHandler<ActionEvent> event) {
		menuButtons[2].setOnAction(event);
	}

	public void addEventToShowChangeDepPref(EventHandler<ActionEvent> event) {
		menuButtons[3].setOnAction(event);
	}

	public void addEventToShowExperimentResults(EventHandler<ActionEvent> event) {
		menuButtons[4].setOnAction(event);
	}

	public void addEventToAddDepartment(EventHandler<ActionEvent> event) {
		addDepartment.setOnAction(event);
	}

	public void addEventToAddRole(EventHandler<ActionEvent> event) {
		addRole.setOnAction(event);
	}

	public void addEventToAddEmployee(EventHandler<ActionEvent> event) {
		addEmployee.setOnAction(event);
	}

	public void addEventToSubmitEmployee(EventHandler<ActionEvent> event) {
		submitEmployee.setOnAction(event);
	}

	public void addEventToAfterSubmitButtons(EventHandler<ActionEvent> e1, EventHandler<ActionEvent> e2,
			EventHandler<ActionEvent> e3, EventHandler<ActionEvent> e4) {
		addAnotherEmpButton.setOnAction(e1);
		addNewRoleButton.setOnAction(e2);
		addNewDepButton.setOnAction(e3);
		finishButton.setOnAction(e4);
	}

	public void addEventToNextStepButtonDepPref(EventHandler<ActionEvent> event) {
		nextStepDepPref.setOnAction(event);
	}

	public void addEventToNextStepButtonRolePref(EventHandler<ActionEvent> event) {
		nextStepRolePref.setOnAction(event);
	}

	public void addEventToEditButton(EventHandler<ActionEvent> event) {
		editButton.setOnAction(event);
	}

	public void addEventToEditRoleButton(EventHandler<ActionEvent> event) {
		editRoleButton.setOnAction(event);
	}

	// GETTERS
	public Group getGroup() {
		return companyWindow;
	}

	public ArrayList<RadioButton> getDepPrefArray() {
		return depPrefArray;
	}

	public ArrayList<RadioButton> getRolePrefArray() {
		return rolePrefArray;
	}

	public String getCompanyNametf() {
		return cmpNametf.getText();
	}

	public Stage getInsertEntStage() {
		return insertEntitiesStage;
	}

	public Scene getInsertEntScene() {
		return insertEntitiesScene;
	}

	public Scene getDepScene() {
		return depScene;
	}

	public Scene getRoleScene() {
		return roleScene;
	}

	public Scene getEmpScene() {
		return empScene;
	}

	public RadioButton getSelectedButton() {
		return (RadioButton) group.getSelectedToggle();
	}

	public RadioButton getSelectedDepButton() {
		return (RadioButton) depGroup.getSelectedToggle();
	}

	public RadioButton getSelectedRoleButton() {
		return (RadioButton) roleGroup.getSelectedToggle();
	}

	public RadioButton getSelectedEmployeeType() {
		return (RadioButton) employeeType.getSelectedToggle();
	}

	public boolean getIsSynchronized() {
		return cbSynchronizable.isSelected();
	}

	public boolean getIsChangeable() {
		return cbChangeable.isSelected();
	}

	public boolean getDepIsSynchronized() {
		return cbDepSynchronizable.isSelected();
	}

	public boolean getDepIsChangeable() {
		return cbDepChangeable.isSelected();
	}

	public boolean getRoleIsSynchronized() {
		return cbRoleSynchronizable.isSelected();
	}

	public boolean getRoleIsChangeable() {
		return cbRoleChangeable.isSelected();
	}

	public int getEarlyHourDelta() {
		return earlyHourDelta.getValue();
	}

	public int getLaterHourDelta() {
		return laterHourDelta.getValue();
	}

	public int getDepEarlyHourDelta() {
		return earlyDepHourDelta.getValue();
	}

	public int getDepLaterHourDelta() {
		return laterDepHourDelta.getValue();
	}

	public int getRoleEarlyHourDelta() {
		return earlyRoleHourDelta.getValue();
	}

	public int getRoleLaterHourDelta() {
		return laterRoleHourDelta.getValue();
	}

	public Spinner<Integer> getPerHour() {
		return perHour;
	}

	public Spinner<Integer> getSalesLastMonth() {
		return salesLastMonth;
	}

	public Spinner<Integer> getBaseSalary() {
		return baseSalary;
	}

	public int getDepIndexToChange() {
		return depIndexToChange;
	}

	public int getRoleIndexToChange() {
		return roleIndexToChange;
	}

}