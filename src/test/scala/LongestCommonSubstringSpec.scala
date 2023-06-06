package pl.archiprogram.here

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks

import scala.util.Random

class LongestCommonSubstringSpec
    extends AnyFlatSpec
    with Matchers
    with TableDrivenPropertyChecks {

  forAll(
    Table(
      ("algo", "name"),
      (NoDeps, "No dependency implementation")
    )
  ) { (algo, name) =>
    behavior of name

    forAll(
      Table(
        ("s1", "s2", "result"),
        ("abc", "def", None),
        ("abcdef", "bcde", Some(1, 0, "bcde")),
        ("ab", "abcdef", Some(0, 0, "ab")),
        ("ef", "abcdef", Some(0, 4, "ef")),
        ("abcdefabc", "zzbcdzz", Some(1, 2, "bcd")),
        ("aaaxbbbxxxcccxxx", "axxx", Some(7, 1, "xxx")),
        ("aaaxbbbxxxcccxxx", "caaazxxxzzxxx", Some(0, 1, "aaa"))
      )
    ) { (s1, s2, result) =>
      it should s"process $s1 and $s2 correctly" in {
        LongestCommonSubstring.of(s1, s2, algo) shouldBe result
      }
    }

    it should "work for a long string as well" in {
      val charA = Random.nextString(1)
      val charB = Random.nextString(1)
      val timesA = Random.between(800, 1200)
      val timesB = Random.between(800, 1200)
      //looking for BBB in AAABBBAAA
      val searchIn =
        charA.repeat(timesA) + charB.repeat(timesB) + charA.repeat(timesA)
      val searchFor = charB.repeat(timesB)

      LongestCommonSubstring.of(searchFor, searchIn, algo) shouldBe Some(
        0,
        timesA,
        searchFor
      )
    }
  }
}
