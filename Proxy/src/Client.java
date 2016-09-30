

public class Client {

	/**
	 * 测试方法
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		//创建需要代理的对象。
		Car car = new Car();
		//创建一个代理实例的调用处理对象。
		InvocationHandler h = new TimeHandler(car);
		//调用代理类的静态方法，生成代理实例。
		Moveable m = (Moveable)Proxy.newProxyInstance(Moveable.class,h);
		m.move();
	}

}
