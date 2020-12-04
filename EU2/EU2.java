package EU2;

import java.util.Arrays;

public class EU2 {

    static public void main(String[] args) {
        int n = 6;
        int[] arr = {0, 5, 4, 3, 2, 1, 0};

        System.out.println(sortera(n, arr));
    }

    static public int sortera(int n, int[] X) {
        
        int c = 0;
        int i = 1;
        int j;
        int temp;
        while (i < n) {
            j = i + 1;
            while (j <= n) {
                c += 1;
                if (X[j] < X[i]) {
                    System.out.println(Arrays.toString(X));
                    temp = X[j];
                    X[j] = X[i];
                    X[i] = temp;
                }

                j++;
            }
            i++;
        }
        System.out.println(c);
        return X[1];
    }
}

// O(n^2) elementjämföreleser
// O(0) BÄSTA FALL
// O(n^2)
    
// [1, 2, 4, 3]