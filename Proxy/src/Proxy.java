

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import org.apache.commons.io.FileUtils;
/**
 * 动态代理类
 *
 */
public class Proxy {
	/**
	 * 返回代理类的一个实例，返回的代理类可以被当做被代理类使用（可使用被代理类在接口中声明过的方法）
	 * @param infce 被代理类所实现接口的类类型
	 * @param h 代理实例的调用处理程序实现的接口。
	 * @return 代理类的一个实例
	 * @throws Exception
	 */
	public static Object newProxyInstance(Class infce,InvocationHandler h) throws Exception{
		//换行符
		String rt = "\r\n";
		//方法
		String methodStr = "";
		for(Method m : infce.getMethods()){
			methodStr +=
					//注解
					"	@Override" + rt +
					//方法名
					"	public void " + m.getName() + "() {" + rt +
					//try语句
					"  try{" + rt +
					//获取接口中的方法
					"  Method md = " + infce.getName() + ".class.getMethod(\"" 
										+ m.getName() + "\");" + rt +
					//执行invoke方法
					"  h.invoke(this,md);" +rt+ 
					//catch语句块
				"  }catch(Exception e){ e.printStackTrace();}" + rt +
			"	}" ;
		}
		
		String str =
		"import java.lang.reflect.Method;" + rt +
		"public class $Proxy0 implements " + infce.getName() + " {" + rt +
		"	public $Proxy0(InvocationHandler h) {" + rt +
		"		this.h = h;" + rt +
		"	}" + rt +
		"  private InvocationHandler h;" + rt+ 
		methodStr + rt +
		"}" ;
		//产生代理类的java文件
		String filename = System.getProperty("user.dir") +"/bin/$Proxy0.java";
		File file = new File(filename);
		FileUtils.writeStringToFile(file, str);
		
		//编译
		//拿到编译器
		JavaCompiler complier = ToolProvider.getSystemJavaCompiler();
		//文件管理者
		StandardJavaFileManager fileMgr = 
				complier.getStandardFileManager(null, null, null);
		//获取文件
		Iterable units = fileMgr.getJavaFileObjects(filename);
		//编译任务
		CompilationTask t = complier.getTask(null, fileMgr, null, null, null, units);
		//进行编译
		t.call();
		fileMgr.close();
		
		//load 到内存
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		Class c = cl.loadClass("$Proxy0");
		
		Constructor ctr = c.getConstructor(InvocationHandler.class);
		return ctr.newInstance(h);
	}

	
	
	
}
