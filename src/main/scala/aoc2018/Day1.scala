package aoc2018

object Day1 {
	val lines = readLines("input.txt")

	def apply() = {
		println(s"Part 1: ${part1()}")
		println(s"Part 2: ${part2()}")
	}

	def part1() = {
		lines.map(_.toInt).sum
	}

	def part2() = {
		def findDup(f: Stream[Int], s: Set[Int] = Set()): Int = {
			if (s(f.head)) f.head else findDup(f.tail, s + f.head)
		}

		val deltas = Stream.continually(lines.map(_.toInt).toStream).flatten
		val freqs = deltas.scanLeft(0)(_+_)
		findDup(freqs)
	}
}
