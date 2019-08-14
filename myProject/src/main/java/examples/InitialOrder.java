package examples;

class Window {
    Window(int marker) {
        System.out.println("window(" + marker + ")");
    }
}

class House {
    int a;

    Window w1 = new Window(1);
    { a=1;
        System.out.println("a: "+a);
    }
    public House() {
        System.out.println("in initialOrder");
        Window w1 = new Window(33);
    }

    Window w2 = new Window(2);

    void f() {
        System.out.println("f()");
    }
    Window w3 = new Window(3);

}

public class InitialOrder {
    public static void main(String[] args) {
        House h =new House();
        h.f();
    }
}
