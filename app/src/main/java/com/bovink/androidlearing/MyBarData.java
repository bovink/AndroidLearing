package com.bovink.androidlearing;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.List;

/**
 * @author fox
 * @since 2017/10/20
 */

public class MyBarData extends BarData {

    public MyBarData() {
        super();
    }

    public MyBarData(IBarDataSet... dataSets) {
        super(dataSets);
    }

    public MyBarData(List<IBarDataSet> dataSets) {
        super(dataSets);
    }

    public void groupBarsOpposite(float fromX, float groupSpace, float barSpace) {

        int setCount = mDataSets.size();
        if (setCount <= 1) {
            throw new RuntimeException("BarData needs to hold at least 2 BarDataSets to allow grouping.");
        }

        IBarDataSet max = getMaxEntryCountSet();
        int maxEntryCount = max.getEntryCount();

        float groupSpaceWidthHalf = groupSpace / 2f;
        float barSpaceHalf = barSpace / 2f;
        float barWidthHalf = getBarWidth() / 2f;

        float interval = getGroupWidth(groupSpace, barSpace);

        for (int i = 0; i < maxEntryCount; i++) {

            float start = fromX;
            fromX -= groupSpaceWidthHalf;

            for (IBarDataSet set : mDataSets) {

                fromX -= barSpaceHalf;
                fromX -= barWidthHalf;

                if (i < set.getEntryCount()) {

                    BarEntry entry = set.getEntryForIndex(i);

                    if (entry != null) {
                        entry.setX(fromX);
                    }
                }

                fromX -= barWidthHalf;
                fromX -= barSpaceHalf;
            }

            fromX -= groupSpaceWidthHalf;
            float end = fromX;
            float innerInterval = end - start;
            float diff = interval - innerInterval;

            // correct rounding errors
            if (diff > 0 || diff < 0) {
                fromX -= diff;
            }
        }

        notifyDataChanged();
    }
}
