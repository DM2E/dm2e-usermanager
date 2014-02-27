	import java.net.URL;


public class Test {

	public Test(){
		URL classesRootDir = getClass().getProtectionDomain().getCodeSource().getLocation();
		System.out.println(classesRootDir);
	}
	
	
	public static void main(String[] args) {
		
		new Test();

	}

}
