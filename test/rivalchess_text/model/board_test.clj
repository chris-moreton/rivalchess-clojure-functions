(ns rivalchess-text.model.board-test
  (:require [clojure.test :refer :all]
            [rivalchess-text.model.board :refer :all]))

(deftest fenBoardPart-test
  (testing "Extract board part from FEN"
    (is (= "6k1/6p1/1p2q2p/1p5P/1P3RP1/2PK1B2/1r2N3/8"
           (fenBoardPart "6k1/6p1/1p2q2p/1p5P/1P3RP1/2PK1B2/1r2N3/8 b - g3 5 56")))))

(deftest fenRanks-test
  (testing "Extract ranks from FEN board part from"
    (is (= ["6k1" "6p1" "1p2q2p" "1p5P" "1P3RP1" "2PK1B2" "1r2N3" "8"] 
           (fenRanks "6k1/6p1/1p2q2p/1p5P/1P3RP1/2PK1B2/1r2N3/8")))))

(deftest rankBits-test (testing "Test conversion from FEN rank string into char array of eight 0s and 1s"
   (is (= [0 0 0 0 0 0 0 0] (rankBits (seq "8") \Q)))
   (is (= [0 0 0 0 0 0 1 0] (rankBits (seq "6k1") \k)))
   (is (= [0 0 0 0 0 0 0 0] (rankBits (seq "6k1") \q)))
   (is (= [0 0 0 0 0 0 1 0] (rankBits (seq "6p1") \p)))
   (is (= [0 0 0 0 0 0 1 1] (rankBits (seq "6pp") \p)))
   (is (= [1 0 0 0 0 0 0 0] (rankBits (seq "P7") \P)))
   (is (= [0 1 0 0 0 0 0 1] (rankBits (seq "1p2q2p") \p)))
   ))

