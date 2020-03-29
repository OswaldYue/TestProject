package com.poplar.gc;

/**
 *
 * 垃圾回收测试
 */
public class GCTest1 {

    /*
    -verbose:gc 输出冗余的gc信息
    -Xms20M 堆初始化大最小容量
    -Xmx20M 堆初始化最大容量
    -Xmn10M 新生代容量
    -XX:+PrintGCDetails
    -XX:SurvivorRatio=8 配置新生代和survivor的大小比例为8：1：1
    以上配置的信息总体如下:
        堆总共大小20M,新生代10M，老年代10M,新生代的Enden区站8份，占8M，两个survivor分别占1份，各占1M
    */

    public static void main(String[] args) {
        int size = 1024 * 1024;
//        byte[] bytes0 = new byte[6 * size];
        System.out.println("hello world 000");
        byte[] bytes1 = new byte[2 * size];
        System.out.println("hello world 111");
        byte[] bytes2 = new byte[2 * size];
        System.out.println("hello world 222");
        byte[] bytes3 = new byte[3 * size];
        System.out.println("hello world 333");
        byte[] bytes4 = new byte[3 * size];
        //当需要分配内存的对象的大小超出了新生代的容量时，对象会被直接分配到老年代
        System.out.println("hello world 444");

        /*
         [GC(此次GC是Minor GC) (Allocation Failure)(表示发生GC的原因:分配失败) [PSYoungGen（PS表示收集器类型：Parallel Scavenge，发生在年轻代）: 8144K（收集前还在存活的对象占据的容量）->728K（收集后还在存活的对象占据的容量）(9216K)(新生代中总的空间容量，按照配置应该是10M啊为什么是9M？为什么？因为有一个1M的survivor不会使用)]
         8144K（此次GC之前堆中总存活对象的容量）->6872K（此次GC之后堆中总存活对象的容量）(19456K)（堆总的容量）, 0.0087417 secs（所用时间）] [Times: user（用户态收集所用时间）=0.02 sys=0.02系统态收集所用时间）, real=0.01 secs]
         说明:
            新生代释放的容量:8144-728=7416K 新生代剩余的容量:9216-728=8488K
            总堆释放的容量:8144-6872=2272K  总堆的剩余容量:19456-6872=12584K
            为何总堆释放的容量要小于新生代的容量?因为新生代的一部分被真正的回收了，另一部分到老年代了那部分是7416-2272=5144K
         [Full GC (Ergonomics) [PSYoungGen(Parallel Old垃圾收集器): 728K->0K(9216K)] [ParOldGen: 6144K->6774K(10240K)] 6872K->6774K(19456K), [Metaspace: 3217K->3217K(1056768K)], 0.0070323 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
         说明：
            新生代释放的容量:728-0=728K
            老年代释放的容量:6144-6774=-630K 增加了630K
            总堆释放的容量:6872-6672=200K
         hello world
         Heap
         PSYoungGen      total 9216K, used 2287K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
         eden space 8192K, 27% used [0x00000000ff600000,0x00000000ff83be00,0x00000000ffe00000)
         from space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
         to   space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
         ParOldGen       total 10240K, used 6774K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
         object space 10240K, 66% used [0x00000000fec00000,0x00000000ff29daa8,0x00000000ff600000)
         Metaspace       used 3225K, capacity 4496K, committed 4864K, reserved 1056768K
         class space    used 350K, capacity 388K, committed 512K, reserved 1048576K
         */
    }
}
