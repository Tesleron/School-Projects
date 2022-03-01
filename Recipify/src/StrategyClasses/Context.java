package StrategyClasses;

import Interfaces.Strategy;
import Classes.*;

public class Context {
	   private Strategy strategy;

	   public Context(Strategy strategy){
	      this.strategy = strategy;
	   }

	   public void executeStrategy(Dish d, Ingredient ing){
	      strategy.doOperation(d, ing);
	   }
	}
