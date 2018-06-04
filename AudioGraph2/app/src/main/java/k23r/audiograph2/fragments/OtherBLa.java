package k23r.audiograph2.fragments;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import k23r.audiograph2.R;

public class OtherBLa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_bla);
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.draw);
        View  mView = new MyView(this);
        layout.addView(mView, new ViewGroup.LayoutParams(500, 500));
    }


    public class MyView extends View {



        class Pt{

            float x, y;



            Pt(float _x, float _y){

                x = _x;

                y = _y;

            }

        }



        Pt[] myPath = { new Pt(100, 100),


                new Pt(200, 200),


                new Pt(200, 500),


                new Pt(400, 500),


                new Pt(400, 200)

        };



        public MyView(Context context) {

            super(context);

            // TODO Auto-generated constructor stub

        }



        @Override

        protected void onDraw(Canvas canvas) {

            // TODO Auto-generated method stub

            super.onDraw(canvas);





            Paint paint = new Paint();

            paint.setColor(Color.WHITE);

            paint.setStrokeWidth(3);

            paint.setStyle(Paint.Style.STROKE);

            Path path = new Path();



            path.moveTo(myPath[0].x, myPath[0].y);

            for (int i = 1; i < myPath.length; i++){

                path.lineTo(myPath[i].x, myPath[i].y);

            }

            canvas.drawPath(path, paint);



        }



    }

}

