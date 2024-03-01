public class Producer extends Thread{
    Share share;
    String name;

    public Producer(Share s, String n){
        share=s;
        name=n;
    }

    public void run(){
        while(true){
            if(name.equals("P1")){
                int tmp=share.m;
                if(share.m>=1 && share.m<=5){
                    share.change();
                    System.out.println("P1, m= "+tmp+", new m="+share.m);
                    share.n();
                }else if(share.m>=6 && share.m<=10){
                    try {
                        share.w();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }else if(name.equals("P2")){
                if(share.m>=6 && share.m<=10){
                    int tmp=share.m;
                    share.change();
                    System.out.println("P2, m= "+tmp+", new m="+share.m);
                    share.n();
                }else if(share.m>=1 && share.m<=5){
                    try {
                        share.w();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
