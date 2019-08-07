package examples;

public class IntegerUsage {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = Integer.valueOf(1);
        Integer c = new Integer(1);
        int d = 1;
        Integer b1 = Integer.valueOf(1);
        Integer c1 = new Integer(1);

        Integer a2 = 1000;
        Integer a3 = 1000;

        Integer b2 = Integer.valueOf(1000);
        Integer b3 = Integer.valueOf(1000);
        System.out.println("a == b: "+String.valueOf(a==b));
        System.out.println("a == c: "+String.valueOf(a==c));
        System.out.println("b == c: "+String.valueOf(b==c));

        System.out.println("a == d: "+String.valueOf(a==d));
        System.out.println("b == d: "+String.valueOf(b==d));
        System.out.println("c == d: "+String.valueOf(c==d));

        System.out.println("b == b1: "+String.valueOf(b==b1));
        System.out.println("c == c1: "+String.valueOf(c==c1));
        System.out.println("a2 == a3: "+String.valueOf(a2==a3));
        System.out.println("b2 == b3: "+String.valueOf(b2==b3));
    }
}
