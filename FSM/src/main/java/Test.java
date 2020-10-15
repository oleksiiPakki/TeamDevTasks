public class Test  {
    protected Integer some = 4;

    public void some(){
        System.out.println(some);
    }

    public static void main(String[] args) {
        A a = new A();
        a.someA();

        Test test = new Test();
        test.some();
    }
}

class A extends Test{
    public void someA(){
        some = 5;
    }
}
