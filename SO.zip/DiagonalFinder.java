import java.awt.image.BufferedImage;

public class DiagonalFinder extends Thread {
    private BufferedImage img;
    private Result result;

    public DiagonalFinder(BufferedImage img) {
        this.img = img;
    }

    @Override
    public void run() {
        Result bestResult = new Result(0, 0, 0, 0, Result.Type.RIGA); // tipo temporaneo

        // 🔹 Diagonali ↘ (destra-giù)
        for (int x = 0; x < img.getWidth(); x++) {
            bestResult = checkDiagonal(x, 0, 1, 1, bestResult, Result.Type.DIAGONALE_DESC);
        }
        for (int y = 1; y < img.getHeight(); y++) {
            bestResult = checkDiagonal(0, y, 1, 1, bestResult, Result.Type.DIAGONALE_DESC);
        }

        // 🔹 Diagonali ↙ (sinistra-giù)
        for (int x = 0; x < img.getWidth(); x++) {
            bestResult = checkDiagonal(x, 0, -1, 1, bestResult, Result.Type.DIAGONALE_ASC);
        }
        for (int y = 1; y < img.getHeight(); y++) {
            bestResult = checkDiagonal(img.getWidth() - 1, y, -1, 1, bestResult, Result.Type.DIAGONALE_ASC);
        }

        result = bestResult;
    }

    private Result checkDiagonal(int startX, int startY, int dx, int dy, Result best, Result.Type type) {
        int x = startX;
        int y = startY;
        int currentColor = img.getRGB(x, y);
        int currentLen = 1;
        int bestX = x;
        int bestY = y;
        int bestColor = currentColor;

        while (true) {
            x += dx;
            y += dy;
            if (x < 0 || y < 0 || x >= img.getWidth() || y >= img.getHeight()) break;

            if (img.getRGB(x, y) == currentColor) {
                currentLen++;
            } else {
                if (currentLen > best.getLength()) {
                    best = new Result(currentLen, bestX, bestY, bestColor, type);
                }
                currentColor = img.getRGB(x, y);
                currentLen = 1;
                bestX = x;
                bestY = y;
                bestColor = currentColor;
            }
        }

        // Controllo finale
        if (currentLen > best.getLength()) {
            best = new Result(currentLen, bestX, bestY, currentColor, type);
        }

        return best;
    }

    public Result getResult() {
        return result;
    }
}