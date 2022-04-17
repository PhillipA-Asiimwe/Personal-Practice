import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class RootishArrayStack<T> extends AbstractList<T>{
    Factory<T> f; //Factotry type, object to be stored in the list 
    List<T[]> blocks; //blocks contain the list elements
    int n; // the number of elemebts in the list

    /**
     * Convert a list index into a block number
     * @param i 
     * @return the index of the block that contains list element i 
     * 
     */
    protected static int indx2blck(int i){
        double db = (-3.0 + Math.sqrt(9+8*i))/2.0;
        int b = (int)Math.ceil(db);
        return b; 
    }

    protected void grow(){
        blocks.add(f.newArray(blocks.size()+1));
    }

    protected void shrink(){
        int r = blocks.size();
        while(r>0&&(r-2)*(r-1)/2>=n){
            blocks.remove(blocks.size()-1);
            r--;
        }
    }

    public T get(int i){ 
        if (i<0||i>n-1)throw new IndexOutOfBoundsException();
        int b = indx2blck(i);
        int j = i -b*(b+1)/2; 
        return blocks.get(b)[j];
    }

    public T set(int i, T x){
        if (i<0 || i> n-1) throw new IndexOutOfBoundsException();
        int b = indx2blck(i);
        int j = i - b*(b+1)/2;
        T y = blocks.get(b)[j];
        blocks.get(b)[j]=x ; 
        return y; 
    }

    public void add(int i, T x){
        if (i < 0 || i > n) throw new IndexOutOfBoundsException(); 
        int r = blocks.size(); 
        if (r*(r+1)/2 < n + 1) grow();
        n++; 
        for (int j = n-1; j > i ; j-- ){
            set(j, get(j-1));
        }
        set(i, x);
    }

    public T remove(int i){ 
        if (i<0 || i > n-1) throw new IndexOutOfBoundsException(); 
        T x = get( i); 
        for (int j = i; j<n-1; j++){
            set(j, get( j+1));
        }
        n--; 
        int r = blocks.size(); 
        if ((r-2)*(r-1)/2 >= n) shrink();
        return x; 
    }

    public int size(){  
        return n;
    }

    public RootishArrayStack(Class<T> t){
        f = new Factory<T>(t);
        n = 0; 
        blocks = new ArrayList<T[]>();
    }

    public void clear(){
        blocks.clear();
        n=0; 
    }


    
}
