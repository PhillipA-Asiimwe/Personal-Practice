import java.util.AbstractList;
import java.util.List;

public class RootArrayDeque<T> extends AbstractList<T> {
    private RootishArrayStack<T> front; 
    private RootishArrayStack<T> back ; 
    private Class<T> t ; 

    public RootArrayDeque(Class<T> r){
        front = new RootishArrayStack<>(r);
        back = new RootishArrayStack<>(r);
        t = r ;
    }

    public T get(int i){
        if(i< front.size()){ 
            return front.get(front.size()-i -1); 
        }else{ 
            return back.get(i - front.size()); 
    
        }
    }

    public T set(int i , T x) { 
        if (i<front.size()){
            return front.set(front.size()-i-1, x);
        }else{
            return back.set(i - front.size(), x);
        }
    }

    public void add(int i , T x){ 
        if(i < front.size()){
            front.add(front.size() - i , x); 
        }else { 
            back.add(i - front.size(),x);
        }
        balance();
    }

    public T remove(int i ){ 
        T x; 
        if( i < front.size()){ 
            x = front.remove(front.size()-i-1);
            balance();
            return x; 
        }else { 
            x = back.remove(i - front.size()); 
            balance(); 
            return x; 
        }
    }

    public void balance(){ 
        int n = size(); 
        if (3*front.size()<back.size()){
            int s = (int)Math.ceil(n/2 - front.size());
            RootishArrayStack<T> frontT = new RootishArrayStack<>(t); 
            RootishArrayStack<T> backT = new RootishArrayStack<>(t);
            frontT.addAll(back.subList(0,s)); 
            frontT.addAll(front);
            backT.addAll(back.subList(s,back.size()));
            front = frontT; 
            back = backT;
        }else if (3*back.size()<front.size()){
            int s = front.size() - n/2;
            RootishArrayStack<T> frontT = new RootishArrayStack<>(t);
            RootishArrayStack<T> backT = new RootishArrayStack<>(t);
            frontT.addAll(front.subList(s, front.size()));
            backT.addAll(front.subList(0, s));
            backT.addAll(back); 
            front = frontT; 
            back = backT;
        }
    }

    public int size(){ return front.size() + back.size();}

    public static void main(String[] args) {
		//List<Integer> rad = new ArrayDeque<Integer>(Integer.class);
		List<Integer> rad = new RootArrayDeque<Integer>(Integer.class);
		int K = 1000000;
		StopWatch s = new StopWatch();
		System.out.print("Appending " + K + " items...");
		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			rad.add(i);
		}
		s.stop();
		System.out.println("done (" + s.elapsedSeconds() + "s)");

		System.out.print("Prepending " + K + " items...");
		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			rad.add(0, i);
		}
		s.stop();
		System.out.println("done (" + s.elapsedSeconds() + "s)");

		System.out.print("Removing " + K + " items from the back...");
		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			rad.remove(rad.size()-1);
		}
		s.stop();
		System.out.println("done (" + s.elapsedSeconds() + "s)");

		System.out.print("Removing " + K + " items from the front...");
		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			rad.remove(0);
		}
		s.stop();
		System.out.println("done (" + s.elapsedSeconds() + "s)");
	}
    
}
