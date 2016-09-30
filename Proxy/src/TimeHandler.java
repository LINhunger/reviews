
import java.lang.reflect.Method;
/**
 * 代理实例的调用处理程序的实现类，加强被代理对象
 *
 */
public class TimeHandler implements InvocationHandler {

	//被代理的对象
	private Object target;
	//构造方法接收被代理对象
	public TimeHandler(Object target) {
		super();
		this.target = target;
	}

	/**
	 * 实现接口方法
	 */
	@Override
	public void invoke(Object proxy, Method m) {
		
		try {
			long starttime = System.currentTimeMillis();
			System.out.println("汽车开始行驶....");
			m.invoke(target);
			long endtime = System.currentTimeMillis();
			System.out.println("汽车结束行驶....  汽车行驶时间："
							+ (endtime - starttime) + "毫秒！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
