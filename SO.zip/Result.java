public class Result {
    public enum Type {
        RIGA, COLONNA, DIAGONALE_DESC, DIAGONALE_ASC
    }

    private int length;     // Lunghezza della sequenza
    private int startX;     // Coordinata X di partenza
    private int startY;     // Coordinata Y di partenza
    private int color;      // Colore dei pixel (ARGB)
    private Type type;      // Tipo sequenza

    public Result(int length, int startX, int startY, int color, Type type) {
        this.length = length;
        this.startX = startX;
        this.startY = startY;
        this.color = color;
        this.type = type;
    }

    // Getter
    public int getLength() { return length; }
    public int getStartX() { return startX; }
    public int getStartY() { return startY; }
    public int getColor() { return color; }
    public Type getType() { return type; }

    // Setter opzionali
    public void setLength(int length) { this.length = length; }
    public void setStartX(int startX) { this.startX = startX; }
    public void setStartY(int startY) { this.startY = startY; }
    public void setColor(int color) { this.color = color; }
    public void setType(Type type) { this.type = type; }

    @Override
    public String toString() {
        String typeStr;
        switch (type) {
            case RIGA -> typeStr = "Riga";
            case COLONNA -> typeStr = "Colonna";
            case DIAGONALE_DESC -> typeStr = "Diagonale ↘";
            case DIAGONALE_ASC -> typeStr = "Diagonale ↙";
            default -> typeStr = "Sconosciuto";
        }
        return String.format("Tipo: %s | Lunghezza: %d | Partenza: (%d,%d) | Colore: #%08X",
                typeStr, length, startX, startY, color);
    }
}