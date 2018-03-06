package com.example.kimsungwon.example01;

import android.content.Context;

import com.dataludi.grids.ColumnCallback;
import com.dataludi.grids.DataColumn;
import com.dataludi.grids.GridColumn;
import com.dataludi.grids.GridView;
import com.dataludi.grids.data.DataField;
import com.dataludi.grids.data.GridDataSet;
import com.dataludi.grids.data.NumberField;
import com.dataludi.grids.data.TextField;
import com.dataludi.grids.data.impl.CsvReader;

import java.io.InputStream;

/**
 * Created by kimsungwon on 2018. 3. 6..
 */

public class HelloGridDemo implements IGridDemo {

    //--------------------------------------------------------------------------
    // fields
    //--------------------------------------------------------------------------
    private GridDataSet m_ds;
    private DataField[] m_fields = new DataField[]{
        new TextField("Country or Area"),
        new TextField("Year"),
        new NumberField("Value"),
    };

    private GridColumn[] m_columns = new GridColumn[]{
        DataColumn.create("Country or Area", "Country or Area", new ColumnCallback<DataColumn>() {
            @Override
            public void onCreate(DataColumn column) {
                column.setWidth(100);
            }
        }),
        DataColumn.create("Year", "Year", new ColumnCallback<DataColumn>() {
            @Override
            public void onCreate(DataColumn column) {
                column.setWidth(60);
//                column.getStyles().setTextAlignment(TextAlign.RIGHT);
            }
        }),
        DataColumn.create("Value", "Value", new ColumnCallback<DataColumn>() {
            @Override
            public void onCreate(DataColumn column) {
                column.setWidth(170);
//                column.getStyles().setTextAlignment(TextAlign.RIGHT);
                column.getStyles().setNumberFormat("#,##0");
                column.getFooter().setExpression("sum");
            }
        }),
    };

    //--------------------------------------------------------------------------
    // constructors
    //--------------------------------------------------------------------------
    public HelloGridDemo() {
        super();
    }

    //--------------------------------------------------------------------------
    // IGridDemo
    //--------------------------------------------------------------------------
    @Override
    public String getLabel() {
        return "Hello Grid";
    }

    @Override
    public void setupGrid(final Context context, final GridView grid) throws Exception {
        m_ds = new GridDataSet(m_fields);

        InputStream is = context.getResources().openRawResource(R.raw.gridtest);
        try {
            CsvReader.Options options = new CsvReader.Options();
            options.setStart(1).setQuoted(true).setCurrency(true);
            Object[][] rows = new CsvReader().read(is, m_ds, options);
            m_ds.setRows(rows);
        } finally {
            is.close();
        }

        grid.setColumns(m_columns);
        grid.setDataSource(m_ds);


    }

    @Override
    public IAction[] getActions() {
        return new IAction[0];
    }
}