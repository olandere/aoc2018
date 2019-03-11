package aoc2018

object Day4 {
  val lines = readLines("day4input.txt")
  val format = raw"\[(\d+)-(\d+)-(\d+) (\d+):(\d+)\] (.+)".r
  val log = lines.map{case format(year, month, day, hour, minute, entry) =>
    LogEntry(year.toInt, month.toInt, day.toInt, hour.toInt, minute.toInt, entry)}.sorted

  def apply() = {
    println(s"Part 1: ${part1()}")
    println(s"Part 2: ${part2()}")
  }

  def part1() = {


    log
  }

  def part2() = {

  }

  case class LogEntry(year: Int, month: Int, day: Int, hour: Int, minute: Int, entry: String) extends Ordered[LogEntry] {
    import scala.math.Ordered.orderingToOrdered

    override def compare(that: LogEntry): Int = (year, month, day, hour, minute) compare (that.year, that.month, that.day, that.hour, that.minute)
  }

}
