name := "personlist"
 
version := "1.0" 
      
lazy val `personlist` = (project in file(".")).enablePlugins(PlayScala)

      
resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"
      
scalaVersion := "2.13.5"

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice , javaJpa,
  "org.hibernate" % "hibernate-core" % "5.4.30.Final", "org.postgresql" % "postgresql" % "42.2.5")

PlayKeys.externalizeResourcesExcludes += baseDirectory.value / "conf" / "META-INF" / "persistence.xml"