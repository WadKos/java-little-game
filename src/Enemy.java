public class Enemy {
    int positionX = 0;
    int positionY = 0;

    public Enemy (int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;

        Main.field[positionY][positionX] = Main.ENEMY_ID;
    }

}
