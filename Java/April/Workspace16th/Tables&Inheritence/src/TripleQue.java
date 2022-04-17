import java.util.*;

public class TripleQue<T> extends AbstractList<T> {
    protected PhilsArrayDeque<T> right ; 
    protected PhilsArrayDeque<T> left ; 

    public TripleQue(Class<T> t){
        right = new PhilsArrayDeque<T>(t);
        left = new PhilsArrayDeque<T>(t);

    }

    public int size(){
        return right.size() + left.size();
    }

    public T get(int i){
        if ( i< right.size()) return right.get(i);
        else return left.get(i- right.size());
    }

    public T set(int i, T x ){ 
        if ( i<right.size()) return right.set(i, x);
        else return left.set(i- right.size(),x);
    }

    public void add (int i, T x) {
        if ( i< right.size())right.add(i,x);
        else left.add(i - right.size(),x);
        reBalance();
    }

    public T remove(int i){ 
        T x;
        if(i<right.size()) x = right.remove(i);
        else x = left.remove(i - right.size());
        reBalance();
        return x; 
    }

    protected void reBalance(){ 
        if (right.size()==left.size() +2 ){
            left.add(0,right.remove(right.size()-1));
        }else if (left.size()==right.size()+2){
            right.add(right.size(),left.remove(0));
        }
    }

    public void clear(){
         right.clear();
         left.clear();
    }
    public static void main(String[] args) {
        List<Integer> tr = new TripleQue<Integer>(Integer.class);
        int K = 1000000;
        StopWatch s = new StopWatch();
        System.out.print("Appending " + K + " items...");
        System.out.flush();
        s.start();
        for (int i = 0; i < K; i++) {
            tr.add(i);
        }
        s.stop();
        System.out.println("done (" + s.elapsedSeconds() + "s)");

        System.out.print("Prepending " + K + " items...");
        System.out.flush();
        s.start();
        for (int i = 0; i < K; i++) {
            tr.add(0, i);
        }
        s.stop();
        System.out.println("done (" + s.elapsedSeconds() + "s)");

        System.out.print("Midpending(?!) " + K + " items...");
        System.out.flush();
        s.start();
        for (int i = 0; i < K; i++) {
            tr.add(tr.size() / 2, i);
        }
        s.stop();
        System.out.println("done (" + s.elapsedSeconds() + "s)");


        System.out.print("Removing " + K + " items from the left...");
        System.out.flush();
        s.start();
        for (int i = 0; i < K; i++) {
            tr.remove(tr.size() - 1);
        }
        s.stop();
        System.out.println("done (" + s.elapsedSeconds() + "s)");

        System.out.print("Removing " + K + " items from the right...");
        System.out.flush();
        s.start();
        for (int i = 0; i < K; i++) {
            tr.remove(0);
        }
        s.stop();
        System.out.println("done (" + s.elapsedSeconds() + "s)");

        System.out.print("Removing " + K + " items from the middle...");
        System.out.flush();
        s.start();
        for (int i = 0; i < K; i++) {
            tr.remove(tr.size() / 2);
        }
        s.stop();
        System.out.println("done (" + s.elapsedSeconds() + "s)");
        
    }

    
}
