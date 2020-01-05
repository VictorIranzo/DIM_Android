package dim.vici.cubism;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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
    }
}