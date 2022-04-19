import java.util.ArrayList;
import java.util.List;

public class PhilsTable<T> implements AbstractTble<T> {
    private int numCols;
    private int numRows;
    private List<List<T>> table; 
    
    public PhilsTable(Class<T> t){
        table = new ArrayList<List<T>>();
        numCols = 0 ; 
        numRows = 0 ; 
    }

    public int rows() {
        return numRows;
    }

    public int cols(){
        return numCols;
    }

    public T get(int i, int j){
        if(i<0 || i > rows() - 1 || j < 0 || j > cols() - 1 ){
            throw new IndexOutOfBoundsException();
        } 
        return table.get(i).get(j);
    }

    public T set(int i, int j, T x ){
        if(i<0 || i > rows() - 1 || j < 0 || j > cols() - 1 ){
            throw new IndexOutOfBoundsException();
        }
        return table.get(i).set(j, x);
    }

    public void addRows(int i){
        if(i<0 || i > rows()){
            throw new IndexOutOfBoundsException();
        } 
        List<T> newRow = new ArrayList<T>();
        for (int k = 0 ; k < cols() ; k++){
            newRow.add(null);
        }
        table.add(i,newRow);
        numRows++;
    }

    public void removeRows(int i ) { 
        if(i<0 || i > rows() - 1){
            throw new IndexOutOfBoundsException();
        } table.remove(i);
        numRows--;
    }

    public void addCols(int j ){ 
        if(j < 0 || j > cols()){
            throw new IndexOutOfBoundsException();
        }
        for(int i =0 ; i < rows(); i++){
            table.get(i).add(j, null);
        }
        numCols++;
    }

    public void removeCols(int j){
        if(j < 0 || j > cols() - 1) throw new IndexOutOfBoundsException();
        for (int u = 0; u<rows(); u++){
            table.get(u).remove(j);
        }
        numCols--; 
    }

    public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < rows(); i++) {
			for (int j = 0; j < cols(); j++) {
				sb.append(String.valueOf(get(i, j)));
				sb.append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}


    public static void main(String[] args) {
		int nrows = 9, ncols = 6;
		PhilsTable<Integer> t = new PhilsTable<Integer>(Integer.class);
		for (int i = 0; i < ncols; i++) {
			t.addCols(t.cols());
		}
		for (int i = 0; i < nrows; i++) {
			t.addRows(t.rows());
		}
		for (int i = 1; i <= nrows; i++) {
			t.set(i-1, (i-1)%t.cols(), 1111*i);
		}
		System.out.println(t.toString());
		t.addCols(2);
        t.addRows(3);
		System.out.println(t.toString());
	}
}


