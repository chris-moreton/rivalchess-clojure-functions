(ns rivalchess-text.model.game_model-test
  (:require [clojure.test :refer :all]
            [rivalchess-text.model.game_model :refer :all]))

(deftest fenBoardPart-test
  (testing "Extract board part from FEN"
    (is (= "6k1/6p1/1p2q2p/1p5P/1P3RP1/2PK1B2/1r2N3/8"
           (fenBoardPart "6k1/6p1/1p2q2p/1p5P/1P3RP1/2PK1B2/1r2N3/8 b - g3 5 56")))))

(deftest fenRanks-test
  (testing "Extract ranks from FEN board part from"
    (is (= ["6k1" "6p1" "1p2q2p" "1p5P" "1P3RP1" "2PK1B2" "1r2N3" "8"]
           (fenRanks "6k1/6p1/1p2q2p/1p5P/1P3RP1/2PK1B2/1r2N3/8")))))

(deftest rankBits-test (testing "Test conversion from FEN rank string into char array of eight 0s and 1s for a given piece"
   (is (= [0 0 0 0 0 0 0 0] (rankBits (seq "8") \Q)))
   (is (= [0 0 0 0 0 0 1 0] (rankBits (seq "6k1") \k)))
   (is (= [0 0 0 0 0 0 0 0] (rankBits (seq "6k1") \q)))
   (is (= [0 0 0 0 0 0 1 0] (rankBits (seq "6p1") \p)))
   (is (= [0 0 0 0 0 0 1 1] (rankBits (seq "6pp") \p)))
   (is (= [1 0 0 0 0 0 0 0] (rankBits (seq "P7") \P)))
   (is (= [0 1 0 0 0 0 0 1] (rankBits (seq "1p2q2p") \p)))
   ))

(deftest boardBits-test (testing "Test conversion from FEN string into char array of 64 0s and 1s for a given piece"
  (let [fen "6k1/6p1/1p2q2p/1p5P/1P3RP1/2PK1B2/1r2N3/8 b kQKq g3 5 56"]
    (is (= [0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 1 0 0 0 0 0 1 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0]
           (boardBits (fenRanks (fenBoardPart fen)) \p)))
    )
))

(deftest pieceBitboardFromFen-test (testing "Test conversion from FEN rank string into char array of eight 0s and 1s"
  (let [fen "6k1/6p1/1p2q2p/1p5P/1P3RP1/2PK1B2/1r2N3/8 b Q g3 5 56"]
    (is (= 6.34693087133696E14 (pieceBitboard (fenRanks (fenBoardPart fen)) \p)))
    )
  ))

(deftest pieceBitboardFromFen-test (testing "Convert algebraic square to bit reference"
                                     (is (= 63 (bitRefFromAlgebraicSquareRef "a8")))
                                     (is (= 0 (bitRefFromAlgebraicSquareRef "h1")))
                                     (is (= 1 (bitRefFromAlgebraicSquareRef "g1")))
                                     )
                                     )

(deftest boardFromFen-test (testing "Test conversion from FEN rank string into board data"
      (let [fen "6k1/6p1/1p2q2p/1p5P/1P3RP1/2PK1B2/1r2N3/8 b q g3 5 56"
            board (position fen)
            bitboards (:bitboards board)]
        (is (= 5404360704 (:whitePawns bitboards)))
        (is (= 2048 (:whiteKnights bitboards)))
        (is (= 1048576 (:whiteKing bitboards)))
        (is (= 262144 (:whiteBishops bitboards)))
        (is (= 0 (:whiteQueens bitboards)))
        (is (= 67108864 (:whiteRooks bitboards)))
        (is (= 634693087133696 (:blackPawns bitboards)))
        (is (= 0 (:blackKnights bitboards)))
        (is (= 144115188075855872 (:blackKing bitboards)))
        (is (= 0 (:blackBishops bitboards)))
        (is (= 8796093022208 (:blackQueens bitboards)))
        (is (= 16384 (:blackRooks bitboards)))
        (is (= :black (:mover board)))
        (is (= 17 (:enPassantSquare board)))
        )
      (let [fen "6k1/6p1/1p2q2p/1p5P/1P3RP1/2PK1B2/1r2N3/8 w kQ - 5 56"
            board (position fen)
            bitboards (:bitboards board)]
        (is (= :white (:mover board)))
        (is (= -1 (:enPassantSquare board)))
        (is (= 5 (:halfMoves board)))
        (is (= {:blackKing true :blackQueen false :whiteKing false :whiteQueen true} (:castlePrivs board)))
        )
      ))


