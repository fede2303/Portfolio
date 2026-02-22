import java.awt.image.BufferedImage;

public class RowFinder extends Thread {
    private BufferedImage img;
    private Result result;

    public RowFinder(BufferedImage img) {
        this.img = img;
    }

    @Override
    public void run() {
        int bestLen = 0, bestX = 0, bestY = 0, bestColor = 0;

        for (int y = 0; y < img.getHeight(); y++) {
            int currentColor = img.getRGB(0, y);
            int currentLen = 1;
            int startX = 0;

            for (int x = 1; x < img.getWidth(); x++) {
                if (img.getRGB(x, y) == currentColor) {
                    currentLen++;
                } else {
                    if (currentLen > bestLen) {
                        bestLen = currentLen;
                        bestX = startX;
                        bestY = y;
                        bestColor = currentColor;
                    }
                    currentColor = img.getRGB(x, y);
                    currentLen = 1;
                    startX = x;
                }
            }
            // Controllo alla fine della riga
            if (currentLen > bestLen) {
                bestLen = currentLen;
                bestX = startX;
                bestY = y;
                bestColor = currentColor;
            }
        }

        // Uso l'enum invece della stringa
        result = new Result(bestLen, bestX, bestY, bestColor, Result.Type.RIGA);
    }

    public Result getResult() {
        return result;
    }
}