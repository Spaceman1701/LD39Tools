package org.x2a.picture;

import java.util.*;

/**
 * Created by ethan on 7/13/17.
 */
public class Picture {

    private static final int PICTURE_SIZE = 8 * 8;

    private final Color[] pixels;

    public Picture(byte[] bytes, boolean hasAlpha) {
        int depth = hasAlpha ? 4 : 3;
        if (bytes.length % depth != 0) {
            throw new IllegalArgumentException("Invalid image size!");
        }
        if (bytes.length / depth != PICTURE_SIZE) {
            throw new IllegalArgumentException("Invalid image size");
        }
        pixels = buildImage(bytes, depth);
    }

    private Color[] buildImage(byte[] bytes, int depth) {
        Color[] colors = new Color[bytes.length / depth];
        for (int i = 0; i < bytes.length; i += depth) { //4 bytes per pixel
            int r = bytes[i] & 0xFF;
            int g = bytes[i + 1] & 0xFF;
            int b = bytes[i + 2] & 0xFF;
            int a = 255;
            if (depth == 4) {
                a = bytes[i + 3] & 0xFF;
            }
            Color c = Color.resolveColor(r, g, b, a);
            colors[i / depth] = c;
        }
        return colors;
    }

    public Color[] getPixels() {
        return pixels;
    }

    public Color[] getUniqueColors() {
        Set<Color> colors = new HashSet<>();
        for (Color c : this.pixels) {
            if (colors.contains(c)) {
                colors.add(c);
            }
        }
        Color[] output = new Color[colors.size()];
        colors.toArray(output);
        return output;
    }
}
