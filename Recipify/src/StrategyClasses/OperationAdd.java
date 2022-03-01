package StrategyClasses;

import Classes.Dish;
import Classes.Ingredient;
import Interfaces.Strategy;

public class OperationAdd implements Strategy {

	@Override
	public void doOperation(Dish theDish, Ingredient theIngredient) {
		theDish.getIngredients().add(theIngredient);
		
		//add queries 
	}

	
}
