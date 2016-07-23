name := "handshake-api"

version := "1.0"

scalaVersion := "2.11.8"

parallelExecution in ThisBuild := false

lazy val versions = new {
  val finatra = "2.1.6"
  val guice = "4.0"
  val logback = "1.0.13"
}

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  "Twitter Maven" at "https://maven.twttr.com"
)

assemblyMergeStrategy in assembly := {
  case "BUILD" => MergeStrategy.discard
  case other => MergeStrategy.defaultMergeStrategy(other)
}

libraryDependencies ++= Seq(
  "com.twitter.finatra" %% "finatra-http" % versions.finatra,
  "com.twitter.finatra" %% "finatra-httpclient" % versions.finatra,
  "ch.qos.logback" % "logback-classic" % versions.logback,

  "com.twitter.finatra" %% "finatra-http" % versions.finatra % "test",
  "com.twitter.finatra" %% "finatra-jackson" % versions.finatra % "test",
  "com.twitter.inject" %% "inject-server" % versions.finatra % "test",
  "com.twitter.inject" %% "inject-app" % versions.finatra % "test",
  "com.twitter.inject" %% "inject-core" % versions.finatra % "test",
  "com.twitter.inject" %% "inject-modules" % versions.finatra % "test",
  "com.google.inject.extensions" % "guice-testlib" % versions.guice % "test",

  "com.twitter.finatra" %% "finatra-http" % versions.finatra % "test" classifier "tests",
  "com.twitter.finatra" %% "finatra-jackson" % versions.finatra % "test" classifier "tests",
  "com.twitter.inject" %% "inject-server" % versions.finatra % "test" classifier "tests",
  "com.twitter.inject" %% "inject-app" % versions.finatra % "test" classifier "tests",
  "com.twitter.inject" %% "inject-core" % versions.finatra % "test" classifier "tests",
  "com.twitter.inject" %% "inject-modules" % versions.finatra % "test" classifier "tests",

  "org.mockito" % "mockito-core" % "1.9.5" % "test",
  "org.scalatest" %% "scalatest" % "2.2.3" % "test",
  "org.specs2" %% "specs2" % "2.3.12" % "test",

  "io.getquill" % "quill-async_2.11" % "0.6.0",
  "com.zaxxer" % "HikariCP" % "2.4.3",
  "com.typesafe" % "config" % "1.3.0",

  "mysql" % "mysql-connector-java" % "5.1.20",

  "org.twitter4j" % "twitter4j-core" % "4.0.4",

  "com.github.cb372" %% "scalacache-memcached" % "0.9.1",
  "com.github.cb372" %% "scalacache-ehcache" % "0.9.1"


)

//unmanagedClasspath in Compile += baseDirectory.value / "src" / "main" / "resources"

//flywayDriver := "com.mysql.jdbc.Driver"

flywayUrl := "jdbc:mysql://192.168.99.100:32776/handshake?characterEncoding=UTF8"

flywayUser := "root"

flywayPassword := "topsecret"

