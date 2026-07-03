import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    // ---- snippet: mystery() ----
    static int mystery() {
        try {
            System.out.println("A");
            return 1;
        } catch (Exception e) {
            System.out.println("B");
            return 2;
        } finally {
            System.out.println("C");
            return 3;
        }
    }

    // ---- snippet: risky(int x) ----
    static int risky(int x) {
        try {
            if (x == 0) throw new ArithmeticException();
            return 10 / x;
        } catch (ArithmeticException e) {
            System.out.println("caught");
            return -1;
        } finally {
            System.out.println("done");
        }
    }

    // ---- Part (c): custom checked exception ----
    static class InsufficientFundsException extends Exception {
        public InsufficientFundsException(String message) {
            super(message);
        }
    }

    static double balance = 5000.0;

    static void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(
                "Cannot withdraw " + amount + "; balance is only " + balance);
        }
        balance -= amount;
    }

    // ---- Part (d): try-with-resources rewrite ----
    static void readFirstLine(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            System.out.println(line);
        } // br.close() is called automatically, even if an exception is thrown above
    }

    public static void main(String[] args) throws IOException {
        System.out.println("mystery() output/value:");
        System.out.println("Returned: " + mystery());

        System.out.println("\nrisky(2):");
        System.out.println("Returned: " + risky(2));

        System.out.println("\nrisky(0):");
        System.out.println("Returned: " + risky(0));

        System.out.println("\nWithdraw request:");
        try {
            withdraw(2000);
            System.out.println("Withdrew 2000, balance = " + balance);
            withdraw(10000); // triggers InsufficientFundsException
        } catch (InsufficientFundsException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        System.out.println("\nCustomer Care :");
        try (java.io.FileWriter fw = new java.io.FileWriter("data.txt")) {
            fw.write("Thank you. Looking forward to serve you again");
        }
        readFirstLine("data.txt");
    }
}
