package com.example.hcipaintframework;


import android.arch.lifecycle.ViewModel;

import com.example.hcipaintframework.Shapes.CanvasShape;


import java.util.ArrayList;

public class SaveStateModel extends ViewModel {

    private ArrayList<CanvasShape> a_shapeList;

    public ArrayList<CanvasShape> getState() {
        if(this.a_shapeList!=null){
            return this.a_shapeList;
        }else{
            return new ArrayList<CanvasShape>();
        }

    }

    public void setState(ArrayList<CanvasShape> shapes) {
        this.a_shapeList = shapes;
    }

}
