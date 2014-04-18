name := "play-spring-data-jpa-backbonejs"

version := "1.0-SNAPSHOT"

play.Project.playJavaSettings

ebeanEnabled := false

libraryDependencies ++= Seq(
    javaCore,
    javaJpa,
    "org.springframework" % "spring-context" % "3.2.5.RELEASE",
    "javax.inject" % "javax.inject" % "1",
    "org.springframework.data" % "spring-data-jpa" % "1.3.5.RELEASE",
    "org.springframework" % "spring-expression" % "3.2.5.RELEASE",
    "org.hibernate" % "hibernate-entitymanager" % "4.2.8.Final",
    "org.mockito" % "mockito-core" % "1.9.5" % "test"
)
