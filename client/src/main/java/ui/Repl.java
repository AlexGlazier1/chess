package ui;
import java.util.Scanner;

public class Repl {

    private final ChessClient client;

    public Repl(String serverUrl) {
        client = new ChessClient(serverUrl);
    }



    public void run() {
        System.out.println("\uD83D\uDC51 Welcome to 240 Chess. Type Help to get started");

        Scanner scanner = new Scanner(System.in);
        var result = "";
        while (!result.equals("quit")) {
            printPrompt();
            String line = scanner.nextLine();

            try {
                result = client.eval(line);
                System.out.print(result);
            } catch (Throwable e) {
                var msg = e.toString();
                System.out.print(msg);
            }
        }
        System.out.println();
    }
    

    private void printPrompt() {
        System.out.print("\n" + ">>> ");
    }


}

