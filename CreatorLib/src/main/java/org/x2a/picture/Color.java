package org.x2a.picture;

/**
 * Created by ethan on 7/12/17.
 */
public class Color {
    private static final int BITS = 0x0000001F;

    private static final short RED = (short)0xF800;
    private static final short GREEN = (short)0x07C0;
    private static final short BLUE = (short)0x003E;
    private static final short ALPHA = (short)0x0001;

    private static final int RED_SHIFT = 11;
    private static final int GREEN_SHIFT = 6;
    private static final int BLUE_SHIFT = 1;

    private final short color;

    public Color(int r, int g, int b, boolean a) {
        if ((r & BITS) != r || (g & BITS) != g || (b & BITS) != b) {
            throw new IllegalArgumentException("invalid color values");
        }
        int alpha = a ? 1 : 0;
        color = (short) ((r << 11) + (g << 6) + (b << 1) + alpha);
    }

    public short getColor() {
        return color;
    }

    public byte red() {
        return (byte) ((color & RED) >>> RED_SHIFT);
    }

    public byte green() {
        return (byte) ((color & GREEN) >>> GREEN_SHIFT);
    }

    public byte blue() {
        return (byte) ((color & BLUE) >>> BLUE_SHIFT);
    }

    public byte alpha() {
        return (byte) (color & ALPHA);
    }

    public boolean isAlpha() {
        return (color & ALPHA) != 0;
    }

    public java.awt.Color toAwtColor() {
        return new java.awt.Color(red(), green(), blue(), alpha());
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Color) {
            return color == ((Color) other).getColor();
        }
        return false;
    }
}
