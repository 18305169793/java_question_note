package examples;

public class InnerClassExample {
    interface Example{
        void sayEndInit();
    }
    private void sayHello(){
        System.out.println("hello");
    }
    class Contents{
        private int i = 11;
        public int value(){
            return i;
        }
    }
    class Destination implements Example{
        private String label;
        Destination(String s){
            label = s;
            //内部类可以调用外部类任何实例区域，内部类拥有外部类一切元素的访问权
            sayHello();
            sayEndInit();
        }
        String readLabel(){
            return label;
        }

        public void sayEndInit() {
            System.out.println("init ending");
        }
        InnerClassExample returnOuter(){
            return InnerClassExample.this;
        }
    }
    public Destination to(String s){
        return new Destination(s);
    }

    public Contents contents(){
        return new Contents();
    }
    public void ship(String dest){
        Contents c =contents();
        Destination d = to(dest);
        System.out.println(d.readLabel());
    }
    public static void main(String[] args) {
        InnerClassExample i1 = new InnerClassExample();
        i1.ship("tasmania");
        //两种实例化内部类方式
        //在外部类的任意非静态方法之外的区域都需要用如下两种方式之一去实例化
        InnerClassExample i2 = new InnerClassExample();
        //方式1
        InnerClassExample.Destination d1 = i2.new Destination("Nan King");
        //方式2
        InnerClassExample.Destination d2 = i2.to("d2");
        //获取外围类对象
        InnerClassExample outer = d1.returnOuter();
        System.out.println(outer == i2 );
    }
}
