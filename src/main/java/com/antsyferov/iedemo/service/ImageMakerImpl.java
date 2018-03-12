package com.antsyferov.iedemo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class ImageMakerImpl implements ImageMaker {

    private static final int MARGIN = 20;
    private static final int HALF_MARGIN = MARGIN / 2;

    @Value("${image.font:Arial}")
    private String fontName;

    @Value("${image.font-size:18}")
    private int fontSize;

    @Override
    public byte[] makeImage(String text) throws IOException {
        List<String> lines = Arrays.asList(text.split("\n"));

        Font font = new Font(fontName, Font.PLAIN, fontSize);
        FontRenderContext frc = new FontRenderContext(null, true, true);

        Rectangle2D lineBounds = font.getStringBounds(getLongestLine(lines), frc);
        int lineWidth = (int) lineBounds.getWidth();
        int lineHeight = (int) lineBounds.getHeight();

        int totalWidth = lineWidth + MARGIN;
        int totalHeight = lineHeight * lines.size() + MARGIN;

        BufferedImage image = new BufferedImage(totalWidth, totalHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics2D = image.createGraphics();

        graphics2D.setColor(Color.ORANGE);
        graphics2D.fillRect(0, 0, totalWidth, totalHeight);
        graphics2D.setColor(Color.BLACK);
        graphics2D.setFont(font);

        float x = (float) lineBounds.getX() + HALF_MARGIN;
        float y = (float) -lineBounds.getY() + HALF_MARGIN;
        for (String line : lines) {
            graphics2D.drawString(line, x, y);
            y += lineHeight;
        }

        graphics2D.dispose();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        outputStream.flush();
        byte[] imageBytes = outputStream.toByteArray();
        outputStream.close();

        return imageBytes;
    }

    private String getLongestLine(List<String> lines) {
        return lines.stream().max(Comparator.comparingInt(String::length)).orElse("");
    }
}
