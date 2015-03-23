package allinone.entities.pof;

public interface TestInterface {

	String getRandomString();

	String getRansomString(String prefix);

	String getRansomString(String prefix, String suffix);

	String getRansomString(String prefix, int length, String suffix);
}
