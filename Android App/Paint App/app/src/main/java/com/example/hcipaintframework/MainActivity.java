package com.example.hcipaintframework;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.databinding.DataBindingUtil;
import android.widget.Switch;
import android.widget.Toast;

import com.example.hcipaintframework.Shapes.CanvasShape;
import com.example.hcipaintframework.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private ActivityMainBinding binding;
    private PaintState_Model stateModel;
    private PaintState_Controller stateController;
    private MyCanvasView canvas;
    private Spinner brushSpinner;
    private SaveStateModel currentState;

    @Override
    protected void onStart() {
        super.onStart();
        if (binding != null) {

            binding.ColorPicker.setOnClickListener(new ColorPickerListener(this, stateModel));

//            binding.TogBLine.setOnClickListener(stateController);

            // Extracting the RadioButton Ids to set the togglepairs
            RadioGroup radioShapeGroup = findViewById(R.id.radioShape);
            int noOfRadioButtons = radioShapeGroup.getChildCount();
            for (int i = 0; i < noOfRadioButtons; i++) {
                System.out.print("number of radio buttons" + radioShapeGroup.getChildCount());
                RadioButton shape = (RadioButton) radioShapeGroup.getChildAt(i);
                if (shape.getText().equals("Line"))
                    stateController.addIDControlPair(shape.getId(), PaintState_Model.DLINE);
                else if (shape.getText().equals("Rect"))
                    stateController.addIDControlPair(shape.getId(), PaintState_Model.DRECT);
                else if (shape.getText().equals("Oval"))
                    stateController.addIDControlPair(shape.getId(), PaintState_Model.DOVAL);
                else if (shape.getText().equals("Curve"))
                    stateController.addIDControlPair(shape.getId(), PaintState_Model.DCURVE);
            }

            // Setting the listener on the RadioGroup to detect changes on the shapes selected by the user
            radioShapeGroup.setOnCheckedChangeListener(stateController);

            // Setting the listener on the Spinner to detect changes on the brush sizes selected by the user
            brushSpinner.setSelection(0, false);
            brushSpinner.setOnItemSelectedListener(stateController);

            // Setting the listener on the Switch to detect changes on the fill option selected by the user
            Switch fillColor = findViewById(R.id.switch1);
            fillColor.setOnCheckedChangeListener(stateController);

            Button clearBtn = findViewById(R.id.clearBtn);
            clearBtn.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    canvas.clear();
                }});
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setFontSpinner();
        stateModel = new PaintState_Model();
        canvas = binding.canvasView;
        canvas.setStateModel(stateModel);
        stateController = new PaintState_Controller(stateModel);
        canvas.setStateModel(stateModel);

        currentState = ViewModelProviders.of(this).get(SaveStateModel.class);
        if(canvas.getShapeList().isEmpty()){
            canvas.setA_shapeList(currentState.getState());
        }
        if(this.getResources().getConfiguration().orientation == 2){
            Toast.makeText(this,"Screen Rotated - LANDSCAPE", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        currentState.setState(canvas.getShapeList());
    }

    public ViewModel getCurrentState() {
        return currentState;
    }

    /**
     * Programmatically setting the brush size values in the spinner
     * rather than simply using XML
     *
     * @return Spinner View that was altered.
     */
    private Spinner setFontSpinner() {
        brushSpinner = (Spinner) findViewById(R.id.spinner3);
        // gets the list from the string.xml
        ArrayAdapter<String> brushSizeData = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources()
                .getStringArray(R.array.brush_sizes));
        brushSpinner.setAdapter(brushSizeData);
        // return the brushSpinner with the corresponding data
        return brushSpinner;
    }

    public void setShape(@PaintState_Model.Shape int shape) {
        //TODO: to be modified when ViewModelProviders are used.
        if (stateModel != null) {
            stateModel.setCurrShape(shape);
        }
//        if (binding != null) {
//            switch (shape) {
//                case PaintState_Model.DOVAL:
//                    binding.TogBLine.setChecked(false);
//                    break;
//                case PaintState_Model.DCURVE:
//                    binding.TogBLine.setChecked(false);
//                    break;
//                case PaintState_Model.DLINE:
//                    binding.TogBLine.setChecked(true);
//                    break;
//                default:
//                    binding.TogBLine.setChecked(false);
//                    break;
//            }
//        }
    }

}
