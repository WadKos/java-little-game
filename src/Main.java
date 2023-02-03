import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {


    static final int EMPTY_ID = 0;
    static final int PLAYER_ID = 1;
    static final int ENEMY_ID = 2;
    static final int MOUNTAIN_ID = 3;
    static final int NPC_ID = 4;


    public static int sizeOfField = 5;
    public static int[][] field = new int[sizeOfField][sizeOfField];
    public static ArrayList<Npc> npcList = new ArrayList<Npc>();

    public static void main(String[] args) {
        System.out.printf("Player: %d \n" +
                "Enemy: %d \n" +
                "Mountain: %d \n" +
                "Npc: %d \n" +
                "You can go 'left', 'right', 'up' and 'down' \n" +
                "Also you can 'talk' and 'attack' \n" +
                "If you wanna stop the game just print 'stop' \n", PLAYER_ID, ENEMY_ID, MOUNTAIN_ID, NPC_ID);
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int oldPositionX = Player.positionX;
        int oldPositionY = Player.positionY;

        field[Player.positionY][Player.positionX] = PLAYER_ID;

        Enemy enemy1 = new Enemy(sizeOfField - 1, sizeOfField - 1);

        Npc npc1 = new Npc(0, 0, "Please, kill enemy in the opposite corner!!!");
        npcList.add(npc1);
        Npc npc2 = new Npc(sizeOfField - 1, 0, "Hi, my name is Tolya Markin");
        npcList.add(npc2);

        for (int i = 0; i < sizeOfField; i++) {
            int mountainPosX = random.nextInt(5);
            int mountainPosY = random.nextInt(5);
            if (isCreateMountain(mountainPosX, mountainPosY)) {
                field[mountainPosY][mountainPosX] = MOUNTAIN_ID;
            }
        }

        //Enemy enemy1 = new Enemy(1, 1);
        //Enemy enemy2 = new Enemy(1, 2);
        //Enemy enemy3 = new Enemy(1, 3);
        //Enemy enemy4 = new Enemy(2, 1);
        //Enemy enemy5 = new Enemy(2, 3);
        //Enemy enemy6 = new Enemy(3, 1);
        //Enemy enemy7 = new Enemy(3, 2);
        //Enemy enemy8 = new Enemy(3, 3);

        while (true) {
            drawField(field);
            String move = scanner.next();
            if (move.equals("stop")) {
                break;
            }
            Player.changePosition(move);
            if (!Player.isLive) {
                break;
            }
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
        System.out.print("-".repeat(3*sizeOfField) + " ");
        System.out.printf("You got %d hp \n", Player.healthPoint);
    }

    static Npc findString(int positionX, int positionY) {
        for (int i = 0; i < npcList.toArray().length; i++) {
            if (npcList.get(i).getPositionX() == positionX && npcList.get(i).getPositionY() == positionY) {
                return npcList.get(i);
            }
        }
        return null;
    }

    static boolean isCreateMountain(int positionX, int positionY) {
        return field[positionY][positionX] == EMPTY_ID;
    }
 }

class Player {
    static int positionX = 2;
    static int positionY = 2;
    static boolean isLive = true;
    static int healthPoint = 100;

    public static void changePosition(String move) {
        switch (move) {
            case "left":
                if (checkMove(checkPosition(positionY),  checkPosition(positionX-1)) == Main.EMPTY_ID) {
                    positionX--;
                } else if (checkMove(checkPosition(positionY),  checkPosition(positionX-1)) == Main.ENEMY_ID) {
                    Player.getDamage(25);
                } else if (checkMove(checkPosition(positionY),  checkPosition(positionX-1)) == Main.MOUNTAIN_ID) {
                    System.out.println("You can't go out there, there is a mountain");
                } else if (checkMove(checkPosition(positionY),  checkPosition(positionX-1)) == Main.NPC_ID) {
                    System.out.println("Don't Tread on Me");
                }
                break;
            case "right":
                if (checkMove(checkPosition(positionY),  checkPosition(positionX+1)) == Main.EMPTY_ID) {
                    positionX++;
                } else if (checkMove(checkPosition(positionY),  checkPosition(positionX+1)) == Main.ENEMY_ID) {
                    Player.getDamage(25);
                } else if (checkMove(checkPosition(positionY),  checkPosition(positionX+1)) == Main.MOUNTAIN_ID) {
                    System.out.println("You can't go out there, there is a mountain");
                } else if (checkMove(checkPosition(positionY),  checkPosition(positionX+1)) == Main.NPC_ID) {
                    System.out.println("Don't Tread on Me");
                }
                break;
            case "up":
                if (checkMove(checkPosition(positionY-1),  checkPosition(positionX)) == Main.EMPTY_ID) {
                    positionY--;
                } else if (checkMove(checkPosition(positionY-1),  checkPosition(positionX)) == Main.ENEMY_ID) {
                    Player.getDamage(25);
                } else if (checkMove(checkPosition(positionY-1),  checkPosition(positionX)) == Main.MOUNTAIN_ID) {
                    System.out.println("You can't go out there, there is a mountain");
                } else if (checkMove(checkPosition(positionY-1),  checkPosition(positionX)) == Main.NPC_ID) {
                    System.out.println("Don't Tread on Me");
                }
                break;
            case "down":
                if (checkMove(checkPosition(positionY+1),  checkPosition(positionX)) == Main.EMPTY_ID) {
                    positionY++;
                } else if (checkMove(checkPosition(positionY+1),  checkPosition(positionX)) == Main.ENEMY_ID) {
                    Player.getDamage(25);
                } else if (checkMove(checkPosition(positionY+1),  checkPosition(positionX)) == Main.MOUNTAIN_ID) {
                    System.out.println("You can't go out there, there is a mountain");
                } else if (checkMove(checkPosition(positionY+1),  checkPosition(positionX)) == Main.NPC_ID) {
                    System.out.println("Don't Tread on Me");
                }
                break;
            case "attack":
                attackEnemy();
                break;
            case "talk":
                talk();
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

    static int checkMove(int positionY, int positionX) {
        return Main.field[positionY][positionX];
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

    static void getDamage(int damage) {
        healthPoint -= damage;
        if (healthPoint <= 0) {
            death();
        }
    }

    static void death() {
        System.out.println("You died! Sorry, good luck next time");
        isLive = false;
    }

    static void talk() {
        for (int i = positionY > 0 ? positionY - 1 : positionY;
             i <= (positionY < Main.sizeOfField - 1 ? positionY + 1 : positionY);
             i++) {
            for (int j = positionX > 0 ? positionX - 1 : positionX;
                 j <= (positionX < Main.sizeOfField - 1 ? positionX + 1 : positionX);
                 j++) {
                if (Main.field[i][j] == Main.NPC_ID) {
                    try {
                        System.out.println(Main.findString(j, i).getDialog());
                    } catch (NullPointerException e) {
                        System.out.println("Oops error");
                    }
                    return;
                }
            }
        }
    }


}
