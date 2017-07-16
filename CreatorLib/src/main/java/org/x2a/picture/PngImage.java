package org.x2a.picture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by ethan on 7/13/17.
 */
public class PngImage {

    private final int width;
    private final int height;
    private final boolean hasAlpha;

    private final byte[] bytes;

    public PngImage(File file) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);
        WritableRaster raster = bufferedImage.getRaster();
        DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();

        bytes = dataBuffer.getData();

        hasAlpha = bytes.length == width * height * 4; //if 4 bits per pixel, there must be alpha
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public boolean isHasAlpha() {
        return hasAlpha;
    }
}
