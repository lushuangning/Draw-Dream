package cuit.drawdream.utils.tool;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
*  @name  MyItemDecoration
*  @author  JWZ
*  @des  该类的主要描述:recycleview的分割线
*  @date  2016/8/31 0031
*/
public class MyItemDecoration extends RecyclerView.ItemDecoration {

    /**
     * 复写onDraw方法，从而达到在每隔条目的被绘制之前（或之后），画线
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        //先初始化一个Paint来简单指定一下Canvas的颜色
        Paint paint = new Paint();
        paint.setColor(Color.rgb(204 ,204,204));

        //获得RecyclerView中总条目数量
        int childCount = parent.getChildCount();

        //遍历一下
        for (int i = 0; i < childCount; i++) {
//            if (i == 0) {
//                //如果是第一个条目，那么我们就不画边框了
//                continue;
//            }
            //获得子View，也就是一个条目的View，准备给他画上边框
            View childView = parent.getChildAt(i);

            //先获得子View的长宽，以及在屏幕上的位置，方便我们得到边框的具体坐标
            float x = childView.getX();
            float y = childView.getY();
            int width = childView.getWidth();
            int height = childView.getHeight();

            //根据这些点画条目的四周的线
            c.drawLine(x, y, x + width, y, paint);
            c.drawLine(x, y, x, y + height, paint);
            c.drawLine(x + width, y, x + width, y + height, paint);
            c.drawLine(x, y + height, x + width, y + height, paint);
        }
        super.onDraw(c, parent, state);
    }
}

