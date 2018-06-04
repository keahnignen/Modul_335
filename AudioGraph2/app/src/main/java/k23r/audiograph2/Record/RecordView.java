package k23r.audiograph2.Record;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class RecordView extends View{

    public RecordView(Context context) {
        super(context);
    }

    public RecordView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecordView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RecordView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    Pt[] myPath = { new Pt(100, 100),

            new Pt(200, 200),

            new Pt(200, 500),

            new Pt(400, 500),

            new Pt(400, 200)

    };





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
