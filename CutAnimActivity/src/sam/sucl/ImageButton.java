package sam.sucl;

import java.io.InputStream;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class ImageButton 
{
    /**按钮图片**/
    private Bitmap mBitButton = null;
    /**图片绘制的XY坐标**/
    private int mPosX =0;
    private int mPosY =0;

    /**图片绘制的宽高**/
    private int mWidth =0;
    private int mHeight =0;
  
    /* *************************************************************************************************
     *       DESC :      构造函数负责获得一个BitMap
     *       ARGC :
     *                      @context:        
     *                      @frameBitmapID:
     *                      @pointX
     *                      @pointY        
     *---------------------------------------------------------------------------------------------------
     ****************************************************************************************************/         
    public ImageButton(Context context, int frameBitmapID, int pointX, int pointY) 
    {
		mBitButton = ReadBitMap(context,frameBitmapID);
		mPosX = pointX;
		mPosY = pointY;
		mWidth = mBitButton.getWidth();
		mHeight = mBitButton.getHeight();
    }
    
    
    /* *************************************************************************************************
     *       DESC :      绘制图片按钮
     *       ARGC :
     *                      @canvas:        画图的一个工具类
     *                      @paint:           画图工具
     *---------------------------------------------------------------------------------------------------
     ****************************************************************************************************/          
    public void DrawImageButton(Canvas canvas, Paint paint) 
    {
    	canvas.drawBitmap(mBitButton, mPosX, mPosY, paint);
    }

    /* *************************************************************************************************
     *       DESC :    通过画布的旋转来实现文字的显示效果
     *       ARGC :
     *---------------------------------------------------------------------------------------------------
     ****************************************************************************************************/  
    void drawText(Canvas canvas ,String text , float x ,float y,Paint paint ,float angle)
    {
    	if(angle != 0)
        {
        	 /* 对画布旋转，再画文本 */
            canvas.rotate(angle, x, y);   
        }
        canvas.drawText(text, x, y, paint);
        
        /*  使用画布恢初始状，以便画下一句文字时再进行旋转*/
        if(angle != 0)
        {
            canvas.rotate(-angle, x, y); 
        }
    }
    
    public void DrawSubjectAndAnswer(Canvas canvas,Paint paint,GlobalData subject1,GlobalData subject2)
    {
    	try
    	{
	        Paint painter = new Paint();                
	        painter.setColor(Color.WHITE);
	        painter.setTextSize(20);                
	        canvas.drawLine(100, 100, 100, 400, painter);
	        drawText(canvas,subject1.MSubjectBody, 58, 388, painter,-90);
	        
	        painter.setColor(Color.RED);
	        painter.setTextSize(40);
	        drawText(canvas,"A " + subject1.MAnswer, 258, 180, painter,0);
	        
	        paint.setColor(Color.BLUE);
	        drawText(canvas,"B " + subject2.MAnswer, 258, 80, painter,0);
	        canvas.drawLine(100, 100, 400, 100, painter);
    	}
    	catch(Exception e)
    	{
    	}
        
//   Relate sentences:    	
//        Paint painter = new Paint();                
//        painter.setColor(Color.WHITE);
//        painter.setTextSize(20);                
//        canvas.drawLine(100, 100, 100, 400, painter);
//        drawText(canvas,"Hello", 80, 200, painter,-90);        
//        
//        painter.setColor(Color.RED);
//        painter.setTextSize(40);
//        //drawText(canvas,"free", 150, 180, painter,-45);
//        drawText(canvas,"free", 150, 180, painter,-90);
//        
//        paint.setColor(Color.BLUE);
//        drawText(canvas,"World", 150, 80, painter,0);
//        canvas.drawLine(100, 100, 400, 100, painter);
    }
    
    
    /* *************************************************************************************************
     *       DESC :      判断是否点中图片按钮
     *       ARGC :
     *                      @pointX:        X坐标
     *                      @pointY:        Y坐标
     *---------------------------------------------------------------------------------------------------
     ****************************************************************************************************/            
    public boolean IsClick(int pointX, int pointY) 
    {
		boolean isClick = false;
		if (pointX >= mPosX && pointX <= mPosX + mWidth &&
		    pointY >= mPosY && pointY <= mPosY + mHeight) 
		{
		    isClick = true;
		}
		return isClick;
    }
    
    /* *************************************************************************************************
     *       DESC :      读取图片资源
     *       ARGC :
     *                      @context:      上下文
     *                      @resId:           谁的ID？
     *          RET:		返回一个图片对象。	            
     *---------------------------------------------------------------------------------------------------
     ****************************************************************************************************/        
    public Bitmap ReadBitMap(Context context, int resId) 
    {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		
		// 获取资源图片
		InputStream is = context.getResources().openRawResource(resId);
		return BitmapFactory.decodeStream(is, null, opt);
    }
}
