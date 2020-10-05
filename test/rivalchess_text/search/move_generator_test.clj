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

;(deftest movesToSquares-test
;  (testing "Test creating a list of moves from a fromSquare and a list of toSquares"
;    (is (= [1 1 1 1] (movesToSquares 11 [22 33 44])))
;    ))