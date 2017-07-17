package org.x2a.picture;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

/**
 * Created by ethan on 7/12/17.
 */
public class TestColor {


    @Test(expected = IllegalArgumentException.class)
    public void testBadValue() {
        int red = 0xFF;
        int green = 0x0F;
        int blue = 0x0F;

        boolean alpha = false;

        Color c = new Color(red, green, blue, alpha);
    }

    @Test
    public void testColorRecreate() {
        int red = 0x0F;
        int green = 0x0F;
        int blue = 0x0F;
        boolean alpha = false;

        Color c1 = new Color(red, green, blue, alpha);
        Assert.assertNotNull(c1);
        Assert.assertTrue(c1.getColor() != 0);

        byte r1 = c1.red();
        byte g1 = c1.green();
        byte b1 = c1.blue();
        boolean isAlpha = c1.isAlpha();

        Color c2 = new Color(r1, g1, b1, isAlpha);
        Assert.assertNotNull(c2);
        Assert.assertEquals(c1, c2);
    }


    @Test
    public void testResolveColorBounded() {
        long seed = 0xF0F0F0F;
        Random random = new Random(seed);

        for (int i = 0; i < 100; i++) {
            int r = random.nextInt(256);
            int b = random.nextInt(256);
            int g = random.nextInt(256);
            int a = random.nextInt(256);

            Color c = Color.resolveColor(r, g, b, a);
            Assert.assertNotNull(c.toString(), c);

            if (r % 32 == 0) {
                Assert.assertEquals(c.toString(), (r / 8), (int)c.red() & 0xFF);
            }
        }
    }

    @Test
    public void testResolveWhite() {
        int r = 255;
        int g = 255;
        int b = 255;
        int a = 255;

        Color c = Color.resolveColor(r, g, b, a);
        Assert.assertEquals(0xFFFF, (c.getColor() & 0xFFFF));
    }
}
