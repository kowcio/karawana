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
		return null;
	}

	@Override
	public String getRandomString(String prefix) {
		return null;
	}

	@Override
	public String getRandomString(String prefix, int length, String suffix) {
		return null;
	}

    @Override
    public String getRandomString(String prefix, String suffix) {
        return null;
    }

}
