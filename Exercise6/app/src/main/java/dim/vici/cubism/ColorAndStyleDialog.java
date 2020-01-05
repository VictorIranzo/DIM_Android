package dim.vici.cubism;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.flask.colorpicker.ColorPickerView;

public class ColorAndStyleDialog extends DialogFragment {
    private View parent;
    private ColorPickerView colorPickerView;
    private Paint configuredPaint;
    private SeekBar strokeWidthBar;

    public ColorAndStyleDialog()
    {
        // Requires empty constructor.
    }

    public static ColorAndStyleDialog newInstance()
    {
        ColorAndStyleDialog dialog = new ColorAndStyleDialog();

        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP){
                    // Since the dialog is set as not cancelable, but we still want the normal behaviour for the back button
                    dismiss();
                    return true;
                }
                return false;
            }
        });

        parent = getActivity().getLayoutInflater().inflate(R.layout.color_and_style_dialog, null);

        // Configures the paint with the values in the Canvas view.
        configuredPaint = ((MainActivity)getActivity()).canvasView.paint;

        colorPickerView = parent.findViewById(R.id.color_picker_view);
        strokeWidthBar = parent.findViewById(R.id.strokeWidthBar);

        strokeWidthBar.setProgress((int)configuredPaint.getStrokeWidth());

        builder.setView(parent);
        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                configuredPaint.setColor(colorPickerView.getSelectedColor());
                configuredPaint.setStrokeWidth(strokeWidthBar.getProgress());
                
                ((MainActivity)getActivity()).canvasView.setColorAndStyle(configuredPaint);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        return builder.create();
    }
}
