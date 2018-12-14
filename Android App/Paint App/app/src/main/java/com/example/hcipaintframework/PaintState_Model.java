package com.example.hcipaintframework;

import android.graphics.Color;
import android.support.annotation.IntDef;
import android.widget.ToggleButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.MutableLiveData;

import java.lang.annotation.RetentionPolicy;

import com.example.hcipaintframework.databinding.ActivityMainBinding;

import java.lang.annotation.Retention;

// TODO: Later we will convert this to a ViewModel

/**
 * PaintState_Model stores information about the current paint drawing state
 * such as the color to draw, the shape to be drawn and the whether rectangles
 * and ovals should be filled.
 * Comments are limited since the functions themselves seem self-explanatory.
 */
public class PaintState_Model {
    private @Shape
    int currShape;

    private int bColor;
    private int brushSize;
    private boolean fillShape;

    public PaintState_Model() {
        currShape = DLINE;
        bColor = Color.BLUE;
        brushSize = 10;
        fillShape = false;
    }

    // The shape interface if one of the
    // the following enumerated data types.
    // This gives an enumerated data type
    public static final int DLINE = 0;
    public static final int DCURVE = 1;
    public static final int DRECT = 2;
    public static final int DOVAL = 3;


    @IntDef({DLINE, DCURVE, DRECT, DOVAL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Shape {
    }

    // TODO: the following code will be migrated elsewhere when you use LiveData in a later assignment
    public void setCurrShape(@Shape int shape) {
        // Presuming none of these components are null.
        currShape = shape;
    }

    /**
     * getCurrShape() returns the current shape the model is considering
     *
     * @return Shape enumerated data type.
     */
    public @Shape
    int getCurrShape() {
        return currShape;
    }

    public void setFillOn(boolean fill) {
        fillShape = fill;
    }

    public boolean isFillShape() {
        return fillShape;
    }

    public int getBrushSize() {
        return brushSize;
    }

    public void setBrushSize(int size) {
        brushSize = size;
    }

    public void setbColor(int color) {
        bColor = color;
    }

    public int getbColor() {
        return bColor;
    }

}
