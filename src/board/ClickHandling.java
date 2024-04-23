package board;

public abstract class ClickHandling {
    private static Square clicked;

    public static void setClicked(Square clicked) {
        ClickHandling.clicked = clicked;
    }

    public static Square getClicked() {
        return clicked;
    }
}
