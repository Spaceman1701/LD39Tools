package org.x2a.picture;

/**
 * Created by ethan on 7/12/17.
 */
public class Color {
    private static final int BITS = 0x0000001F;

    private static final int RED = 0xF800;
    private static final int GREEN = 0x07C0;
    private static final int BLUE = 0x003E;
    private static final int ALPHA = 0x0001;

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

    @Override
    public String toString() {
        int r = red();
        int g = green();
        int b = blue();
        int a = alpha();

        return "Color: <" + r + ", " + g + ", " + b + ", " + a + ">, hex = "
                + Integer.toHexString((int)color & 0xFF);
    }


    public static Color resolveColor(int r, int g, int b, int a) {
        boolean resolvedAlpha = a >= 128;
        r = Math.max(Math.min(r, 255), 0);
        b = Math.max(Math.min(b, 255), 0);
        g = Math.max(Math.min(g, 255), 0);

        r = shrinkRange(r);
        g = shrinkRange(g);
        b = shrinkRange(b);

        return new Color(r, g, b, resolvedAlpha);
    }

    private static int shrinkRange(int v) {
        return v / 8;
    }
}
