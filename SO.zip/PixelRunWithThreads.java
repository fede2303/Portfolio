import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;

public class PixelRunWithThreads {
    public static void main(String[] args) {
        try {
            // Carico l'immagine da resources
            BufferedImage img = ImageIO.read(
                PixelRunWithThreads.class.getResourceAsStream("/selfie.png")
            );

            if (img == null) {
                throw new IOException("Immagine non trovata in /resources/");
            }

            // Creo i thread
            RowFinder rowThread = new RowFinder(img);
            ColumnFinder colThread = new ColumnFinder(img);
            DiagonalFinder diagThread = new DiagonalFinder(img);

            // Avvio i thread
            rowThread.start();
            colThread.start();
            diagThread.start();

            // Aspetto che finiscano
            rowThread.join();
            colThread.join();
            diagThread.join();

            // Raccolgo i risultati
            Result rowResult = rowThread.getResult();
            Result colResult = colThread.getResult();
            Result diagResult = diagThread.getResult();

            // Scelgo il migliore
            Result best = rowResult;
            if (colResult.getLength() > best.getLength()) best = colResult;
            if (diagResult.getLength() > best.getLength()) best = diagResult;

            System.out.println("Sequenza più lunga trovata:");
            System.out.println(best);

            // Evidenzio la sequenza
            highlightSequence(img, best);

            // Salvo l'immagine modificata
            File output = new File("resources/output.png");
            ImageIO.write(img, "png", output);

            System.out.println("Immagine salvata in: " + output.getAbsolutePath());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Metodo per colorare di rosso la sequenza trovata, con controllo dei limiti
    private static void highlightSequence(BufferedImage img, Result res) {
        int length = res.getLength();
        int x = res.getStartX();
        int y = res.getStartY();

        Color red = Color.RED;
        int width = img.getWidth();
        int height = img.getHeight();

        switch (res.getType()) {
            case RIGA -> {
                for (int i = 0; i < length; i++) {
                    int nx = x + i;
                    if (nx < width) {
                        img.setRGB(nx, y, red.getRGB());
                    }
                }
            }

            case COLONNA -> {
                for (int i = 0; i < length; i++) {
                    int ny = y + i;
                    if (ny < height) {
                        img.setRGB(x, ny, red.getRGB());
                    }
                }
            }

            case DIAGONALE_DESC -> { // ↘
                for (int i = 0; i < length; i++) {
                    int nx = x + i;
                    int ny = y + i;
                    if (nx < width && ny < height) {
                        img.setRGB(nx, ny, red.getRGB());
                    }
                }
            }

            case DIAGONALE_ASC -> { // ↙
                for (int i = 0; i < length; i++) {
                    int nx = x - i;
                    int ny = y + i;
                    if (nx >= 0 && ny < height) {
                        img.setRGB(nx, ny, red.getRGB());
                    }
                }
            }
        }
    }
}