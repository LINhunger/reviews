
import java.util.Random;

/**
 * 需要被代理的类
 *
 */
public class Car implements Moveable {

	@Override
	public void move() {
		// 实现接口方法
		try {
			Thread.sleep(new Random().nextInt(1000));
			System.out.println("汽车行驶中....");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
