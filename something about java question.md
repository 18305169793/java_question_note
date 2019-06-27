# something about java question

1.  ##### why Comparator Interface with @FunctionalInterface can have two abstract function？
   
   >from docs
   >
   >If an interface declares an abstract method overriding one of the public methods of java.lang.Object,     that    also does not count toward the interface's abstract method count  since any implementation of the interface will have an implementation from  java.lang.Object or elsewhere.
   >如果接口声明了一个抽象方法覆盖的公共方法之一java.lang.Object ，也不会向接口的抽象方法计数统计以来的接口的任何实施都会有一个实现从java.lang.Object或其他地方。
   >如果接口声明了一个覆盖java.lang.Object的全局方法之一的抽象方法，那么它不会计入接口的抽象方法数量中，因为接口的任何实现都将具有java.lang.Object或其他地方的实现。

