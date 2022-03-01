package StrategyClasses;

import Classes.Dish;
import Classes.Ingredient;
import Interfaces.Strategy;

public class OperationRemove implements Strategy{

	@Override
	public void doOperation(Dish theDish, Ingredient theIngredient) {
		theDish.getIngredients().remove(theIngredient);	
		
		//add queries
	}

}
