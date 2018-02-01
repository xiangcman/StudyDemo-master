package com.single.studydemo.imageloader.displayer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

/**
 * Created by xiangcheng on 18/2/1.
 * 画椭圆的图片
 */

public class OvalDisplayer implements BitmapDisplayer {
    @Override
    public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
        imageAware.setImageDrawable(new OvalDrawable(bitmap));
    }

    static class OvalDrawable extends Drawable {

        private Bitmap bitmap;
        private Path ovalPath;
        private Paint bitmapPaint;

        public OvalDrawable(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        @Override
        public void draw(@NonNull Canvas canvas) {
            int layer = canvas.saveLayer(getBounds().left, getBounds().top, getBounds().right, getBounds().bottom, null, Canvas.ALL_SAVE_FLAG);
            //先绘制dst层
            canvas.drawPath(ovalPath, bitmapPaint);
            bitmapPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            //src层
            canvas.drawBitmap(bitmap, 0, 0, bitmapPaint);
            bitmapPaint.setXfermode(null);
            canvas.restoreToCount(layer);
        }

        @Override
        public void setAlpha(int alpha) {

        }

        @Override
        protected void onBoundsChange(Rect bounds) {
            super.onBoundsChange(bounds);
            //获取到宽高的缩放比例
            float scaleWidth = (float) (bounds.width() * 1.0 / bitmap.getWidth());
            float scaleHeight = (float) (bounds.height() * 1.0 / bitmap.getHeight());
            Matrix matrix = new Matrix();
            //需要对图片进行缩放
            matrix.setScale(scaleWidth, scaleHeight);
            //获取到缩放后的bitmap对象
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

            ovalPath = new Path();
            bitmapPaint = new Paint();
            //添加dst层的椭圆
            ovalPath.addOval(bounds.left, bounds.top, bounds.right, bounds.bottom, Path.Direction.CCW);

        }

        @Override
        public void setColorFilter(@Nullable ColorFilter colorFilter) {

        }

        @Override
        public int getOpacity() {
            return PixelFormat.TRANSLUCENT;
        }
    }

}
