public interface AbstractTble<T> {
    public int rows();
    public int cols();
    public T get(int i, int j);
    public T set(int i , int j, T x);
    public void addRows(int i);
    public void removeRows(int i); 
    public void addCols(int i ); 
    public void removeCols(int i);
    public String toString();
    
}
