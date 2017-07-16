package org.x2a.picture;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by ethan on 7/13/17.
 */
public class TestPicture {

    @Test
    public void createPictureFromPng() throws IOException {
        File testPng = new File(getClass().getClassLoader().getResource("test.png").getFile());
        PngImage image = new PngImage(testPng);
        Assert.assertEquals(8, image.getWidth());
        Assert.assertEquals(8, image.getHeight());
        Assert.assertEquals(8 * 8 * 3, image.getBytes().length);
        Picture picture = new Picture(image.getBytes(), image.isHasAlpha());
    }
}
