name := "WebDisk"

version := "0.0.1"

lazy val `webdisk` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq( cache, ws, specs2 % Test)

libraryDependencies += "net.sf.barcode4j" % "barcode4j" % "2.0"

libraryDependencies ++= Seq(
  "org.webjars" % "bootstrap" % "2.3.2"
)

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.36"

libraryDependencies += "com.qiniu" % "qiniu-java-sdk" % "7.1.0"


libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "2.0.0", "com.typesafe.play" %% "play-slick-evolutions" % "2.0.0"
)

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"  