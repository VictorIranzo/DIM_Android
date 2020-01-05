package dim.vici.cubism;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyView canvasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton drawFreeButton = findViewById(R.id.draw_free_button);
        ImageButton drawSquaresButton = findViewById(R.id.draw_squares_button);
        ImageButton drawFigureButton = findViewById(R.id.draw_free_figure_button);
        ImageButton colorsButton = findViewById(R.id.colors_button);

        canvasView = findViewById(R.id.viewInstance);

        drawFreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),R.string.draw_free, Toast.LENGTH_SHORT).show();

                canvasView.TypeDraw = TypeDraw.FREE;
            }
        });

        drawSquaresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),R.string.draw_squares, Toast.LENGTH_SHORT).show();

                canvasView.TypeDraw = TypeDraw.SQUARES;
            }
        });

        drawFigureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),R.string.draw_figures, Toast.LENGTH_SHORT).show();

                canvasView.TypeDraw = TypeDraw.FIGURE;
            }
        });

        colorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorAndStyleDialog dialog = ColorAndStyleDialog.newInstance();
                dialog.setCancelable(false);

                dialog.show(getSupportFragmentManager(), "dialog");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.set_Background:
                ShowSelectorBackground();
        }
        return super.onOptionsItemSelected(item);
    }

    private void ShowSelectorBackground() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add(getString(R.string.none));
        arrayAdapter.add(getString(R.string.mondrian));
        arrayAdapter.add(getString(R.string.picasso));
        arrayAdapter.add(getString(R.string.braque));
        arrayAdapter.add(getString(R.string.gris));

        builderSingle.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(canvasView.getContext());
                builderInner.setMessage(strName);
                builderInner.setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        dialog.dismiss();
                    }
                });
                builderInner.show();
            }
        });
        builderSingle.show();
    }
}