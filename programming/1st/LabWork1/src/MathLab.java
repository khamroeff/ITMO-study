import java.lang.Math;
public class MathLab {
    public static void main(String[] args) {
        int[] a = new int[20/2 - (4+1)/2 + 1];
        for (int i = 4; i <= 20; i += 2){
            a[(i - 4) / 2] = i;
        }

        float x[] = new float[11];
        for (int i = 0; i < 11; i++) {
            x[i] = (float) (Math.random() * 10 - 6);
        }
        double b[][] = new double[9][11];
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 11; j++){
                switch (a[i]) {
                    case 4: b[i][j] = Math.tan(Math.log(Math.pow(Math.abs(x[j])/2, 2)));
                        break;
                    case 6:
                    case 8:
                    case 12:
                    case 20: b[i][j] = Math.cos(Math.cbrt(Math.cbrt(x[j])));
                        break;
                    default: b[i][j] = Math.pow(Math.E, Math.pow(3/4 * (Math.pow(Math.E, Math.atan(((x[j]-1) * Math.E/1) + 1)) + 4), 2));

                }
                System.out.printf("%8.3f", b[i][j]);
            }
            System.out.println();
        }


    }
}
