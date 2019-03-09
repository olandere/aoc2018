package aoc2018

object Day2 {

	val lines = readLines("day2input.txt")

	def apply() = {
		println(s"Part 1: ${part1()}")
		println(s"Part 2: ${part2()}")
	}

	def part1() = {
		def hasN(n: Int)(s: String) = s.groupBy(x => x).exists{case (k, v) => v.size == n}

		val has2 = hasN(2) _
		val has3 = hasN(3) _

		lines.count(has2) * lines.count(has3)
	}

	def part2() = {

		def findNDiffs(n: Int)(l: Seq[String]): (String, String) = {
			def helper(s: String, l: Seq[String]) = {
				l.filter(x => s.diff(x).length == n)
			}
			helper(l.head, l.tail).headOption.map(r => (l.head, r)).getOrElse(findNDiffs(n)(l.tail))
		}

		val (s1, s2) = findNDiffs(1)(lines)
		s1.intersect(s2)
	}
}
