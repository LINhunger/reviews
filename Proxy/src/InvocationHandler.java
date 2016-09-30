

import java.lang.reflect.Method;

/**
 * 代理实例的调用处理程序实现的接口。
 * 每个代理实例都具有一个关联的调用处理程序。
 * 对代理实例调用方法时，将对方法调用进行编码并将其指派到它的调用处理程序的 invoke 方法。 
 */
public interface InvocationHandler {

	/**
	 * 在代理实例上处理方法。在与方法关联的代理实例上调用方法时，将在调用处理程序上调用此方法。
	 * @param proxy 在其上调用方法的代理实例。
	 * @param method 对应于在代理实例上调用的接口方法的 Method 实例。
	 * Method 对象的声明类将是在其中声明方法的接口，该接口可以是代理类赖以继承方法的代理接口的超接口。
	 */
	public void invoke(Object proxy,Method method);
}
