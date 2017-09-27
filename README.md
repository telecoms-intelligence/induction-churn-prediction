# References
* [MapR blog post about churn prediction with Spark in Scala](https://mapr.com/blog/churn-prediction-sparkml/)
* Orange Telecom churn data sets:
 + Training sample: https://bml-data.s3.amazonaws.com/churn-bigml-80.csv
 + Test sample: https://bml-data.s3.amazonaws.com/churn-bigml-20.csv

# Install and run
```bash
$ mkdir -p ~/dev/ti
$ cd ~/dev/ti
$ git clone https://github.com/telecoms-intelligence/induction-churn-prediction.git
$ cd induction-churn-prediction
$ sbt +compile +package
$ sbt +run
```


