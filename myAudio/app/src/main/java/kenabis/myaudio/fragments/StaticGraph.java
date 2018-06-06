package kenabis.myaudio.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import java.util.ArrayList;

import kenabis.myaudio.record.PathWithPaint;
import kenabis.myaudio.R;


public class StaticGraph extends AppCompatActivity {

    View mView;
    DrawingView mDrawView;
    private Paint paintGreen;


    public StaticGraph()
    {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        this.height = size.x;
        this.width = size.y;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.myDrawing);
        mDrawView = new DrawingView(this);
        layout.addView(mDrawView, new LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        Bundle extras = getIntent().getExtras();
        float[] array = extras.getFloatArray("array");
        drawArray(array);
    }



    private static final int YELLOW_TOLERANCE = 2;
    private static final int GREEN_TOLERANCE = 1;

    private final float width;
    private final float height;

    private void drawArray(float[] array) {

        for (int i = 0; i < 100; i++)
        {
            mDrawView.Draw(height / 8000 * 6000, width / array.length * i, Color.GREEN);
        }

        for (int i = 0; i < array.length; i++) {
            float s = array[i];
            mDrawView.Draw(height / 8000 * array[i], width / array.length * i + 100, Color.YELLOW);
        }

    }

    double [] mPitchesIn4 = {4186.01, 4434.92, 4698.63, 4978.03, 5274.04, 5587.65, 5919.91, 6271.93, 6644.88, 7040.00, 7458.62, 7902.13};

    private int getColor(float frequency)
    {
        for (double pitch : mPitchesIn4)
        {
            if (isBetweenTolerance(pitch, frequency, GREEN_TOLERANCE))
            {
               return Color.GREEN;
            }
            if (isBetweenTolerance(pitch, frequency, YELLOW_TOLERANCE))
            {
                return Color.YELLOW;
            }
        }
        return Color.RED;
    }

    private boolean isBetweenTolerance(double perfectPitchInHz, float frequency, int toleranceInPercent)
    {
        int counter = 0;
        for (double j = 1; j < 256; j = j * 2) {
            counter++;
            double exactPitch = perfectPitchInHz / j ;
            double toleranceInHz = exactPitch / 100 * this.GREEN_TOLERANCE;
            if (frequency < exactPitch+toleranceInHz && frequency > exactPitch-toleranceInHz){
                return true;
            }
        }
        return false;
    }


    private Paint getPaint(int color) {
        Paint p = new Paint();

        //p.setDither(true);

        p.setColor(color); /*
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeJoin(Paint.Join.ROUND);
        p.setStrokeCap(Paint.Cap.ROUND);
        p.setStrokeWidth(3);
        */
        return p;
    }

    class DrawingView extends View {
        private Path path;
        private Bitmap mBitmap;
        private Canvas mCanvas;

        public DrawingView(Context context) {
            super(context);
            path = new Path();
            mBitmap = Bitmap.createBitmap(800, 400, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
            this.setBackgroundColor(Color.BLACK);
        }

        private ArrayList<PathWithPaint> _graphics1 = new ArrayList<PathWithPaint>();

        public Integer oldColor;

        public Float oldx;
        public Float oldy;

        public void Draw(float x, float y, int color)
        {
            if (oldx == null)
            {
                oldx = x;
                oldy = y;
            }


            //mCanvas.drawLine(oldx, oldy, x, y, getPaint(color));
            mCanvas.drawPoint(x, y, getPaint(color));
            oldx = x;
            oldy = y;
/*

            paintGreen = getPaint(color);
            PathWithPaint pp = new PathWithPaint();

            StartDraw(x,y);
            ContinueDraw(x, y, pp, paintGreen);
*/
            /*
            if (oldColor == null || oldColor != color)
            {
                oldColor = color;
                StartDraw(x,y);
            }
            else
            {
                ContinueDraw(x, y, pp, paintGreen);
            }
*/
            invalidate();
        }



        private int counter = 0;
        /*
        @Override
        public boolean onTouchEvent(MotionEvent event) {


            counter++;
            if (counter > 10)
            {
                paintGreen = getPaint(Color.RED);
            }
            PathWithPaint pp = new PathWithPaint();
            mCanvas.drawPath(path, paintGreen);
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                path.moveTo(event.getX(), event.getY());
                path.lineTo(event.getX(), event.getY());
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                path.lineTo(event.getX(), event.getY());
                pp.setPath(path);
                pp.setmPaint(paintGreen);
                _graphics1.add(pp);
            }
            invalidate();

            return true;

        }
        */

        private void StartDraw(float x, float y)
        {
            path = new Path();
            path.moveTo(x, y);
            path.lineTo(x, y);
        }

        private void ContinueDraw(float x, float y, PathWithPaint pp, Paint color)
        {
            path.lineTo(x, y);
            pp.setPath(path);
            pp.setmPaint(color);
            _graphics1.add(pp);
        }


        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (_graphics1.size() > 0) {
                canvas.drawPath(
                        _graphics1.get(_graphics1.size() - 1).getPath(),
                        _graphics1.get(_graphics1.size() - 1).getmPaint());
            }
        }
    }
}
