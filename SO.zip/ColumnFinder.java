import java.awt.image.BufferedImage;

public class ColumnFinder extends Thread {
    private BufferedImage img;
    private Result result;

    public ColumnFinder(BufferedImage img) {
        this.img = img;
    }

    @Override
    public void run() {
        int bestLen = 0, bestX = 0, bestY = 0, bestColor = 0;

        for (int x = 0; x < img.getWidth(); x++) {
            int currentColor = img.getRGB(x, 0);
            int currentLen = 1;
            int startY = 0;

            for (int y = 1; y < img.getHeight(); y++) {
                if (img.getRGB(x, y) == currentColor) {
                    currentLen++;
                } else {
                    if (currentLen > bestLen) {
                        bestLen = currentLen;
                        bestX = x;
                        bestY = startY;
                        bestColor = currentColor;
                    }
                    currentColor = img.getRGB(x, y);
                    currentLen = 1;
                    startY = y;
                }
            }
            // Controllo finale colonna
            if (currentLen > bestLen) {
                bestLen = currentLen;
                bestX = x;
                bestY = startY;
                bestColor = currentColor;
            }
        }

        // Uso l'enum invece della stringa
        result = new Result(bestLen, bestX, bestY, bestColor, Result.Type.COLONNA);
    }

    public Result getResult() {
        return result;
    }
}