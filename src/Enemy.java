public class Enemy {
    static int positionX = 0;
    static int positionY = 0;

    public Enemy (int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;

        Main.field[positionY][positionX] = Main.ENEMY_ID;
    }

}
