package pl.archiprogram.here

trait LongestCommonSubstring {
  def findLongestCommonSubstring(
      s1: String,
      s2: String
  ): Option[(Int, Int, String)]
}

object LongestCommonSubstring {
  private val default: LongestCommonSubstring = CscMatrix

  def of(
      s1: String,
      s2: String,
      algo: LongestCommonSubstring = default
  ): Option[(Int, Int, String)] =
    if (s1.length <= s2.length)
      algo.findLongestCommonSubstring(s1, s2)
    else
      algo.findLongestCommonSubstring(s2, s1) match {
        case Some((i1, i2, s)) => Some(i2, i1, s)
        case None              => None
      }
}
