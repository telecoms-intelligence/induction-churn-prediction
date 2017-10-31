# References
* [MapR blog post about churn prediction with Spark in Scala](https://mapr.com/blog/churn-prediction-sparkml/)
* Orange Telecom churn data sets:
 + Training sample: https://bml-data.s3.amazonaws.com/churn-bigml-80.csv
 + Test sample: https://bml-data.s3.amazonaws.com/churn-bigml-20.csv

# Install and run
```bash
$ mkdir -p ~/dev/ti
$ cd ~/dev/ti
$ git clone https://github.com/bom4v/ti-data-samples.git
$ git clone https://github.com/telecoms-intelligence/induction-churn-prediction.git
$ cd induction-churn-prediction
$ ./mkLocalDir.sh
$ sbt +compile +package
$ sbt +run
$ # If the control characters prove to be annoying:
$ sbt +run | sed -r "s/\x1B\[([0-9]{1,2}(;[0-9]{1,2})?)?[mGK]//g" | sed -r "s/\x1B\[([0-9];)?([0-9]{1,2}(;[0-9]{1,2})?)?[mGK]//g"
[info] Set current project to ti-spark-examples (in build file:~/dev/ti/induction-churn-prediction/)
[info] Running org.bom4v.ti.Demonstrator 
2666
667
root
 |-- state: string (nullable = true)
 |-- len: integer (nullable = true)
 |-- acode: string (nullable = true)
 |-- intlplan: string (nullable = true)
 |-- vplan: string (nullable = true)
 |-- numvmail: double (nullable = true)
 |-- tdmins: double (nullable = true)
 |-- tdcalls: double (nullable = true)
 |-- tdcharge: double (nullable = true)
 |-- ...
 |-- temins: double (nullable = true)
 |-- tecalls: double (nullable = true)
 |-- techarge: double (nullable = true)
 |-- tnmins: double (nullable = true)
 |-- tncalls: double (nullable = true)
 |-- tncharge: double (nullable = true)
 |-- timins: double (nullable = true)
 |-- ticalls: double (nullable = true)
 |-- ticharge: double (nullable = true)
 |-- numcs: double (nullable = true)
 |-- churn: string (nullable = true)

+-----+---+-----+--------+-----+--------+------+-------+--------+------+-------+--------+------+-------+--------+------+-------+--------+-----+-----+
|state|len|acode|intlplan|vplan|numvmail|tdmins|tdcalls|tdcharge|temins|tecalls|techarge|tnmins|tncalls|tncharge|timins|ticalls|ticharge|numcs|churn|
+-----+---+-----+--------+-----+--------+------+-------+--------+------+-------+--------+------+-------+--------+------+-------+--------+-----+-----+
|   KS|128|  415|      No|  Yes|    25.0| 265.1|  110.0|   45.07| 197.4|   99.0|   16.78| 244.7|   91.0|   11.01|  10.0|    3.0|     2.7|  1.0|False|
|   OH|107|  415|      No|  Yes|    26.0| 161.6|  123.0|   27.47| 195.5|  103.0|   16.62| 254.4|  103.0|   11.45|  13.7|    3.0|     3.7|  1.0|False|
| ... |
|   NJ|137|  415|      No|   No|     0.0| 243.4|  114.0|   41.38| 121.2|  110.0|    10.3| 162.6|  104.0|    7.32|  12.2|    5.0|    3.29|  0.0|False|
|   AZ|130|  415|      No|   No|     0.0| 183.0|  112.0|   31.11|  72.9|   99.0|     6.2| 181.8|   78.0|    8.18|   9.5|   19.0|    2.57|  0.0|False|
+-----+---+-----+--------+-----+--------+------+-------+--------+------+-------+--------+------+-------+--------+------+-------+--------+-----+-----+
only showing top 20 rows

+-----+-----+
|churn|count|
+-----+-----+
|False| 2278|
| True|  388|
+-----+-----+

+-----+-----+
|churn|count|
+-----+-----+
|False|  379|
| True|  388|
+-----+-----+

767
+---+--------+--------+------+-------+------+-------+------+-------+--------+------+-------+--------+-----+-----+
|len|intlplan|numvmail|tdmins|tdcalls|temins|tecalls|tnmins|tncalls|tncharge|timins|ticalls|ticharge|numcs|churn|
+---+--------+--------+------+-------+------+-------+------+-------+--------+------+-------+--------+-----+-----+
|128|      No|    25.0| 265.1|  110.0| 197.4|   99.0| 244.7|   91.0|   11.01|  10.0|    3.0|     2.7|  1.0|False|
| 77|      No|     0.0|  62.4|   89.0| 169.9|  121.0| 209.6|   64.0|    9.43|   5.7|    6.0|    1.54|  5.0| True|
| ... |
|128|      No|     0.0| 237.9|  125.0| 247.6|   93.0| 208.9|   68.0|     9.4|  13.9|    4.0|    3.75|  1.0| True|
| 82|      No|     0.0| 143.9|   61.0| 194.9|  105.0| 109.6|   94.0|    4.93|  11.1|    2.0|     3.0|  1.0|False|
+---+--------+--------+------+-------+------+-------+------+-------+--------+------+-------+--------+-----+-----+
only showing top 20 rows

The Best Model and Parameters:
--------------------
DecisionTreeClassificationModel (uid=dtc_a184b8c1a882) of depth 5 with 53 nodes
Learned classification tree model:
DecisionTreeClassificationModel (uid=dtc_a184b8c1a882) of depth 5 with 53 nodes
  If (feature 11 <= 3.0)
   If (feature 3 <= 222.4)
    If (feature 1 in {1.0})
     If (feature 9 <= 13.1)
      If (feature 10 <= 2.0)
       Predict: 0.0
      Else (feature 10 > 2.0)
       Predict: 1.0
     Else (feature 9 > 13.1)
      Predict: 0.0
    Else (feature 1 not in {1.0})
     If (feature 4 <= 125.0)
      If (feature 3 <= 209.5)
       Predict: 1.0
      Else (feature 3 > 209.5)
       Predict: 1.0
     Else (feature 4 > 125.0)
      If (feature 3 <= 161.2)
       Predict: 1.0
      Else (feature 3 > 161.2)
       Predict: 0.0
   ...
   Else (feature 3 > 180.9)
    If (feature 8 <= 104.0)
     If (feature 8 <= 81.0)
      If (feature 4 <= 94.0)
       Predict: 1.0
      Else (feature 4 > 94.0)
       Predict: 0.0
     Else (feature 8 > 81.0)
      If (feature 4 <= 137.0)
       Predict: 1.0
      Else (feature 4 > 137.0)
       Predict: 0.0
    Else (feature 8 > 104.0)
     If (feature 0 <= 135.0)
      Predict: 0.0
     Else (feature 0 > 135.0)
      If (feature 0 <= 160.0)
       Predict: 1.0
      Else (feature 0 > 160.0)
       Predict: 0.0

area under the precision-recall curve: 0.9747578698231796
area under the receiver operating characteristic (ROC) curve : 0.8484817813765183
MapPartitionsRDD[1233] at map at BinaryClassificationMetrics.scala:214
+-----+----------+--------------------+
|label|prediction|         probability|
+-----+----------+--------------------+
|  1.0|       1.0|[0.10676156583629...|
|  0.0|       0.0|[0.91666666666666...|
|  0.0|       0.0|           [1.0,0.0]|
|  1.0|       1.0|[0.10676156583629...|
|  1.0|       1.0|[0.10676156583629...|
|  1.0|       1.0|           [0.1,0.9]|
|  1.0|       1.0|[0.27027027027027...|
|  0.0|       0.0|[0.91666666666666...|
|  1.0|       1.0|[0.10676156583629...|
|  1.0|       1.0|[0.10676156583629...|
|  1.0|       1.0|[0.10676156583629...|
|  1.0|       1.0|[0.48275862068965...|
|  1.0|       1.0|[0.27027027027027...|
|  1.0|       1.0|[0.10676156583629...|
|  0.0|       1.0|[0.10676156583629...|
|  1.0|       1.0|[0.10676156583629...|
|  0.0|       0.0|[0.95192307692307...|
|  1.0|       0.0|[0.57142857142857...|
|  1.0|       1.0|[0.10676156583629...|
|  1.0|       1.0|         [0.08,0.92]|
+-----+----------+--------------------+
only showing top 20 rows

counttotal : 667
correct : 574
wrong: 93
ratio wrong: 0.13943028485757122
ratio correct: 0.8605697151424287
ratio true positive : 0.1184407796101949
ratio false positive : 0.0239880059970015
ratio true negative : 0.7421289355322339
ratio false negative : 0.11544227886056972
wrong: 93
+----------+-----+-----+
|prediction|label|equal|
+----------+-----+-----+
|       1.0|  1.0|    1|
|       0.0|  0.0|    1|
|       0.0|  0.0|    1|
|       1.0|  1.0|    1|
|       1.0|  1.0|    1|
|       1.0|  1.0|    1|
|       1.0|  1.0|    1|
|       0.0|  0.0|    1|
|       1.0|  1.0|    1|
|       1.0|  1.0|    1|
|       1.0|  1.0|    1|
|       1.0|  1.0|    1|
|       1.0|  1.0|    1|
|       1.0|  1.0|    1|
|       1.0|  0.0|    0|
|       1.0|  1.0|    1|
|       0.0|  0.0|    1|
|       0.0|  1.0|    0|
|       1.0|  1.0|    1|
|       1.0|  1.0|    1|
+----------+-----+-----+
only showing top 20 rows

[success] Total time: 22 s, completed Oct 31, 2017 5:40:04 PM
```


