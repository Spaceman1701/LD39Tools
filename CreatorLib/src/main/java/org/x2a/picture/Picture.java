package org.x2a.picture;

/**
 * Created by ethan on 7/13/17.
 */
public class Picture {

    private static final int PICTURE_SIZE = 8 * 8;

    private final Color[] pixels;

    public Picture(byte[] bytes) {
        if (bytes.length % 4 != 0) {
            throw new IllegalArgumentException("Invalid image size!");
        }
        if (bytes.length / 4 != PICTURE_SIZE) {
            throw new IllegalArgumentException("Invalid image size");
        }
        pixels = buildImage(bytes);
    }

    private Color[] buildImage(byte[] bytes) {
        Color[] colors = new Color[bytes.length / 4];
        for (int i = 0; i < bytes.length; i+=4) { //4 bytes per pixel
            int r = bytes[i] & 0xFF;
            int g = bytes[i + 1] & 0xFF;
            int b = bytes[i + 2] & 0xFF;
            int a = bytes[i + 3] & 0xFF;
            Color c = Color.resolveColor(r, g, b, a);
            colors[i / 4] = c;
        }
        return colors;
    }
}
