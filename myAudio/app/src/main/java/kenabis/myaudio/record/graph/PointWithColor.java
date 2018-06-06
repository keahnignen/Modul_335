package kenabis.myaudio.record.graph;

import android.graphics.Paint;

public class PointWithColor {

    public PointWithColor(int x, int y, int color)
    {
        this.x = x;
        this.y = y;
        this.point =getPaint(color);
    }

    public int x;
    public int y;
    public Paint point;

    public Paint getPaint(int color) {
        Paint p = new Paint();

        p.setDither(true);

        p.setColor(color);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeJoin(Paint.Join.ROUND);
        p.setStrokeCap(Paint.Cap.ROUND);
        p.setStrokeWidth(5);

        return p;
    }
}
