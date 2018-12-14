package com.example.hcipaintframework;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.util.ArrayList;

import android.graphics.PointF;
import android.view.View;

import com.example.hcipaintframework.Shapes.CanvasShape;


public class MyCanvasView extends View {
    private PaintState_Model model;

    private ArrayList<CanvasShape> a_shapeList;

    public MyCanvasView(Context context) {
        super(context);
        MyCanvasController controller = new MyCanvasController();
        a_shapeList = new ArrayList<>();
        this.setOnTouchListener(controller);
    }

    public MyCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        MyCanvasController controller = new MyCanvasController();
        a_shapeList = new ArrayList<>();
        this.setOnTouchListener(controller);
    }

    // Returns the  a_shapeList arraylist
    public ArrayList<CanvasShape> getShapeList() {

        return a_shapeList;
    }

    // Adds the CanvasShape to the a_shapeList arraylist
    public void setA_shapeList(ArrayList<CanvasShape> a_shape) {
        this.a_shapeList = a_shape;
    }

    // Setter method to return the PaintState_Model object
    public void setStateModel(PaintState_Model pvm) {
        model = pvm;
    }

    // Getter method to return the PaintState_Model object
    public PaintState_Model getStateModel() {
        return model;
    }

    /**
     * TO ADD: you don't need to write this function but I think it would be useful.
     * addShape takes a start, end and (optional) guide point
     * and generates a shape based on the current paint state.
     *
     * @param startPt start point or minimum point
     * @param endPt   end point or maximum point
     * @param guidePt point used for curves but null for other shapes
     * @return the generated shape based on the paint state. If a curve
     * with no guide point or an error occurs then return null.
     */
    public CanvasShape addShape(PointF startPt, PointF endPt, PointF guidePt) {
        CanvasShape cs = ShapeFactory.makeShape(model.getCurrShape(),
                startPt, endPt, guidePt,
                model.getBrushSize(), model.getbColor(),
                model.isFillShape());


        // Check if the canvas shape is empty, canvas shape object is null
        // when the guide point is not selected in the case of curve shape
        if (cs != null) {
            this.a_shapeList.add(cs);
        }
        invalidate();
        return cs;
    }

    // clears the array list a_shapeList and also calls the overrided draw method
    public void clear() {
        a_shapeList.clear();
        invalidate();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (!this.a_shapeList.isEmpty()) {
            for (CanvasShape cv : this.a_shapeList) {
                cv.draw(canvas);
            }
        }
    }
}
