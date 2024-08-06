import java.util.Scanner;

public class Main {
    public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";


    public static void main(String[] args) {
        char[][] board = initBoard();

        char currentPlayer = 'o';

        while (true) {
            drawBoard(board);
            getTurn(currentPlayer, board);

            if (checkWin(currentPlayer, board)) {
                drawBoard(board);
                String color = currentPlayer == 'o' ? YELLOW : BLUE;
                System.out.println("\n" + GREEN + "Player " + color + currentPlayer + GREEN + " wins!" + RESET);
                break;
            }

            if (checkDraw(board)) {
                drawBoard(board);
                System.out.println("\nIts a draw");
                break;
            }

            currentPlayer = switchPlayer(currentPlayer);
        }
    }

    private static char switchPlayer(char currentPlayer) {
        return currentPlayer == 'o' ? 'x' : 'o';
    }

    private static void getTurn(char player, char[][] board) {

        int input = getInput("Player " + player, board);

        addToBoard(input, player, board);
    }

    private static int getInput(String player, char[][] board) {
        Scanner sc = new Scanner(System.in);
        int result;

        System.out.print("\n" + player + ", enter your move (1 - 9): ");


        while (true) {
            String input = sc.nextLine().trim();

            try {
                result = Integer.parseInt(input);

                int[] cords = numberToBoardCord(result);

                if (result >= 1 && result <= 9 && board[cords[0]][cords[1]] != ' ') {
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


    private static boolean checkWin(char player, char[][] board) {

        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }

        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    private static boolean checkDraw(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static char[][] initBoard() {
        char[][] board = new char[3][3];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = ' ';
            }
        }

        return board;
    }

    private static void drawBoard(char[][] board) {
        System.out.println("\n-------------");

        for (int i = 0; i < board.length; i++) {
            System.out.printf("| %s | %s | %s |%n", board[i][0], board[i][1], board[i][2]);
            if (i < board.length - 1) {
                System.out.println("|---+---+---|");
            }
        }


        System.out.println("-------------");
    }

    public static int[] numberToBoardCord(int number) {
        number--;
        return new int[]{number / 3, number % 3};
    }

    private static void addToBoard(int input, char player, char[][] board) {
        int[] cords = numberToBoardCord(input);
        board[cords[0]][cords[1]] = player;
    }
}