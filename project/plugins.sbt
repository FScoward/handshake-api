logLevel := Level.Warn

resolvers += "Flyway" at "https://flywaydb.org/repo"

addSbtPlugin("io.spray" % "sbt-revolver" % "0.8.0")
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.8.2")

addSbtPlugin("org.flywaydb" % "flyway-sbt" % "4.0.3")

addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.6.0")