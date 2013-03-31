package bsp.fileloader;


/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

import org.apache.poi.hslf.usermodel.*;
//import org.apache.poi.hslf.model.*;

import javax.imageio.ImageIO;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.Rectangle2D;

/**
 * Demonstrates how you can use HSLF to convert each slide into a PNG image
 *
 * @author Yegor Kozlov
 */
public final class PPT2PNG {

    public static void main(String args[]) throws Exception {
    	
    	String file = null;
    	float scale = (float) 2;
    	
        file = "C:/Users/aaron/Documents/UWaterloo/temp/Presentation_1.ppt";
        

        FileInputStream is = new FileInputStream(file);
        SlideShow ppt = new SlideShow(is);
        is.close();

        Dimension pgsize = ppt.getPageSize();
        int width = (int)(pgsize.width*scale);
        int height = (int)(pgsize.height*scale);

        org.apache.poi.hslf.model.Slide[] slide = ppt.getSlides();
        for (int i = 0; i < slide.length; i++) {

            String title = slide[i].getTitle();
            System.out.println("Rendering slide "+slide[i].getSlideNumber() + (title == null ? "" : ": " + title));

            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = img.createGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);

            graphics.setPaint(Color.white);
            graphics.fill(new Rectangle2D.Float(0, 0, width, height));

            graphics.scale((double)width/pgsize.width, (double)height/pgsize.height);

            slide[i].draw(graphics);

            String fname = file.replaceAll("\\.ppt", "-" + (i+1) + ".png");
            FileOutputStream out = new FileOutputStream(fname);
            ImageIO.write(img, "png", out);
            out.close();
        }
    }

    
}