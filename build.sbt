libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "3.8.6" % "test",
  "org.specs2" %% "specs2-junit" % "3.8.6" % "test",
  "org.specs2" %% "specs2-scalaz-core" % "7.0.0" % "test",
  "junit" % "junit" % "4.12" % "test",
  "org.hamcrest" % "hamcrest-all" % "1.3" % "test",
  "org.pegdown" % "pegdown" % "1.6.0" % "test"
)

scalacOptions in Test ++= Seq("-Yrangepos")
