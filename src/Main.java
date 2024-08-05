import java.util.*;

public class Main {
    public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";

    private static final Map<Integer, String> board = new HashMap<>() {{
        put(1, " ");
        put(2, " ");
        put(3, " ");
        put(4, " ");
        put(5, " ");
        put(6, " ");
        put(7, " ");
        put(8, " ");
        put(9, " ");
    }};

    private static final Set<Integer[]> winningNumbers = new HashSet<>() {{
        add(new Integer[]{1, 2, 3});
        add(new Integer[]{4, 5, 6});
        add(new Integer[]{7, 8, 9});
        add(new Integer[]{1, 4, 7});
        add(new Integer[]{2, 5, 8});
        add(new Integer[]{3, 6, 9});
        add(new Integer[]{1, 5, 9});
        add(new Integer[]{3, 5, 7});
    }};

    public static void main(String[] args) {

        while (true) {
            if (getTurn("o"))
                break;
            if (getTurn("x"))
                break;
        }
    }

    private static boolean getTurn(String player) {
        drawBoard();

        String color = player.equals("o") ? YELLOW : BLUE;

        int input = getInput("Player " + player);
        board.put(input, player);

        if (checkWin(player)) {
            drawBoard();
            System.out.println("\n" + GREEN + "Player " + color + player + GREEN + " wins!" + RESET);
            return true;
        }

        return false;
    }

    private static boolean checkWin(String player) {
        Set<Integer> results = new HashSet<>();

        for (Map.Entry<Integer, String> entry : board.entrySet()) {
            if (entry.getValue().contains(player)) {
                results.add(entry.getKey());
            }
        }

        for (Integer[] i : winningNumbers) {
            if (results.containsAll(Arrays.asList(i))) {
                return true;
            }
        }

        return false;
    }


    private static int getInput(String player) {
        Scanner sc = new Scanner(System.in);
        int result;

        System.out.print("\n" + player + ", enter your move (1 - 9): ");


        while (true) {
            String input = sc.nextLine().trim();

            try {
                result = Integer.parseInt(input);

                if (result >= 1 && result <= 9 && !board.get(result).equals(" ")) {
                    System.err.print("Invalid move. Square already occupied. Please choose another: ");
                    continue;
                }

                if (result >= 1 && result <= 9) {
                    return result;
                } else {
                    System.err.print("Invalid input. Please enter a number between 1 and 9: ");
                }


            } catch (NumberFormatException e) {
                System.err.print("Invalid input. Please enter a number: ");
            }
        }
    }

    private static void drawBoard() {
        System.out.println("\n-------------");
        System.out.printf("| %s | %s | %s |%n", board.get(1), board.get(2), board.get(3));
        System.out.println("|---+---+---|");
        System.out.printf("| %s | %s | %s |%n", board.get(4), board.get(5), board.get(6));
        System.out.println("|---+---+---|");
        System.out.printf("| %s | %s | %s |%n", board.get(7), board.get(8), board.get(9));
        System.out.println("-------------");
    }
}