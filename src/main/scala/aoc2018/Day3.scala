package aoc2018

object Day3 {
  val format = raw"#(\d+) @ (\d+),(\d+): (\d+)x(\d+)".r

  val lines = readLines("day3input.txt")
  val rects = lines.map { case format(id, x, y, w, h) => Rectangle(id.toInt, x.toInt, y.toInt, w.toInt, h.toInt) }

  def apply() = {
    println(s"Part 1: ${part1()}")
    println(s"Part 2: ${part2()}")
  }

  def part1() = {

    def union[A](a: Set[A], b: Set[A]) = a.union(b)

    def overlaps(rectangles: Seq[Day3.Rectangle]): Seq[Option[Rectangle]] = {
      def helper(r: Rectangle, rs: Seq[Day3.Rectangle], acc: Seq[Option[Day3.Rectangle]]): Seq[Option[Day3.Rectangle]] = {
        if (rs.isEmpty) acc
        else helper(r, rs.tail, acc :+ r.overlapRegion(rs.head))
      }
      if (rectangles.isEmpty) Seq() else
      helper(rectangles.head, rectangles.tail, Seq()) ++ overlaps(rectangles.tail)
    }

    overlaps(rects).filter(_.isDefined).map(_.get).map(_.cells).fold(Set())(union).size
  }

  def part2() = {
    for {
      r <- rects if !rects.exists{r1 => r1 != r && r.overlapRegion(r1).isDefined}
    } yield r
  }

  case class Rectangle(id: Int, x: Int, y: Int, w: Int, h: Int) {

    def overlapRegion(other: Rectangle): Option[Rectangle] = {
      if (x + w < other.x + 1 || other.x + other.w < x + 1) None
      else if (y + h < other.y + 1 || other.y + other.h < y + 1) None
      else {
        Some(Rectangle(-1,
          math.max(x, other.x),
          math.max(y, other.y),
          math.min(x + w, other.x + other.w) - math.max(x, other.x),
          math.min(y + h, other.y + other.h) - math.max(y, other.y)))
      }
    }

    def area: Int = w * h

    def cells: Set[(Int, Int)] = {
      (for {
        r <- x + 1 to x + w
        c <- y + 1 to y + h
      } yield (r, c)).toSet
    }
  }

}
