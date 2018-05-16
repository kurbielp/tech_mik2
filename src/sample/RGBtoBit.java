package sample;

import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

import javafx.embed.swing.SwingFXUtils;
public class RGBtoBit {


   // BufferedImage img =null;


    int[] filter = {1, 2, 1, 2, 4, 2, 1, 2, 1};
    int filterWidth = 3;

    //BufferedImage blurred = blur(img, filter, filterWidth);

    public  BufferedImage blur(BufferedImage image, int[] filter, int filterWidth) {
        if (filter.length % filterWidth != 0) {
            throw new IllegalArgumentException("filter contains a incomplete row");
        }

        final int width = image.getWidth();
        final int height = image.getHeight();
        int sum = IntStream.of(filter).sum();
        if (sum == 0) sum = 1;

        int[] input = image.getRGB(0, 0, width, height, null, 0, width);

        int[] output = new int[input.length];

        final int pixelIndexOffset = width - filterWidth;
        final int centerOffsetX = filterWidth / 2;
        final int centerOffsetY = filter.length / filterWidth / 2;

        // apply filter
        for (int h = height - filter.length / filterWidth + 1, w = width - filterWidth + 1, y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int r = 0;
                int g = 0;
                int b = 0;
                for (int filterIndex = 0, pixelIndex = y * width + x;
                     filterIndex < filter.length;
                     pixelIndex += pixelIndexOffset) {
                    for (int fx = 0; fx < filterWidth; fx++, pixelIndex++, filterIndex++) {
                        int col = input[pixelIndex];
                        int factor = filter[filterIndex];

                        // sum up color channels seperately
                        r += ((col >>> 16) & 0xFF) * factor;
                        g += ((col >>> 8) & 0xFF) * factor;
                        b += (col & 0xFF) * factor;
                    }
                }
                r /= sum;
                g /= sum;
                b /= sum;
                // combine channels with full opacity
                output[x + centerOffsetX + (y + centerOffsetY) * width] = (r << 16) | (g << 8) | b | 0xFF000000;
            }
        }

        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        result.setRGB(0, 0, width, height, output, 0, width);
        return result;
    }
    /*
    public BufferedImage usreniajacy (BufferedImage image, int[] filter, int filterWidth) {
        if (filter.length % filterWidth != 0) {
            throw new IllegalArgumentException("filter contains a incomplete row");
        }

        final int width = image.getWidth();
        final int height = image.getHeight();
        final int sum = IntStream.of(filter).sum();

        int[] input = image.getRGB(0, 0, width, height, null, 0, width);

        int[] output = new int[input.length];


        int rsume, gsume, bsume, graysume;
        int margin = ((sum-1)/2);
        for (int i=margin; i<ObrazKolorowy->width-margin; i++)
            for (int j=margin; j<ObrazKolorowy->height-margin; j++)
            {
                rsume = 0;
                gsume = 0;
                bsume = 0;
                for (int k=0; k<sum; k++)
                    for (int l=0; l<sum; l++)
                    {
                        rsume += filter[k*sum+l]*red[i+k-margin][j+l-margin];
                        gsume += filter[k*sum+l]*green[i+k-margin][j+l-margin];
                        bsume += filter[k*Sum+l]*blue[i+k-margin][j+l-margin];
                    }
                rsume /= Norm;
                gsume /= Norm;
                bsume /= Norm;

                if (rsume > 255) rsume = 255;
                else if (rsume < 0) rsume = 0;
                if (gsume > 255) gsume = 255;
                else if (gsume < 0) gsume = 0;
                if (bsume > 255) bsume = 255;
                else if (bsume < 0) bsume = 0;

                WynikKolorowy->Canvas->Pixels[i][j] = (TColor)rsume + (gsume << 8) + (bsume << 16);
                output[x + centerOffsetX + (y + centerOffsetY) * width] = (r << 16) | (g << 8) | b | 0xFF000000;
            }

        return result;
    }
    */
}

