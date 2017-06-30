package lijinbo.bwie.com.lijinbo20170602;

import android.widget.GridView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
/**
 * Created by lijinbo on 2017/6/2.
 */

public class MyGridView extends GridView{


    public MyGridView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
