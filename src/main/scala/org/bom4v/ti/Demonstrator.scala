package org.bom4v.ti

object Demonstrator extends App {

  // Spark 2.x way, with a SparkSession
  // https://databricks.com/blog/2016/08/15/how-to-use-sparksession-in-apache-spark-2-0.html
  val spark = org.apache.spark.sql.SparkSession
    .builder()
    .appName("SparkSessionForBom4VDemonstrator")
    .config("spark.master", "local")
    .getOrCreate()

  // CSV data file, from the local file-system
  val dataFilepath = "data/churn-bigml-80.csv"
  // CSV data file, from HDFS
  // (check the fs.defaultFS property in the $HADOOP_CONF_DIR/core-site.xml file)
  // val dataFilepath = "hdfs://localhost:8020/data/churn-bigml-80.csv"

  // Spark 2x
  val cdrDF = spark.read
    .format("com.databricks.spark.csv")
    .option("header", "true")       // Use first line of all files as header
    .option("inferSchema", "true")  // Automatically infer data types
    .option("delimiter", ",")
    .option("timestampFormat", "yyyy-MM-dd HH:mm:ss")
    .load(dataFilepath)

  // Print the schema of this input
  println ("cdrDF:")
  cdrDF.printSchema()
  
  // Sample 3 records along with headers
  cdrDF.show (3)
  
  // Show all the records along with headers 
  cdrDF.show ()  
  
  // Sample the first 5 records
  cdrDF.head(5).foreach (println)
  
  // Alias of head
  cdrDF.take(5).foreach (println)
    
  // Headers: State,Account length,Area code,International plan,Voice mail plan,Number vmail messages,Total day minutes,Total day calls,Total day charge,Total eve minutes,Total eve calls,Total eve charge,Total night minutes,Total night calls,Total night charge,Total intl minutes,Total intl calls,Total intl charge,Customer service calls,Churn

  // Select just the emitter MSISDN to a different DataFrame
  val minutesDF:org.apache.spark.sql.DataFrame = cdrDF.select ("Total day minutes")
  println ("minutesDF:")
  minutesDF.show(3)
  
  // Select more than one column and create a different DataFrame
  val minutesCallsDF = cdrDF.select ("Total day calls", "Total day minutes")
  println ("Calls and minutes:")
  minutesCallsDF.show(3)
  
  // Now, let's save the modified DataFrame with a new name
  val options = Map ("header" -> "true", "path" -> "altCDR.csv")
  
  // Modify DataFrame - pick 'recEntityId' and 'callingNumber' columns,
  // change 'recEntityId' column name to just 'number'
  val copyOfCDRDF = cdrDF
    .select (cdrDF ("Total day calls").as("nb_calls"), cdrDF ("Total day minutes").as("nb_minutes"))
  println ("copyOfCDRDF:")
  copyOfCDRDF.show()

  // Save this new dataframe with headers
  // and with file name "altCDR.csv"
  copyOfCDRDF.write
    .format ("com.databricks.spark.csv")
    .mode (org.apache.spark.sql.SaveMode.Overwrite)
    .options (options).save
  
  // Load the saved data and verify the schema and list some records
  // Instead of using the csvFile, you could do a 'load' 
  val newCDRDF = spark.read
    .format ("com.databricks.spark.csv")
    .options (options).load

  //
  println ("newCDRDF:")
  newCDRDF.printSchema()
  newCDRDF.show()  

  // Stop Spark
  spark.stop()
}

