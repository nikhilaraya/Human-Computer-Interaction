package com.example.hcipaintframework;

import android.graphics.PointF;
import android.graphics.Paint;

import com.example.hcipaintframework.Shapes.CanvasShape;
import com.example.hcipaintframework.Shapes.Curve;
import com.example.hcipaintframework.Shapes.Line;
import com.example.hcipaintframework.Shapes.Oval;
import com.example.hcipaintframework.Shapes.Rectangle;

public class ShapeFactory {

    // Returns the CanvasShape object for the user input of shape, start point,end point,guidepoint,
    // brush size, color and fill flag.
    public static CanvasShape makeShape(@PaintState_Model.Shape int shape, final PointF mouseStart,
                                        final PointF mouseMid, final PointF mouseEnd,
                                        int brush, int color, boolean fill) {

        //Defining the Paint object with the user input of color, brush size and fill option
        final Paint pline = new Paint();
        pline.setColor(color);
        pline.setStrokeWidth(brush);
        if (fill) {
            pline.setStyle(Paint.Style.FILL);
        } else {
            pline.setStyle(Paint.Style.STROKE);
        }
        CanvasShape cv = null;

        // implementing the draw method of CanvasShape interface for the shape Line
        if (shape == 0) {
            cv = new Line(mouseStart, mouseMid, pline);
        }

        // implementing the draw method of CanvasShape interface for the shape Curve
        if (shape == 1) {
            // Return null when the quide point is not selected
            if (mouseEnd == null) {
                cv = null;
            } else {
                // Return null when the all the points are selected
                cv = new Curve(mouseStart, mouseMid, mouseEnd, pline);
            }
        }
        // implementing the draw method of CanvasShape interface for the shape rectangle
        if (shape == 2) {
            cv = new Rectangle(mouseStart, mouseMid, pline);
        }
        // implementing the draw method of CanvasShape interface for the shape Oval
        if (shape == 3) {
            cv = new Oval(mouseStart, mouseMid, pline);
        }

        return cv;
    }
}
