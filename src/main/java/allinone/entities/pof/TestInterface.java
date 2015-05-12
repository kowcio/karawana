package allinone.entities.pof;

public interface TestInterface {

	String getRandomString();

	String getRandomString(String prefix);

	String getRandomString(String prefix, String suffix);

	String getRandomString(String prefix, int length, String suffix);
}
