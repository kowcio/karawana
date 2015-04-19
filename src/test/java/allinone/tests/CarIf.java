package allinone.tests;

import lombok.Builder;
import lombok.Data;

public interface CarIf {

	String getWheels(Car car);

	default CarDecorator getDecorator(Car car){
		return new CarDecorator(car);		
	}
	default boolean check(Car car){
		return car == null ? false : true;		
	}
	static boolean checkStatic(Car car){
		return car == null ? false : true;		
	}
	default boolean checkWith(Car car, Boolean test){
		return car == null ? false : true;		
	}
}
