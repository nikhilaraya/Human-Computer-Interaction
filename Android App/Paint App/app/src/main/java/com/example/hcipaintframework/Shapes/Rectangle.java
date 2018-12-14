package com.example.hcipaintframework.Shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.example.hcipaintframework.Shapes.CanvasShape;

public class Rectangle implements CanvasShape {
    public final float x1, y1, x2, y2;
    public final Paint pline;

    public Rectangle(PointF mouseStart, PointF mouseEnd, Paint pline) {

        // Point 1
        this.x1 = mouseStart.x;
        this.y1 = mouseStart.y;

        // Point 2
        this.x2 = mouseEnd.x;
        this.y2 = mouseEnd.y;

        this.pline = pline;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(this.x1, this.y1, this.x2, this.y2, pline);
    }
}