package allinone.entities.pof;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import lombok.Data;

@Data
public class ProffOfConceptClassFieldTestInterfaceImpl implements TestInterface {

	String	      TestField;
	List<String>	testList	= new ArrayList<String>();

	TestInterface	testIfField;

	@Test
	public void POFInConsole() {
		testIfField = new ProffOfConceptClassFieldTestInterfaceImpl();
	}

	@Override
	public String getRandomString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRansomString(String prefix) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRansomString(String prefix, String suffix) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRansomString(String prefix, int length, String suffix) {
		// TODO Auto-generated method stub
		return null;
	}

}
