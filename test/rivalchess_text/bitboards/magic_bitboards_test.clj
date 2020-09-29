(ns rivalchess-text.bitboards.magic-bitboards-test
  (:require [clojure.test :refer :all]
            [rivalchess-text.bitboards.magic-bitboards :refer :all]))

(deftest rookMagicMovesLookup-test
  (testing "Rook Magic Moves Sample"
    (is (= 16544 (lookup2d magicMovesRook 6 2)))))

(deftest bishopMagicMovesLookup-test
  (testing "Bishop Magic Moves Sample"
    (is (= 1089536 (lookup2d magicMovesBishop 6 2)))))


