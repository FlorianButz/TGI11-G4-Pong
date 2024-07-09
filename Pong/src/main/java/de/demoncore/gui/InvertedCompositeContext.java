package de.demoncore.gui;

import java.awt.CompositeContext;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

public class InvertedCompositeContext implements CompositeContext {
    @Override
    public void dispose() {
        // No resources to dispose
    }

    @Override
    public void compose(Raster src, Raster dstIn, WritableRaster dstOut) {
        int width = Math.min(src.getWidth(), dstIn.getWidth());
        int height = Math.min(src.getHeight(), dstIn.getHeight());

        int[] srcPixels = new int[width * height * 4];
        int[] dstPixels = new int[width * height * 4];

        src.getPixels(0, 0, width, height, srcPixels);
        dstIn.getPixels(0, 0, width, height, dstPixels);

        for (int i = 0; i < srcPixels.length; i += 4) {
            dstPixels[i] = 255 - srcPixels[i]; // Red
            dstPixels[i + 1] = 255 - srcPixels[i + 1]; // Green
            dstPixels[i + 2] = 255 - srcPixels[i + 2]; // Blue
            dstPixels[i + 3] = srcPixels[i + 3]; // Alpha (unchanged)
        }

        dstOut.setPixels(0, 0, width, height, dstPixels);
    }
}
