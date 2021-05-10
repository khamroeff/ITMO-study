public class Sum {
    public static void main(String[] args) {
        int sum = 0;
        try {
            for (String arg : args) {
                int num = Integer.parseInt(arg);
                sum += num;
            }
            System.out.println(sum);
        } catch (NumberFormatException e) {
            System.err.println("Illegal input argument");
        }
    }
}
