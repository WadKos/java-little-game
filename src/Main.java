import java.util.Scanner;

public class Main {
    static public int sizeOfField = 5;

    public static void main(String[] args) {
        boolean[][] field = new boolean[sizeOfField][sizeOfField];
        Scanner scanner = new Scanner(System.in);
        int oldPositionX = Player.positionX;
        int oldPositionY = Player.positionY;
        field[Player.positionY][Player.positionX] = true;

        while (true) {
            drawField(field);
            String move = scanner.next();
            Player.changePosition(move);
            field[oldPositionY][oldPositionX] = false;
            oldPositionX = Player.positionX;
            oldPositionY = Player.positionY;
            field[Player.positionY][Player.positionX] = true;
        }
    }

    static void drawField(boolean[][] field) {
        System.out.println("-".repeat(3*sizeOfField));
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.printf("|%d|", field[i][j] ? 1 : 0);
            }
            System.out.println();
        }
        System.out.println("-".repeat(3*sizeOfField));
    }
}

class Player {
    static int positionX = 0;
    static int positionY = 0;

    public static void changePosition(String move) {
        switch (move) {
            case "left":
                positionX--;
                break;
            case "right":
                positionX++;
                break;
            case "up":
                positionY--;
                break;
            case "down":
                positionY++;
                break;
            default:
                System.out.println("Вы ввели что-то не то, попробуйте еще раз");
        }
        positionY = checkPosition(positionY);
        positionX = checkPosition(positionX);
    }

    static int checkPosition(int position) {
        if (position < 0) {
            return Main.sizeOfField - 1;
        } else if (position >= Main.sizeOfField) {
            return 0;
        }
        return position;
    }
}
