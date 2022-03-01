package Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import Model.Model;
import View.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class Controller implements Controllable{
	private Model theModel;
	private View theView;

	public Controller(Model model, View view) {
		theModel = model;
		theView = view;

		// EVENT HANDLERS

		try {
			theModel.loadCompany();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		EventHandler<ActionEvent> addAnotherEmpButtonEvent = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				view.getInsertEntStage().setScene(view.getEmpScene());
			}

		};

		EventHandler<ActionEvent> addNewRoleButtonEvent = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				view.getInsertEntStage().setScene(view.getRoleScene());
			}

		};

		EventHandler<ActionEvent> addNewDepButtonEvent = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				view.getInsertEntStage().setScene(view.getDepScene());

			}

		};

		EventHandler<ActionEvent> finishButtonEvent = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				try {
					theModel.saveToFile();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				view.getInsertEntStage().close();// return to main menu
			}
		};

		EventHandler<ActionEvent> submitEmployeeButtonEvent = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				sendEmployeeData(view.getSelectedButton().getText().toUpperCase(), /* view.getIsChangeable(), */
						view.getSelectedButton().getText().toUpperCase().equals("LATER") ? view.getLaterHourDelta()
								: view.getEarlyHourDelta(),
						view.getSelectedEmployeeType().getText().toUpperCase(), view.getBaseSalary().getValue(),
						view.getSalesLastMonth().getValue(), view.getPerHour().getValue());

				view.afterSubmitEmpWindow();
				view.addEventToAfterSubmitButtons(addAnotherEmpButtonEvent, addNewRoleButtonEvent, addNewDepButtonEvent,
						finishButtonEvent);

			}

		};

		EventHandler<ActionEvent> addEmployeeButtonEvent = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sendRoleData(view.getSelectedRoleButton().getText().toUpperCase(), view.getRoleIsChangeable(),
						view.getRoleIsSynchronized(),
						view.getSelectedRoleButton().getText().toUpperCase().equals("LATER")
								? view.getRoleLaterHourDelta()
								: view.getRoleEarlyHourDelta());
				view.addEmployeeWindow();
				view.addEventToSubmitEmployee(submitEmployeeButtonEvent);

			}

		};
		EventHandler<ActionEvent> addRoleButtonEvent = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				sendDepartmentData(view.getSelectedDepButton().getText().toUpperCase(), view.getDepIsChangeable(),
						view.getDepIsSynchronized(),
						view.getSelectedDepButton().getText().toUpperCase().equals("LATER")
								? view.getDepLaterHourDelta()
								: view.getDepEarlyHourDelta());
				view.addRoleWindow();
				view.addEventToAddEmployee(addEmployeeButtonEvent);
			}

		};

		EventHandler<ActionEvent> addDepartmentButtonEvent = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				sendCompanyData(view.getCompanyNametf());
				view.addDepartmentWindow();
				view.addEventToAddRole(addRoleButtonEvent);
			}

		};

		EventHandler<ActionEvent> InsertEntitiesHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (theModel.getCompany() != null) {
					if (view.askBeforeInsertion())
						view.addEventToAddDepartment(addDepartmentButtonEvent);
				} else {
					view.InsertEntitiesPopup();
					view.addEventToAddDepartment(addDepartmentButtonEvent);
				}
			}

		};
		view.addEventToInsertEntities(InsertEntitiesHandler);

		EventHandler<ActionEvent> showEntitiesHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (theModel.getCompany() != null)
					view.showEntitiesPopup(theModel.getCompany());
				else {
					view.alertCompanyNull("show entites.");
				}

			}

		};
		view.addEventToShowEntities(showEntitiesHandler);

		EventHandler<ActionEvent> editButtonHandlerDep = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				updateDepData(view.getDepIndexToChange(), view.getSelectedDepButton().getText().toUpperCase(),
						view.getDepIsChangeable(), view.getDepIsSynchronized(),
						view.getSelectedDepButton().getText().toUpperCase().equals("LATER")
								? view.getDepLaterHourDelta()
								: view.getDepEarlyHourDelta());

				try {
					theModel.saveToFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		};

		EventHandler<ActionEvent> editButtonHandlerRole = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				updateRoleData(view.getDepIndexToChange(), view.getSelectedRoleButton().getText().toUpperCase(),
						view.getRoleIsChangeable(), view.getRoleIsSynchronized(),
						view.getSelectedRoleButton().getText().toUpperCase().equals("LATER")
								? view.getRoleLaterHourDelta()
								: view.getRoleEarlyHourDelta(),
						view.getRoleIndexToChange());

				try {
					theModel.saveToFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		};

		EventHandler<ActionEvent> nextStepDepPrefHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
