package Handler;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;import com.sun.org.apache.bcel.internal.generic.RET;

public class UppercaseHandler implements InvocationHandler{
	Object target;
	
	public UppercaseHandler(Object target) {
		this.target = target;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		Object ret = method.invoke(target, args);
		if(ret instanceof String && method.getName().startsWith("say")) {
			return ((String)ret).toUpperCase();
		}else {
			return ret;
		}
	}

}
