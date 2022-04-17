import java.lang.reflect.Array;
public class Factory<T> {
    Class<T> l;
    
    public Class<T> type(){
        return l;
    }

    public Factory(Class<T> l0){
        l=l0;
    }

    @SuppressWarnings({"unchecked"})
    protected T[] newArray(int n){
        return(T[])Array.newInstance(l,n);
    }

    public T newInstance() {
        T x;
        try{
            x = l.newInstance();
        } catch( Exception e){
            x = null;
        }
        return x; 
    }
    
}
