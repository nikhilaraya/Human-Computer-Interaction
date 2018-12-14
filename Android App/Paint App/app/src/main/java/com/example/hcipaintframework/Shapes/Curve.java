package com.example.hcipaintframework.Shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

import com.example.hcipaintframework.Shapes.CanvasShape;

public class Curve implements CanvasShape {
    public final float x1, y1, x2, y2, x3, y3;
    public final Paint pline;

    public Curve(PointF mouseStart, PointF mouseEnd, PointF midPoint, Paint pline) {

        // Point 1
        this.x1 = mouseStart.x;
        this.y1 = mouseStart.y;

        // Point 2
        this.x2 = mouseEnd.x;
        this.y2 = mouseEnd.y;

        // Point 3
        this.x3 = midPoint.x;
        this.y3 = midPoint.y;

        this.pline = pline;
    }

    @Override
    public void draw(Canvas canvas) {
        Path pth = new Path();
        pth.moveTo(this.x1, this.y1);
        pth.quadTo(this.x3, this.y3, this.x2, this.y2);
        canvas.drawPath(pth, pline);
    }
}