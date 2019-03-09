package object aoc2018 {
  import scala.io.Source

  def readLines(filename: String): Seq[String] = Source.fromURL(getClass.getClassLoader.getResource(filename)).getLines.toSeq
}