//				System.out.println("Clicked on next step");
				view.prefEditorWindow(view.getSelectedDepButton().getText());
				view.addEventToEditButton(editButtonHandlerDep);

			}

		};

		EventHandler<ActionEvent> nextStepRolePrefHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
//				System.out.println("Clicked on next step");
				view.prefRoleEditorWindow(view.getSelectedRoleButton().getText());
				view.addEventToEditRoleButton(editButtonHandlerRole);

			}

		};

		EventHandler<ActionEvent> showChangeDepPref = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (theModel.getCompany() != null) {
					view.showChangeDepPref(theModel.getCompany());
					if (theView.getDepPrefArray().size() > 0)
						view.addEventToNextStepButtonDepPref(nextStepDepPrefHandler);
				} else {
					view.alertCompanyNull("change department preference.");
				}
			}

		};
		view.addEventToShowChangeDepPref(showChangeDepPref);
		EventHandler<ActionEvent> showChangeRolePref = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (theModel.getCompany() != null) {
					view.showChangeRolePref(theModel.getCompany());
					if (theView.getRolePrefArray().size() > 0)
						view.addEventToNextStepButtonRolePref(nextStepRolePrefHandler);
				} else {
					view.alertCompanyNull("change role preference.");

				}
			}

		};
		view.addEventToShowChangeRolePref(showChangeRolePref);

		EventHandler<ActionEvent> showResultsHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (theModel.getCompany() == null) {
					view.alertCompanyNull("show experiment results.");
					return;
				}
				int result = getWinOrLoss();
				if (result > 0)
					view.showProfit(result);
				else if (result < 0){
					view.showLoss(result);
				}
				else {
					view.showNoChange();
				}

			}

		};
		view.addEventToShowExperimentResults(showResultsHandler);

	}
	
	@Override
	public void sendCompanyData(String cmpName) {
		if (theModel.createCompany(cmpName)) {
		}

	}
	
	@Override
	public void sendDepartmentData(String pref, boolean changeable, boolean synchronizable, int hourDelta) {
		if (theModel.createDepartment(pref, changeable, synchronizable, hourDelta))
			;

	}
	
	@Override
	public void sendRoleData(String pref, boolean changeable, boolean synchronizable, int hourDelta) {
		if (theModel.createRole(pref, changeable, synchronizable, hourDelta))
			;

	}
	
	@Override
	public void sendEmployeeData(String pref, int hourDelta, String employeeType, int baseSalary, int sales,
			int hourly) {
		if (theModel.createEmployee(pref, hourDelta, employeeType, baseSalary, sales, hourly))
			;

	}
	
	@Override
	public void updateDepData(int depIndex, String pref, boolean changeable, boolean synchronizable, int hourDelta) {
		theModel.updateDepData(depIndex, pref, changeable, synchronizable, hourDelta);
		theView.alertEditedSuccessfuly();
		theView.getInsertEntStage().close();
	}
	
	@Override
	public void updateRoleData(int depIndex, String pref, boolean changeable, boolean synchronizable, int hourDelta,
			int roleIndex) {
		theModel.updateRoleData(depIndex, pref, changeable, synchronizable, hourDelta, roleIndex);
		theView.alertEditedSuccessfuly();
		theView.getInsertEntStage().close();
	}
	
	@Override
	public int getWinOrLoss() {
		return theModel.getCompany().calcEfficieny();
	}

}
