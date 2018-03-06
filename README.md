2. 안드로이드에서 Grid 만들기 

2-1 안드로이드 폰에서 보이는 결과값은 다음과 같습니다. 






2-2 csv파일(gridtest.csv)을 불러올수있게 코드를 추가합니다.


InputStream is = context.getResources().openRawResource(R.raw.gridtest);
try {
  	 CsvReader.Options options = new CsvReader.Options();
   	options.setStart(1).setQuoted(true).setCurrency(true);
   	Object[][] rows = new CsvReader().read(is, m_ds, options);
  	 m_ds.setRows(rows, false);
} finally  {
   	is.close();
}







2-3 csv파일의 처음 줄에 위치한 Country or Area, Year, Value를 타입에 맞게 데이터 필드를 생성한다.




2-4 Column을 보여주고 싶은 형태로 코드를 작성합니다.


   DataColumn.create("Country or Area", "Country or Area", new ColumnCallback<DataColumn>() {
       @Override
       public void onCreate(DataColumn column) {
           column.setWidth(100);
       }
   }),

:  Column의 이름은 “Country or Area”, 폭은 100의 크기로 지정했다. 

   DataColumn.create("Year", "Year", new ColumnCallback<DataColumn>() {
       @Override
       public void onCreate(DataColumn column) {
           column.setWidth(60);
           column.getStyles().setTextAlignment(TextAlign.RIGHT);
       }
   }),

: Column의 이름은 “Year”, 폭의 크기는 60으로 했고, 텍스트를 오른쪽정렬로 지정했다.





   DataColumn.create("Value", "Value", new ColumnCallback<DataColumn>() {
       @Override
       public void onCreate(DataColumn column) {
           column.setWidth(170);
           column.getStyles().setTextAlignment(TextAlign.RIGHT);
           column.getStyles().setNumberFormat("#,##0");
           column.getFooter().setExpression("sum");
       }
   }),


: Column의 이름은 “Value”, 폭의 크기는 170으로 했고, 텍스트를 오른쪽 지정으로 했으며 
  보여지는 숫자의 형태는 천단위로 끊었고, 마지막으로 Value에 해당하는 값의 합을 표현했습니다.


2-5  각 행의 변화에따른 색상 변화로 결과는 다음과 같습니다. 

grid.getBody().getCheckedStyles().setFill(Fill.create("#2000ff88")); // 행 체크시
grid.getBody().getUpdatedStyles().setFill(Fill.create("#10000000")); // 값 수정시




2-6 아래는 기본 그리드를 만드는데 사용한 전체 코드 입니다.


public class HelloGridDemo implements IGridDemo {

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
               column.getStyles().setTextAlignment(TextAlign.RIGHT);
           }
       }),
       DataColumn.create("Value", "Value", new ColumnCallback<DataColumn>() {
           @Override
           public void onCreate(DataColumn column) {
               column.setWidth(170);
               column.getStyles().setTextAlignment(TextAlign.RIGHT);
               column.getStyles().setNumberFormat("#,##0");
               column.getFooter().setExpression("sum");
           }
       }),
   };

   public HelloGridDemo() {
       super();
   }

   @Override
   public void setupGrid(final Context context, final GridView grid) throws Exception {
       m_ds = new GridDataSet(m_fields);

       InputStream is = context.getResources().openRawResource(R.raw.gridtest);
       try {
           CsvReader.Options options = new CsvReader.Options();
           options.setStart(1).setQuoted(true).setCurrency(true);
           Object[][] rows = new CsvReader().read(is, m_ds, options);
           m_ds.setRows(rows, false);
       } finally {
           is.close();
       }

       grid.setColumns(m_columns);
       grid.setDataSource(m_ds);

       grid.getBody().getCheckedStyles().setFill(Fill.create("#2000ff88"));
       grid.getBody().getUpdatedStyles().setFill(Fill.create("#10000000"));
   }

   @Override
   public IAction[] getActions() {
       return new IAction[0];
   }
}


최종 결과 입니다.



