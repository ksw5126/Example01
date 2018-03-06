package com.example.kimsungwon.example01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.dataludi.charting.ChartComponent;
import com.dataludi.common.Utils;
import com.dataludi.grids.GridComponent;
import com.dataludi.grids.GridView;
import com.dataludi.ui.UIComponent;

public class MainActivity extends AppCompatActivity {

    GridView m_grid;
    IGridDemo.IAction[] m_gridActions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ViewGroup layout = (ViewGroup)findViewById(R.id.mainLayout);

        UIComponent view = setupGrid();
//        UIComponent view = setupChart();

        layout.addView(view);

        // action bar
        //getSupportActionBar().hide();
//        getSupportActionBar().setTitle("XXXXX");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (m_gridActions != null) {
            for (int i = 0; i < m_gridActions.length; i++) {
                IGridDemo.IAction action = m_gridActions[i];
                menu.add(0, i, i, action.getLabel());
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (m_gridActions != null) {
            IGridDemo.IAction action = m_gridActions[item.getItemId()];
            try {
                action.run(m_grid);
            } catch (Throwable ex) {
                ex.printStackTrace();
                Utils.alert(this, ex.getMessage());
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private UIComponent setupGrid() {
        GridComponent container = new GridComponent(this);

//        IGridDemo demo = new DynamicRenderersDemo();
//        IGridDemo demo = new SparkColumnRendererDemo();
//        IGridDemo demo = new SparkLineRendererDemo();
//        IGridDemo demo = new SparkWinLossRendererDemo();
//        IGridDemo demo = new GridSummaryDemo();
//        IGridDemo demo = new CalcedColumnsDemo();
//        IGridDemo demo = new CheckRendererDemo();
//        IGridDemo demo = new ColumnDynamicStylesDemo();
//        IGridDemo demo = new RangeCalcedColumnsDemo();
//        IGridDemo demo = new RowGroupingDemo();
//        IGridDemo demo = new GridExamDemo();
//        IGridDemo demo = new FixedColumnsDemo();
//        IGridDemo demo = new FixedRowsDemo();
//        IGridDemo demo = new CellMergingDemo();
//        IGridDemo demo = new ColumnGroupingDemo();
//        IGridDemo demo = new DelegateColumnsDemo();
//        IGridDemo demo = new ColumnLookupDemo();
//        IGridDemo demo = new LabelFieldDemo();
//        IGridDemo demo = new LookupTreeDemo();
//        IGridDemo demo = new DynamicRenderersDemo();
//        IGridDemo demo = new PagingDemo();
//        IGridDemo demo = new HiddenRowDemo();
//        IGridDemo demo = new RowSwipeDemo();
//        IGridDemo demo = new BarRendererDemo();
//        IGridDemo demo = new EditRowDemo();
//        IGridDemo demo = new SetRowsDemo();
//        IGridDemo demo = new SetRowCountDemo();
//        IGridDemo demo = new LazyLoadDataDemo();
        IGridDemo demo = new HelloGridDemo();
//        IGridDemo demo = new CheckBarDemo();

        m_grid = (GridView)container.getGridView();
        m_gridActions = demo.getActions();

        try {
            //getSupportActionBar().setTitle(demo.getClass().getSimpleName());
            getSupportActionBar().setTitle(demo.getLabel());
            demo.setupGrid(this, m_grid);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return container;
    }

    private UIComponent setupChart() {
        ChartComponent container = new ChartComponent(this);

//        IChartDemo demo = new HelloChartExam();
//        IChartDemo demo = new ColumnChartExam();
//        IChartDemo demo = new PieChartExam();

        try {
            //getSupportActionBar().setTitle(demo.getLabel());
//            getSupportActionBar().setTitle(demo.getClass().getSimpleName());
//            demo.setupChart(this, container.getChartView());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return container;
    }
}
