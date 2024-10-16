package io.lin.qrgenie.imagehandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageService {
    private static String ticketTemplatePath;
    private static String outputPath = "txout/";

    public ImageService(String root, String ticketTemplatePath, String ticketTypePath, String ticketModePath) {
        this.ticketTemplatePath = root + ticketTemplatePath;
        outputPath = root + outputPath + ticketTypePath + ticketModePath;
    }


    public void insertQrImageToTicket(BufferedImage qrImage, String filename, int x, int y, int height, int width) throws IOException {
        BufferedImage ticketImage = ImageIO.read(new File(ticketTemplatePath));
        Graphics2D g = ticketImage.createGraphics();

        Image resizedQrCode = qrImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        g.drawImage(resizedQrCode, x, y, null);

        createImage(ticketImage, outputPath+filename);

        g.dispose();
    }

    private void createImage(BufferedImage image, String path) throws IOException {
        ImageIO.write(image, "png", new File(path));
    }
}