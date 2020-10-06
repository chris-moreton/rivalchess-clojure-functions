(ns rivalchess-text.search.move-generator-test
  (:require [clojure.test :refer :all]
            [rivalchess-text.model.game_model :refer :all]
            [rivalchess-text.search.move-generator :refer :all]))

(deftest getPieceBitboardForColour-test
  (testing "Get piece bitboard for colour"
  (let [position (position "6k1/6p1/1p2q2p/1p5P/1P3RP1/2PK1B2/1r2N3/8 b kQKq g3 5 56")
        bitboards (:bitboards position)]
    (is (= (:blackPawns bitboards) (bitboardForMover position :pawn)))
    (is (not= (:whitePawns bitboards) (bitboardForMover position :pawn))))))

(deftest bitRefList-test
  (testing "Get list of set bits in Long"
    (let [position (position "n5k1/6n1/1n2q2p/1p5P/1P3RP1/2PK1B2/1r2N3/8 b kQKq g3 5 56")
          bitboards (:bitboards position)]
      (is (= [46 49 63] (bitRefList (:blackKnights bitboards))))
      )))

(deftest bitString-test
  (testing "Test conversion of bitboard to a string of 1s and 0s"
    (is (= "0000000000000000000000000000000000000000000000000000000000001111" (bitString 15)))
    ))

(deftest allSquaresExceptFriendly-test
  (testing "Get a bitboard with all bits set except friendly pieces"
    (let [position (position "n5k1/6n1/1n2q2p/1p5P/1P3RP1/2PK1B2/1r2N3/8 b kQKq g3 5 56")]
      (is (= "0111110111111101101101101011111111111111111111111011111111111111" (bitString (allSquaresExceptFriendly position))))
      )))

(deftest movesToSquares-test
  (testing "Test creating a list of moves from a fromSquare and a list of toSquares"
    (is (= [(+ (bit-shift-left 11 16) 22)
            (+ (bit-shift-left 11 16) 33)
            (+ (bit-shift-left 11 16) 44)
            ] (movesToSquares 11 [22 33 44])))
    ))

(deftest knightMoves-test
  (testing "Test generating knight moves"
    (let [position (position "n5k1/6n1/1n2q2p/1p5P/1P3RP1/2PK1B2/1r2N3/8 b kQKq g3 5 56")]
      (is (= ["a8c7" "b6a4" "b6c4" "b6c8" "b6d5" "b6d7" "g7e8" "g7f5" "g7h5"]
             (into [] (sort (map algebraicMoveFromCompactMove (knightMoves position))))))
      )
    (let [position (position "n5k1/6n1/1n2q2p/1p5P/1P3RP1/2PK1B2/1r2N3/8 w kQKq g3 5 56")]
      (is (= ["e2c1" "e2d4" "e2g1" "e2g3"]
             (into [] (sort (map algebraicMoveFromCompactMove (knightMoves position))))))
      )
    ))

(deftest kingMoves-test
  (testing "Test generating king moves"
    (let [position (position "n5k1/6n1/1n2q2p/1p5P/1P3RP1/2PK1B2/1r2N3/8 b kQKq g3 5 56")]
      (is (= ["g8f7" "g8f8" "g8h7" "g8h8"]
             (into [] (sort (map algebraicMoveFromCompactMove (kingMoves position))))))
      )
    (let [position (position "n5k1/6n1/1n2q2p/1p5P/1P3RP1/2PK1B2/3rN3/8 w - - 0 1")]
      (is (= ["d3c2" "d3c4" "d3d2" "d3d4" "d3e3" "d3e4"]
             (into [] (sort (map algebraicMoveFromCompactMove (kingMoves position))))))
      )
    ))