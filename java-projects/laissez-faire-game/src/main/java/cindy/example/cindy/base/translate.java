package cindy.example.cindy.base;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class translate extends ProcessingFX {
    /**
     * Translate.
     * <p>
     * The translate() function allows objects to be moved
     * to any location within the window. The first parameter
     * sets the x-axis offset and the second parameter sets the
     * y-axis offset.
     */

    double x, y;
    double dim = 80.0;

    @Override
    protected void setup(GraphicsContext pen) {
        //size(640, 360);
        pen.clearRect(0,0,1030,700);

    }

    @Override
    protected void draw(GraphicsContext pen) {
        window.setBackground(Color.rgb(102, 102, 102));

        x = x + 0.8;

        if (x > 1030 + dim) {
            x = -dim;
        }

        matrix.translate(x, 500 / 2 - dim / 2);
        pen.setFill(Color.rgb(255, 255, 255));
        pen.fillRect(-dim / 2, -dim / 2, dim, dim);

        // Transforms accumulate. Notice how this rect moves
        // twice as fast as the other, but it has the same
        // parameter for the x-axis value
        matrix.translate(x, dim);
        pen.setFill(Color.rgb(0, 0, 0));
        pen.rect(-dim / 2, -dim / 2, dim, dim);
    }


}
