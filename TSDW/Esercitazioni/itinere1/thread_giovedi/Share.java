import java.util.*;

public class Share {
    static int sample=50;
    Random rand=new Random();

    public synchronized void change(int i){
        sample=i;
    }
}
