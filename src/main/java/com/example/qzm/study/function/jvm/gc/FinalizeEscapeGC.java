package com.example.qzm.study.function.jvm.gc;

/**
 * @ClassName FinalizeEscapeGC
 * @Description 1.对象可以在gc时自我拯救。2.自救的机会只有一次finalize()方法只会被系统调用一次
 * @Version 1.0
 **/
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC save_hook = null;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("execute finalize....");
        FinalizeEscapeGC.save_hook = this;
    }

    public static void main(String[] args) throws Throwable {
        save_hook = new FinalizeEscapeGC();
        save_hook = null;
        System.gc();
        Thread.sleep(500);
        //第一次可以自救成功
        if (save_hook != null) {
            System.out.println("alive...");
        } else {
            System.out.println("dead...");
        }

        save_hook = null;
        System.gc();
        Thread.sleep(500);
        //第二次失败
        if (save_hook != null) {
            System.out.println("alive...");
        } else {
            System.out.println("dead...");
        }
    }
}
