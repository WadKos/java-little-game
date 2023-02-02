import java.util.Scanner;

public class Main {


    static final int EMPTY_ID = 0;
    static final int PLAYER_ID = 1;
    static final int ENEMY_ID = 2;
    public static int sizeOfField = 5;
    public static int[][] field = new int[sizeOfField][sizeOfField];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int oldPositionX = Player.positionX;
        int oldPositionY = Player.positionY;
        field[Player.positionY][Player.positionX] = PLAYER_ID;
        Enemy enemy1 = new Enemy(sizeOfField - 1, sizeOfField - 1);

        while (true) {
            drawField(field);
            String move = scanner.next();
            if (move.equals("stop")) {
                break;
            }
            Player.changePosition(move);
            field[oldPositionY][oldPositionX] = EMPTY_ID;
            oldPositionX = Player.positionX;
            oldPositionY = Player.positionY;
            field[Player.positionY][Player.positionX] = PLAYER_ID;
        }
    }

    static void drawField(int[][] field) {
        System.out.println("-".repeat(3*sizeOfField));
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.printf("|%d|", field[i][j]);
            }
            System.out.println();
        }
        System.out.println("-".repeat(3*sizeOfField));
    }
}

class Player {
    static int positionX = 2;
    static int positionY = 2;

    public static void changePosition(String move) {
        switch (move) {
            case "left":
                if (checkMove(checkPosition(positionY),  checkPosition(positionX-1))) {
                    positionX--;
                } else {
                    System.out.println("You should kill enemy first");
                }
                break;
            case "right":
                if (checkMove(checkPosition(positionY), checkPosition(positionX + 1))) {
                    positionX++;
                } else {
                    System.out.println("You should kill enemy first");
                }
                break;
            case "up":
                if (checkMove(checkPosition(positionY - 1), checkPosition(positionX))) {
                    positionY--;
                } else {
                    System.out.println("You should kill enemy first");
                }
                break;
            case "down":
                if (checkMove(checkPosition(positionY + 1), checkPosition(positionX))) {
                    positionY++;
                } else {
                    System.out.println("You should kill enemy first");
                }
                break;
            case "attack":
                attackEnemy();
                break;
            default:
                System.out.println("WTF are you write???");
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

    static boolean checkMove(int positionY, int positionX) {
        return Main.field[positionY][positionX] != Main.ENEMY_ID;
    }

    static void attackEnemy() {
        for (int i = positionY > 0 ? positionY - 1 : positionY;
             i <= (positionY < Main.sizeOfField - 1 ? positionY + 1 : positionY);
             i++) {
            for (int j = positionX > 0 ? positionX - 1 : positionX;
                 j <= (positionX < Main.sizeOfField - 1 ? positionX + 1 : positionX);
                 j++) {
                if (Main.field[i][j] == Main.ENEMY_ID) {
                    Main.field[i][j] = Main.EMPTY_ID;
                    return;
                }
            }
        }
    }
}
