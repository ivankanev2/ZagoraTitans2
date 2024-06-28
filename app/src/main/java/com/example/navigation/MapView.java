package com.example.navigation;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import com.github.chrisbanes.photoview.PhotoView;
import java.util.ArrayList;
import java.util.List;

/**
 * A custom view that extends PhotoView to display a map with points and arrows.
 */
public class MapView extends PhotoView {
    // Paint object for drawing lines
    private Paint paint;
    // Paint object for drawing arrow heads
    private Paint arrowPaint;
    // List of points to be drawn on the map
    private List<float[]> points;
    // Matrix for transforming the canvas
    private Matrix matrix;

    /**
     * Constructor for the MapView.
     *
     * @param context The context in which the view is being used.
     * @param attrs   The attributes of the XML tag that is inflating the view.
     */
    public MapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(); // Initialize the view
    }

    /**
     * Initializes the view by setting up the paints and the list of points.
     */
    private void init() {
        // Set up the paint for drawing lines
        paint = new Paint();
        paint.setColor(Color.RED); // Set the color to red
        paint.setStrokeWidth(12); // Set the stroke width to 12
        paint.setStyle(Paint.Style.STROKE); // Set the style to stroke

        // Set up the paint for drawing arrow heads
        arrowPaint = new Paint();
        arrowPaint.setColor(Color.RED); // Set the color to red
        arrowPaint.setStyle(Paint.Style.FILL); // Set the style to fill

        // Initialize the list of points
        points = new ArrayList<>();

        // Initialize the matrix for transforming the canvas
        matrix = new Matrix();
    }

    /**
     * Sets the list of points to be drawn on the map.
     *
     * @param points The list of points to be drawn.
     */
    public void setPoints(List<float[]> points) {
        this.points = points;
        invalidate(); // Request a redraw of the view
    }

    /**
     * Draws the map with points and arrows.
     *
     * @param canvas The canvas on which to draw.
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.getDisplayMatrix(matrix);
        canvas.save();
        canvas.concat(matrix);

        // Draw lines between points
        if (points.size() > 1) {
            for (int i = 0; i < points.size() - 1; i++) {
                float[] start = points.get(i);
                float[] end = points.get(i + 1);
                canvas.drawLine(start[0], start[1], end[0], end[1], paint);
                drawArrow(canvas, start, end); // Draw an arrow head at the end of the line
            }
        }
        canvas.restore();
    }

    /**
     * Draws an arrow head at the end of a line.
     *
     * @param canvas The canvas on which to draw.
     * @param start  The starting point of the line.
     * @param end    The ending point of the line.
     */
    private void drawArrow(Canvas canvas, float[] start, float[] end) {
        float arrowHeadLength = 35; // The length of the arrow head
        float arrowHeadAngle = (float) Math.toRadians(45); // The angle of the arrow head

        // Calculate the direction of the line
        float dx = end[0] - start[0];
        float dy = end[1] - start[1];
        float angle = (float) Math.atan2(dy, dx);

        // Calculate the points of the arrow head
        float x1 = end[0] - arrowHeadLength * (float) Math.cos(angle - arrowHeadAngle);
        float y1 = end[1] - arrowHeadLength * (float) Math.sin(angle - arrowHeadAngle);
        float x2 = end[0] - arrowHeadLength * (float) Math.cos(angle + arrowHeadAngle);
        float y2 = end[1] - arrowHeadLength * (float) Math.sin(angle + arrowHeadAngle);

        // Create a path for the arrow head
        Path path = new Path();
        path.moveTo(end[0], end[1]);
        path.lineTo(x1, y1);
        path.lineTo(x2, y2);
        path.close();

        // Draw the arrow head
        canvas.drawPath(path, arrowPaint);
    }
}