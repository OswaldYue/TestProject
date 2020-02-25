package java.lang;

public class StringSS {

    // 在类加载的链接阶段就已经存在了 只是给了默认值0
    // 后续在初始化阶段会正确为其赋值1
    private static int i = 1;

    private int x;

    static {

        System.out.println("自定义java.lang.String初始化");
    }

    public StringSS() {
        x=10;
    }

    public int getValue() {
        return 1;
    }
}
