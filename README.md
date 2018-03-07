Getting Started
======================

# 1. 안드로이드에서 Grid 만들기
## 1. 1 안드로이드 폰에서 보이는 결과값은 다음과 같습니다.

<img width="342" alt="2018-02-19 3 13 09" src="https://user-images.githubusercontent.com/16948394/37020650-d9756aa4-215f-11e8-8509-6dadccd42ce6.png">


## 1. 2 프로젝트 생성

	1> 안드로이드 스튜디오를 실행하고 File → New → New Project 를 선택해서 새 프로젝트를 생성합니다.
	2> 요구하는 SDK를 선택하고, 지원하는 최소 SDK인  API 버전 15(IceCreamSandwich) 를 선택합니다.
	3> Add an Activity to Mobile 에서 Empty Activity를 선택하고 Next를 클릭합니다.
	4> Finish를 눌러 프로젝트를 생성합니다.

## 1. 3 aar 파일 import 하기

	1> 안드로이드 스튜디오에서 File - Project Structure를 클릭합니다.
	2> 왼쪽 상단에 +를 선택합니다.
	
![2018-02-14 11 47 15](https://user-images.githubusercontent.com/16948394/37069453-cdba21c2-21f6-11e8-9be6-ca621b236cc7.png)

    3> Import .JAR/AAR Package를 선택하고 Next를 클릭합니다.
	
<img width="1074" alt="2018-02-14 11 54 54" src="https://user-images.githubusercontent.com/16948394/37069466-d642de92-21f6-11e8-8334-1cc512ff3c1d.png">

	4> charting-release.aar, common-release, grid-release 3개의 파일을 추가하고 Next를 클릭하고
	choose module에 :charting-release, :common-release, :grid-release를 선택후 OK를 클릭합니다.
	
<img width="195" alt="2018-02-14 12 07 52" src="https://user-images.githubusercontent.com/16948394/37069475-de9eb49e-21f6-11e8-8162-664376ea55ce.png">

	5> 확인하고 OK를 선택합니다.
	
<img width="877" alt="2018-02-19 2 34 14" src="https://user-images.githubusercontent.com/16948394/37069480-ea35fe8e-21f6-11e8-8b6e-9e71040d3fb6.png">

## 1. 4 csv파일(gridtest.csv)을 불러올수 있게 코드를 추가합니다.

	InputStream is = context.getResources().openRawResource(R.raw.gridtest);
	try {
  		CsvReader.Options options = new CsvReader.Options();
   		options.setStart(1).setQuoted(true).setCurrency(true);
   		Object[][] rows = new CsvReader().read(is, m_ds, options);
  		m_ds.setRows(rows, false);
	} 
	finally {
   		is.close();
	}

## 1. 5 csv파일의 처음 줄에 위치한 Country or Area, Year, Value를 타입에 맞게 데이터 필드를 생성한다.

![2018-02-19 3 34 07](https://user-images.githubusercontent.com/16948394/37020670-ee1dc992-215f-11e8-8483-1b133d8bdd4f.png)

## 1. 6 Column을 보여주고 싶은 형태로 코드를 작성합니다.

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

## 1. 7 각 행의 변화에따른 색상 변화로 결과는 다음과 같습니다.

grid.getBody().getCheckedStyles().setFill(Fill.create("#2000ff88")); // 행 체크시
grid.getBody().getUpdatedStyles().setFill(Fill.create("#10000000")); // 값 수정시

<img width="319" alt="2018-02-19 4 06 55" src="https://user-images.githubusercontent.com/16948394/37020684-f96fd092-215f-11e8-84be-6dcbb011b109.png">

## 1. 8 아래는 기본 그리드를 만드는데 사용한 전체 코드 입니다.

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







