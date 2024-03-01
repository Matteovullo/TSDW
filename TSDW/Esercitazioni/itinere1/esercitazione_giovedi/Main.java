public class Main{
    public static void main(String[] args) throws Exception{
        Share share=new Share();
        Producer P1=new Producer(share, "P1");
        Producer P2=new Producer(share, "P2");

        P1.start();
        P2.start();

        P1.join();
        P2.join();
    }
}