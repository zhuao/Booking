package com.thoughtworks.android.booking.ui.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by hxxu on 1/7/16.
 */
public class RoundImageView extends ImageView {
    public RoundImageView(Context context) {
        super(context);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();

        if (drawable == null){
            return;
        }

        if (getWidth() == 0 || getHeight() == 0){
            return;
        }

        Bitmap originBitmap =  ((BitmapDrawable)drawable).getBitmap();
        Bitmap convertBitmap = originBitmap.copy(Bitmap.Config.ARGB_8888, true);

        Bitmap roundBitmap = getCroppedBitmap(convertBitmap,getWidth());
        canvas.drawBitmap(roundBitmap,0,0,null);

    }

    public static Bitmap getCroppedBitmap(Bitmap bitmap,int radius){
        Bitmap roundBitmap;
        if (bitmap.getWidth() != radius || bitmap.getHeight() != radius){
            float smallest = Math.min(bitmap.getWidth(),bitmap.getHeight());
            float factor = smallest / radius;
            roundBitmap = bitmap.createScaledBitmap(bitmap,(int)(bitmap.getWidth()/factor),(int)(bitmap.getHeight()/factor),false);
        }else {
            roundBitmap = bitmap;
        }

        Bitmap output = Bitmap.createBitmap(radius,radius, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, radius, radius);

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(radius / 2 + 0.7f, radius / 2 + 0.7f,
                radius / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(roundBitmap, rect, rect, paint);

        return output;
    }

}
