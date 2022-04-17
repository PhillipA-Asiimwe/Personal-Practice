import java.lang.module.ResolutionException;
import java.util.AbstractList;

public class PhilsArrayDeque<T> extends AbstractList<T> {
    private Factory<T> f;
    private T[] a; 
    private int d; 
    private int n; 

    private void resize(){ 
        T[] b = f.newArray(Math.max(2*n,1));
        for (int l=0; l < n; l++)
            b[l] = a[(d + l ) % a.length];
        a = b; 
        d = 0 ; 
    }

    public PhilsArrayDeque(Class<T> t){
        f = new Factory<>(t);
        a = f.newArray(1);
        n = 0; 
        d = 0; 
    }

    public int size(){ 
        return n; 
    }
    
    public T get(int i){ 
        if (i<0 || i>n-1) throw new IndexOutOfBoundsException(); 
        return a[(d+i)% a.length] ;
    }

    public T set( int i , T x){ 
        if (i<0 || i>n-1) throw new IndexOutOfBoundsException();
        T y = a[(d + i ) % a.length];
        a[(d+i)% a.length] = x ; 
        return y; 
    }

    public void add(int i, T x ){
        if (i < 0 || i >n ) throw new IndexOutOfBoundsException();
        if ( n+1 > a.length) resize();
        if ( i<n/2){ 
            d = (d==0) ? a.length-1 : d - 1; 
            for (int k=0 ; k<= i-1;k ++){
                a[(d+k)%a.length]= a[(d+k+1)%a.length];
            }
        }else { 
            for (int k=n; k>i;k--){
                a[(d+k)%a.length]= a[(d+k-1)%a.length];
            }
        }
        a[(d+i)%a.length] = x ; 
        n++; 
    }

    public T remove(int i){ 
        if ( i<0||i>n-1) throw new IndexOutOfBoundsException();
        T x = a[(d+i)%a.length];
        if(i<n/2){
            for (int k = i; k>0; k--){
                a[(d+k)%a.length] = a[(d+k-1)%a.length];
            }
            d = (d+1)%a.length;
        }else{
            for(int k = i; k<n-1;k++){
                a[(d+k)%a.length] = a[(d+k+1)%a.length];
            }
        }
        n--; 
        if (3*n < a.length) resize();
        return x; 
    }

    public void clear(){ 
        n=0;
        resize();
    }
    
}
