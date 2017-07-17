package org.x2a.picture;

import java.util.*;

/**
 * Created by ethan on 7/13/17.
 */
public class Pallet {

    public static final int PALLET_SIZE = 16;

    private final List<Color> colors;

    public Pallet(byte[] bytes, boolean hasAlpha) {
        int depth = hasAlpha ? 4 : 3;
        if (bytes.length % depth != 0) {
            throw new IllegalArgumentException("Invalid image size!");
        }
        if (bytes.length / depth != PALLET_SIZE) {
            throw new IllegalArgumentException("Invalid image size");
        }
        colors = new ArrayList<>();
        for (int i = 0; i < bytes.length; i += depth) { //4 bytes per pixel
            int r = bytes[i] & 0xFF;
            int g = bytes[i + 1] & 0xFF;
            int b = bytes[i + 2] & 0xFF;
            int a = 255;
            if (depth == 4) {
                a = bytes[i + 3] & 0xFF;
            }
            Color c = Color.resolveColor(r, g, b, a);
            colors.add(c);
        }
    }

    public List<Color> getColors() {
        return colors;
    }

    public boolean canDefinePicture(Picture picture) {
        return Arrays.stream(picture.getUniqueColors()).allMatch(this.colors::contains);
    }
}
