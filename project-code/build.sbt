import play.core.PlayVersion

val playVersionSuffix: String = {
  val versions = PlayVersion.current.split('.')
  require(versions.length >= 2)
  s"${versions(0)}${versions(1)}"
}

name := s"play-cxf_play$playVersionSuffix"

organization := "eu.imind.play"

organizationName := "iMind"

organizationHomepage := Some(url("http://imind.eu/"))

version := "1.4.0"

scalaVersion := "2.12.8"

libraryDependencies += guice

libraryDependencies += "org.springframework" % "spring-context" % "[5.1.8.RELEASE,)"

libraryDependencies += "org.apache.cxf" % "cxf-core" % "3.3.2"

libraryDependencies += "com.typesafe.play" %% "play-iteratees" % "2.6.1"

libraryDependencies += "com.typesafe.play" %% "play-iteratees-reactive-streams" % "2.6.1"

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

homepage := Some(url("http://www.imind.eu/web/2013/11/07/developing-soap-services-using-play-framework-2-2-x/"))

scmInfo := Some(ScmInfo(
  url("https://github.com/imindeu/play-cxf"),
  "scm:git:https://github.com/imindeu/play-cxf.git",
  Some("scm:git:git@github.com:imindeu/play-cxf.git")))

pomExtra :=
  <developers>
    <developer>
      <id>kustra</id>
      <name>László Zsolt Kustra</name>
      <email>laszlo.kustra@gmail.com</email>
      <url>http://laszlo.kustra.hu/</url>
      <organization>iMind</organization>
      <organizationUrl>http://imind.eu/</organizationUrl>
    </developer>
  </developers>

lazy val root = (project in file(".")).enablePlugins(PlayScala)

credentials += (sys.env.get("ARTIFACT_REPO_USER") match {
  case Some(repoUser) => Credentials("Artifactory Realm", "rll.jfrog.io", repoUser, sys.env("ARTIFACT_REPO_PASSWORD"))
  case _ => Credentials(Path.userHome / ".ivy2" / ".pronto-credentials")
})

publishTo := {
  if (isSnapshot.value)
    Some("Artifactory Realm" at "https://rll.jfrog.io/rll/pronto-snapshots")
  else
    Some("Artifactory Realm" at "https://rll.jfrog.io/rll/pronto-releases")
}
