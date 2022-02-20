import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class huiwen {

    public boolean isPalindrome(String s) {
        //去除数字和不必要的符号
        String cleanStr = _cleanString(s);

        return true;
    }

    private String _cleanString(String s) {
        return "";
    }


}



//https://www.liaoxuefeng.com/wiki/1252599548343744/1264804593397984
//代理模式 https://cloud.tencent.com/developer/article/1461796
class Main {
    public static void main(String[] args) {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method);
                if (method.getName().equals("morning")) {
                    System.out.println("Good morning, " + args[0]);
                }
                return null;
            }
        };
        Hello hello = (Hello) Proxy.newProxyInstance(
                Hello.class.getClassLoader(), // 传入ClassLoader
                new Class[] { Hello.class , Hello2.class}, // 传入要实现的接口
                handler); // 传入处理调用方法的InvocationHandler
        hello.morning("Bob");
    }
}
interface Hello2 {
    void morning2(String name);
}
interface Hello {
    void morning(String name);
}

/**
 *
public class HelloDynamicProxy implements Hello {
    InvocationHandler handler;
    public HelloDynamicProxy(InvocationHandler handler) {
        this.handler = handler;
    }
    public void morning(String name) {
        handler.invoke(
                this,
                Hello.class.getMethod("morning", String.class),
                new Object[] { name });
    }
}

 */
