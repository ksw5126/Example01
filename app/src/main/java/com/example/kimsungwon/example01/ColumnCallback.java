package com.example.kimsungwon.example01;

import com.dataludi.grids.GridColumn;

/**
 * Created by kimsungwon on 2018. 3. 6..
 */

public interface ColumnCallback<T extends GridColumn> {

    void onCreate(T column);
}
