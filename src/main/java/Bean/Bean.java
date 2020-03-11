package Bean;

import Pointcut.Target;

public class Bean {
	public void method() throws RuntimeException{
		
	}
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		System.out.println(Target.class.getMethod("minus", int.class, int.class));
	}
}
