module Recipify {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.web;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	requires java.desktop;
	
	
	opens application to javafx.graphics, javafx.fxml;
}
