package org.framework.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.madgag.gif.fmsware.AnimatedGifEncoder;

public class GenerateGif {

	public boolean generate() throws IOException{
		
		
		
		BufferedImage src1 = ImageIO.read(new File("Img221785570.jpg"));
        BufferedImage src2 = ImageIO.read(new File("W.gif"));
        //BufferedImage src3 = ImageIO.read(new File("c:/ship3.jpg")); 
        AnimatedGifEncoder e = new AnimatedGifEncoder(); 
        e.setRepeat(0); 
        e.start("laoma.gif"); 
        e.setDelay(300); // 1 frame per sec 
        e.addFrame(src1); 
        e.setDelay(100); 
        e.addFrame(src2); 
        e.setDelay(100); 
    //  e.addFrame(src2); 
        e.finish();
		return false;
	}
}
