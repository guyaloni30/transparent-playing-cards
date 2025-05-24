package cardsgame.spot;

public enum Color {
    blue(34),
    orange(31),
    green(32),
    purple(35),
    ;
    private final String ansi;

    Color(int ansi) {
        this.ansi = "\u001B[" + ansi + "m";
    }

    public String print(String text) {
        return ansi + name() + " " + text + "\u001B[0m";//reset
    }

    public String html() {
        return ".card ." + name() + " {border-color:" + name() + ";}.card .dot { background-color:" + name() + ";}";
    }
}
