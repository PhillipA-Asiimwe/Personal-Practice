import java.util.List;


/**
 */
public class Tester {
	public static boolean testPart1(List<Integer> ell) {
        List<Integer> tr = ell;
        int K = 1000;
        StopWatch s = new StopWatch();
        System.out.print("Appending " + K + " items...");
        System.out.flush();
        s.start();
        for (int i = 0; i < K; i++) {
            tr.add(i);
            ell.add(i);
        }
        s.stop();
        System.out.println("done (" + s.elapsedSeconds() + "s)");

        System.out.print("Prepending " + K + " items...");
        System.out.flush();
        s.start();
        for (int i = 0; i < K; i++) {
            tr.add(0, i);
            ell.add(0,i);
        }
        s.stop();
        System.out.println("done (" + s.elapsedSeconds() + "s)");

        System.out.print("Midpending(?!) " + K + " items...");
        System.out.flush();
        s.start();
        for (int i = 0; i < K; i++) {
            tr.add(tr.size() / 2, i);
            ell.add(tr.size() / 2, i);
        }
        s.stop();
        System.out.println("done (" + s.elapsedSeconds() + "s)");


        System.out.print("Removing " + K + " items from the left...");
        System.out.flush();
        s.start();
        for (int i = 0; i < K; i++) {
            tr.remove(tr.size() - 1);
            ell.remove(ell.size() - 1);
        }
        s.stop();
        System.out.println("done (" + s.elapsedSeconds() + "s)");

        System.out.print("Removing " + K + " items from the right...");
        System.out.flush();
        s.start();
        for (int i = 0; i < K; i++) {
            tr.remove(0);
            ell.remove(0);
        }
        s.stop();
        System.out.println("done (" + s.elapsedSeconds() + "s)");

        System.out.print("Removing " + K + " items from the middle...");
        System.out.flush();
        s.start();
        for (int i = 0; i < K; i++) {
            tr.remove(tr.size() / 2);
            ell.remove(ell.size()/2);
        }
        s.stop();
        System.out.println("done (" + s.elapsedSeconds() + "s)");

        for(int i=0; i<tr.size();i++){
            if (tr.get(i)!=ell.get(i)){
                return false;
            }
        }

        ell.clear();
        tr.clear();
        K = 3000;
        s.start();
        for (int i=0 ; i < K ; i++){
            tr.add(i);
        }
        s.stop();
        if(s.elapsedSeconds()>0.1) return false;

        s.start();
        while(tr.size()> 0){
            tr.remove(0);
        }
        s.stop();
        if (s.elapsedSeconds()>0.3) return false;

      return true;


	}

	public static boolean testPart2(List<Integer> ell) {
        List<Integer> rad = ell;
        int K = 100;
        StopWatch s = new StopWatch();
        System.out.print("Appending " + K + " items...");
        System.out.flush();
        s.start();
        for (int i = 0; i < K; i++) {
            rad.add(i);
            ell.add(i);

        }
        s.stop();
        System.out.println("done (" + s.elapsedSeconds() + "s)");

        System.out.print("Prepending " + K + " items...");
        System.out.flush();
        s.start();
        for (int i = 0; i < K; i++) {
            rad.add(0, i);
            ell.add(0, i);
        }
        s.stop();
        System.out.println("done (" + s.elapsedSeconds() + "s)");

        System.out.print("Removing " + K + " items from the back...");
        System.out.flush();
        s.start();
        for (int i = 0; i < K; i++) {
            rad.remove(rad.size()-1);
            ell.remove(rad.size()-1);
        }
        s.stop();
        System.out.println("done (" + s.elapsedSeconds() + "s)");

        System.out.print("Removing " + K + " items from the front...");
        System.out.flush();
        s.start();
        for (int i = 0; i < K; i++) {
            rad.remove(0);
            ell.remove(0);
        }
        s.stop();
        System.out.println("done (" + s.elapsedSeconds() + "s)");

        for(int i=0; i<rad.size();i++){
            if (rad.get(i)!=ell.get(i)){
                return false;
            }
        }


		return true;
	}

	public static boolean testPart3(AbstractTble<Integer> ell) {
		return true;
	}

}
