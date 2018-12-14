package com.example.hcipaintframework;

import android.view.View;
import android.graphics.PointF;
import android.view.MotionEvent;

import com.example.hcipaintframework.Shapes.CanvasShape;

public class MyCanvasController implements View.OnTouchListener {
    private PointF startPt;
    private PointF endPt;
    private boolean isDragOn;
    private boolean isNeedingPt;

    public MyCanvasController() {
        startPt = new PointF();
        endPt = new PointF();
        isNeedingPt = false;
        isDragOn = false;
    }

    public void resetTouch() {
        isDragOn = false;
        isNeedingPt = false;
        startPt.set(0, 0);
        endPt.set(0, 0);
    }

    public boolean onTouch(View v, MotionEvent event) {
        MyCanvasView view = (MyCanvasView) v;
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:

                if (isNeedingPt) {
                    PointF thirdPt = new PointF(event.getX(), event.getY());
                    view.addShape(startPt, endPt, thirdPt);
                    view.invalidate();
                    isNeedingPt = false;
                } else {
                    isDragOn = false;
                    endPt.set(event.getX(), event.getY());
                    CanvasShape shape = view.addShape(startPt, endPt, null);

                    if (shape == null) {
                        isNeedingPt = true;
                    }
                }
                break;
            case MotionEvent.ACTION_DOWN:
                if (!isNeedingPt) {
                    startPt.set(event.getX(), event.getY());
                    this.isDragOn = true;
                    isNeedingPt = false;
//                    view.invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        return true;
    }
}
