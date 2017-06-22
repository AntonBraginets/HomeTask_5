import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static int count = 1;
    static int player_number = 0;
    static int step_player1 = 0;
    static int step_player2 = 0;
    static int step_comp = 0;
    static boolean flag = true;
    static String turn = "Player 1";
    static String winner;
    static String array[][] = {{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8", "9"}}; //создаем массив (пока что в ручном режиме)

    public static void main(String[] args) {
        System.out.print("Please choose game mode (1 for single player or 2 for two players): ");

        numberOfPlayersChecker();

        numberOfPlayers(player_number);//выбираем количество игроков
        fieldPrint(); // выводим наше поле на экран

        do {//ходим, пока не будет победителя
            if (player_number == 1 & turn.equals("Player 1")) //выбран режим для одного игрока и компьютера, ходит игрок 1
                try {
                    single_player1_move();
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Please make correct choice: 1 to 9.");
                    single_player1_move();
                }

            else if (player_number == 1 & turn.equals("Computer")) //выбран режим для одного игрока и компьютера, ходит компьютер
                single_comp_move();

            else if (player_number == 2 & turn.equals("Player 1")) //выбран режим для двух игроков, ходит игрок 1
                try {
                    multi_player1_move();
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Please make correct choice: 1 to 9.");
                    multi_player1_move();
                }

            else //выбран режим для двух игроков, ходит игрок 2
                try {
                    multi_player2_move();
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Please make correct choice: 1 to 9.");
                    multi_player2_move();
                }

            fieldPrint();//выводим наше поле после хода
            System.out.println(turn + "'s turn");

        } while (winnerChecker());//проверяем, можем ли мы еще ходить или уже есть победитель
        System.out.println("Winner is: " + winner);
    }

    public static int numberOfPlayersChecker() {
        try {
            player_number = new Scanner(System.in).nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.print("Please enter correct value: 1 or 2.");
            numberOfPlayersChecker();
        }
        if (player_number < 1 || player_number > 2) {
            System.out.print("Please enter correct value: 1 or 2.");
            numberOfPlayersChecker();
        }
        return player_number;
    }

    public static void single_player1_move() {
        System.out.print("Please choose field 1 to 9 to put 'X': ");
        step_player1 = new Scanner(System.in).nextInt();
        if (step_player1 > 0 && step_player1 < 10) {
            makeMove(step_player1);
            turn = "Computer";
        } else {
            System.out.println("Please make correct choice: 1 to 9.");
            single_player1_move();
        }
    }

    public static void single_comp_move() {
        step_comp = new Random().nextInt(8) + 1;//случайным образом выбираем ячейку, в котору умный комп вставит свой нолик
        makeMove(step_comp);//ход компьютера
        turn = "Player 1";
    }

    public static void multi_player1_move() {
        System.out.print("Please choose field 1 to 9 to put 'X': ");
        step_player1 = new Scanner(System.in).nextInt();
        if (step_player1 > 0 && step_player1 < 10) {
            makeMove(step_player1);
            turn = "Player 2";
        } else {
            System.out.println("Please make correct choice: 1 to 9.");
            multi_player1_move();
        }
    }

    public static void multi_player2_move() {
        System.out.print("Please choose field 1 to 9 to put 'O': ");
        step_player2 = new Scanner(System.in).nextInt();
        if (step_player2 > 0 || step_player2 < 10) {
            makeMove(step_player2);
            turn = "Player 1";
        } else {
            System.out.println("Please make correct choice: 1 to 9.");
            multi_player2_move();
        }
    }

    public static void fieldPrint() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + "\t");
                count++;
            }
            System.out.println();
        }
    }

    public static void numberOfPlayers(int player_number) {
        switch (player_number) {
            case 1: {
                System.out.println("You chose single player.");
                break;
            }
            case 2: {
                System.out.println("You chose game for two players.");
                break;
            }
            //default: {
            //    System.out.println("This option is unavailable. Please enter 1 or 2");
            //    break;
            //}
        }
    }

    public static void makeMove(int step) {
        if (turn.equals("Player 1")) {
            switch (step) {
                case 1:
                case 2:
                case 3:
                    if (array[0][step - 1].equals("X") || array[0][step - 1].equals("O"))
                        single_player1_move();
                    else array[0][step - 1] = "X";
                    break;

                case 4:
                case 5:
                case 6:
                    if (array[1][step - 4].equals("X") || array[1][step - 4].equals("O"))
                        single_player1_move();
                    else array[1][step - 4] = "X";
                    break;

                case 7:
                case 8:
                case 9:
                    if (array[2][step - 7].equals("X") || array[2][step - 7].equals("O"))
                        single_player1_move();
                    else array[2][step - 7] = "X";
                    break;

            }
        } else if (turn.equals("Computer")) {
            switch (step) {
                case 1:
                case 2:
                case 3:
                    if (array[0][step - 1].equals("X") || array[0][step - 1].equals("O"))
                        single_comp_move();
                    else array[0][step - 1] = "O";
                    break;

                case 4:
                case 5:
                case 6:
                    if (array[1][step - 4].equals("X") || array[1][step - 4].equals("O"))
                        single_comp_move();
                    else array[1][step - 4] = "O";
                    break;

                case 7:
                case 8:
                case 9:
                    if (array[2][step - 7].equals("X") || array[2][step - 7].equals("O"))
                        single_comp_move();
                    else array[2][step - 7] = "O";
                    break;
            }
        } else {
            switch (step) {
                case 1:
                case 2:
                case 3:
                    if (array[0][step - 1].equals("X") || array[0][step - 1].equals("O"))
                        multi_player2_move();
                    else array[0][step - 1] = "O";
                    break;

                case 4:
                case 5:
                case 6:
                    if (array[1][step - 4].equals("X") || array[1][step - 4].equals("O"))
                        multi_player2_move();
                    else array[1][step - 4] = "O";
                    break;

                case 7:
                case 8:
                case 9:
                    if (array[2][step - 7].equals("X") || array[2][step - 7].equals("O"))
                        multi_player2_move();
                    else array[2][step - 7] = "O";
                    break;
            }
        }
    }

    public static boolean winnerChecker() {
        for (int i = 0; i < array.length; i++) {
            if (array[i][0].equals(array[i][1]) && array[i][1].equals(array[i][2]) && array[i][2].equals("X")) {
                flag = false;
                winner = "Player 1";
                break;
            }
            if (array[i][0].equals(array[i][1]) && array[i][1].equals(array[i][2]) && array[i][2].equals("O")) {
                flag = false;
                if (player_number == 1)
                    winner = "Computer";
                else
                    winner = "Player 2";
                break;
            }
            for (int j = 0; j < array[i].length; j++) {
                if (array[0][j].equals(array[1][j]) && array[1][j].equals(array[2][j]) && array[2][j].equals("X")) {
                    flag = false;
                    winner = "Player 1";
                    break;
                }
                if (array[0][j].equals(array[1][j]) && array[1][j].equals(array[2][j]) && array[2][j].equals("O")) {
                    flag = false;
                    if (player_number == 1)
                        winner = "Computer";
                    else
                        winner = "Player 2";
                    break;
                }
            }
        }
        if (array[0][0].equals(array[1][1]) && array[1][1].equals(array[2][2]) && array[2][2].equals("X")) {
            winner = "Player 1";
            flag = false;
        }
        if (array[0][0].equals(array[1][1]) && array[1][1].equals(array[2][2]) && array[2][2].equals("O")) {
            if (player_number == 1)
                winner = "Computer";
            else
                winner = "Player 2";
            flag = false;
        }
        if (array[2][0].equals(array[1][1]) && array[1][1].equals(array[0][2]) && array[0][2].equals("X")) {
            winner = "Player 1";
            flag = false;
        }
        if (array[2][0].equals(array[1][1]) && array[1][1].equals(array[0][2]) && array[0][2].equals("O")) {
            if (player_number == 1)
                winner = "Computer";
            else
                winner = "Player 2";
            flag = false;
        }

        return flag;
    }
}