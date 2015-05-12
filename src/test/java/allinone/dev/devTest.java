package allinone.dev;

import org.testng.annotations.Test;

import allinone.model.LoggerIf;

public class devTest implements LoggerIf {

	@Test
	public void testIf() {

		log.info("Log infocheck.");

	}

}
