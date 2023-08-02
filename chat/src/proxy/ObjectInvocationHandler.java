package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
public class ObjectInvocationHandler implements InvocationHandler {

	private Object targetObject1;
	private Object targetObject2;

	public ObjectInvocationHandler(Object targetObject1, Object targetObject2) {
		this.targetObject1 = targetObject1;
		this.targetObject2 = targetObject2;

	}

	@Override
	public Object invoke(Object object, Method method, Object[] args) throws Throwable {
		Object returnValue = null;
		if(method.getDeclaringClass().isAssignableFrom(targetObject1.getClass())){
			 returnValue = method.invoke(targetObject1, args);
			if(returnValue == null && targetObject2 != null) {
				 returnValue = method.invoke(targetObject2, args);
			}
		}else {
			returnValue = method.invoke(targetObject2, args);
		}
	
//		
//		if(method.getClass().isAnnotationPresent(cacheAnnotaion.class)){
//			 returnValue = method.invoke(targetObject1, args);
//			if(returnValue == null && targetObject2 != null) {
//				 returnValue = method.invoke(targetObject2, args);
//			}
//		}else {
//			returnValue = method.invoke(targetObject2, args);
//		}

	return returnValue;
	}

}
