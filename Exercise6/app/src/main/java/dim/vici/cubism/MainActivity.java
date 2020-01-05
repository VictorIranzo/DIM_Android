package dim.vici.cubism;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
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

        final ArrayAdapter<Painting> arrayAdapter = new ArrayAdapter<Painting>(this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add(new Painting(R.string.none, getString(R.string.none)));
        arrayAdapter.add(new Painting(R.string.mondrian, getString(R.string.mondrian)));
        arrayAdapter.add(new Painting(R.string.picasso, getString(R.string.picasso)));
        arrayAdapter.add(new Painting(R.string.braque, getString(R.string.braque)));
        arrayAdapter.add(new Painting(R.string.gris, getString(R.string.gris)));

        builderSingle.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Painting itemSelected = arrayAdapter.getItem(which);
                int resourceId = -1;

                switch (itemSelected.id)
                {
                    case R.string.none:
                        break;
                    case R.string.mondrian:
                        resourceId = R.drawable.mondrian;
                        break;
                    case R.string.picasso:
                        resourceId = R.drawable.picasso;
                        break;
                    case R.string.braque:
                        resourceId = R.drawable.braque;
                        break;
                    case R.string.gris:
                        resourceId = R.drawable.gris;
                        break;
                }

                if (resourceId == -1)
                {
                    canvasView.painting = null;
                }
                else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
                {
                    Drawable image = getResources().getDrawable(resourceId, null);
                    image.setBounds(canvasView.getLeft(), canvasView.getTop(), canvasView.getRight(), canvasView.getBottom());

                    canvasView.painting = image;
                }

                canvasView.invalidate();
            }
        });
        builderSingle.show();
    }
}