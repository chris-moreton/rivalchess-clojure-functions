(ns rivalchess-text.search.move-generator-test
  (:require [clojure.test :refer :all]
            [rivalchess-text.model.game_model :refer :all]
            [rivalchess-text.search.move-generator :refer :all]))

(deftest getPieceBitboardForColour-test
  (testing "Get piece bitboard for colour"
  (let [gameModel (gameModel "6k1/6p1/1p2q2p/1p5P/1P3RP1/2PK1B2/1r2N3/8 b kQKq g3 5 56")
        bitboards (:bitboards gameModel)]
    (is (= (:blackPawns bitboards) (bitboardForMover gameModel :pawn :black)))
    (is (not= (:whitePawns bitboards) (bitboardForMover gameModel :pawn :black))))))