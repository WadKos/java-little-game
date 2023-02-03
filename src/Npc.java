public class Npc {
    int positionX;
    int positionY;

    String dialog;

    public Npc(int positionX, int positionY, String dialog) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.dialog = dialog;


        Main.field[positionY][positionX] = Main.NPC_ID;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public String getDialog() {
        return dialog;
    }
}
