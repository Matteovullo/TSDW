public class Main{
    public static void main(String[] args) throws Exception{
        Th T1=new Th("T1");
        Th T2=new Th("T2");
        Th T3=new Th("T3");

        T1.start();
        T2.start();
        T3.start();

        T1.join();
        T2.join();
        T3.join();
    }
}