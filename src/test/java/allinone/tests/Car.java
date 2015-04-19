package allinone.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Car implements CarIf {
	
	String name;
	
    public static Predicate<Car> i  = (s)-> s.name.length() > 5;
    
    
	@Override
	public String getWheels(Car car) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Boolean> test(List<Car> cars){
		
		printCarsWithPredicate(new ArrayList<Car>() , (car)->car!=null);
		
		return cars.stream().map(car -> check(car)).collect(Collectors.toList());
		
	}
	
	public List<Boolean> bi(List<Car> cars){
		
		return cars.stream()
				.filter(c->i.test(c))
				.filter(CarIf::checkStatic)
				.map(car-> 
		checkWith(car,true)).collect(Collectors.toList());
		
	}

	public static void printCarsWithPredicate(
		    List<Car> roster, Predicate<Car> tester) {
		    for (Car p : roster) {
		        if (tester.test(p)) {
		            p.toString();
		        }
		    }
		}

}
