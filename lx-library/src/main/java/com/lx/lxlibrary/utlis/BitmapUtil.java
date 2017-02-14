package com.lx.lxlibrary.utlis;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * 创建人：LX
 * 创建日期：2016/8/22
 * 描述：
 */
public class BitmapUtil {
    /**
     * @param bitmap       设置的图片
     * @param saturability 图片的饱和度
     * @return
     */
    public static Bitmap createSaturationBitmap(Bitmap bitmap, float saturability) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap faceIconGreyBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(faceIconGreyBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(saturability);
        ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(colorMatrix);
        paint.setColorFilter(colorMatrixFilter);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return faceIconGreyBitmap;
    }

}
