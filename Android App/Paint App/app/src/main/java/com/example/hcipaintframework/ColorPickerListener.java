package com.example.hcipaintframework;

import android.graphics.Color;
import android.view.View;
import android.content.Context;

import top.defaults.colorpicker.ColorPickerPopup;

public class ColorPickerListener implements View.OnClickListener {

    private Context mContext;
    private PaintState_Model mStateModel;

    public ColorPickerListener(Context c, PaintState_Model model) {
        mContext = c;
        mStateModel = model;
    }

    @Override
    public void onClick(View v) {
        new ColorPickerPopup.Builder(mContext)
                .initialColor(Color.RED) // Set initial color
                .enableAlpha(true) // Enable alpha slider or not
                .okTitle("Choose")
                .cancelTitle("Cancel")
                .showIndicator(true)
                .showValue(true)
                .build()
                .show(v, new ColorPickerPopup.ColorPickerObserver() {
                    @Override
                    public void onColorPicked(int color) {
                        //TODO: will need to change with LiveData
                        mStateModel.setbColor(color);
                    }

                    @Override
                    public void onColor(int color, boolean fromUser) {
                        //TODO: will need to change with LiveData
                        mStateModel.setbColor(color);
                    }
                });
    }

}
