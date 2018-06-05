package k23r.audiograph2.fragments;

import android.graphics.Paint;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;

import k23r.audiograph2.PathWithPaint;
import k23r.audiograph2.R;


public class test extends AppCompatActivity {

    View mView;
    DrawingView mDrawView;
    private Paint paintGreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.myDrawing);
        mDrawView = new DrawingView(this);
        layout.addView(mDrawView, new LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        generateRandomNumber();
        paintGreen = getPaint(Color.GREEN);

    }

    private void generateRandomNumber() {

        for (int i = 0; i < 100; i++) {
            int randomNum = 2 + (int)(Math.random() * 100);
            mDrawView.Draw(i, randomNum);
        }


    }


    private Paint getPaint(int color) {
        Paint p = new Paint();
        p.setDither(true);
        p.setColor(color);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeJoin(Paint.Join.ROUND);
        p.setStrokeCap(Paint.Cap.ROUND);
        p.setStrokeWidth(3);
        return p;
    }

    class DrawingView extends View {
        private Path path;
        private Bitmap mBitmap;
        private Canvas mCanvas;

        public DrawingView(Context context) {
            super(context);
            path = new Path();
            mBitmap = Bitmap.createBitmap(820, 480, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
            this.setBackgroundColor(Color.BLACK);
        }

        private ArrayList<PathWithPaint> _graphics1 = new ArrayList<PathWithPaint>();

        public void Draw(float x, float y)
        {
            PathWithPaint pp = new PathWithPaint();
            mCanvas.drawPath(path, paintGreen);
            path.lineTo(x, y);
            pp.setPath(path);
            pp.setmPaint(paintGreen);
            _graphics1.add(pp);
            invalidate();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            /*
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
                        */
            return true;

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
