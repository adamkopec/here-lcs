package pl.archiprogram.here

import breeze.linalg.CSCMatrix
private[here] object CscMatrix extends LongestCommonSubstring {
  override def findLongestCommonSubstring(
      s1: String,
      s2: String
  ): Option[(Int, Int, String)] =
    if (s1.isEmpty || s2.isEmpty)
      None
    else {
      val lengths: CSCMatrix[Int] =
        new CSCMatrix.Builder[Int](rows = s1.length, cols = s2.length).result

      s2.toList.zipWithIndex
        .flatMap { (s2Char, s2Index) =>
          val s1LengthPositions = s1.toList.zipWithIndex.map {
            (s1Char, s1Index) =>
              val length = if (s2Char == s1Char) {
                if (s1Index > 0 && s2Index > 0)
                  lengths(s1Index - 1, s2Index - 1) + 1
                else 1
              } else {
                0
              }

              if (length > 0) {
                lengths.update(s1Index, s2Index, length)
                Some((s1Index, s2Index, length))
              } else {
                None
              }
          }

          s1LengthPositions.flatMap(v => v.maxByOption(_._3))
        }
        .maxByOption(_._3)
        .flatMap { case (s1LastCharIndex, s2LastCharIndex, matchLength) =>
          Some(
            s1LastCharIndex - matchLength + 1,
            s2LastCharIndex - matchLength + 1,
            s1.substring(
              s1LastCharIndex - matchLength + 1,
              s1LastCharIndex + 1
            )
          )
        }
    }
}
