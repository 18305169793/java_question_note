package examples;
class Father{
    public void is(){
        System.out.println("father");
    }
}
class Son extends Father{
    public void is(){
        System.out.println("son");
    }
}
public class DynamicBind {
    public static void main(String[] args) {
        Father o1 = new Father();
        Father o2 = new Son();
        o1.is();
        o2.is();
        Object o3= o2;
        ((Father)o3).is();
    }
}
