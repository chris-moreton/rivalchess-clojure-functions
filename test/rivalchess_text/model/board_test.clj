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
  (let [fen "6k1/6p1/1p2q2p/1p5P/1P3RP1/2PK1B2/1r2N3/8 b - g3 5 56"]
    (is (= [0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 1 0 0 0 0 0 1 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0]
           (boardBits (fenRanks (fenBoardPart fen)) \p)))
    )
))

(deftest pieceBitboardFromFen-test (testing "Test conversion from FEN rank string into char array of eight 0s and 1s"
  (let [fen "6k1/6p1/1p2q2p/1p5P/1P3RP1/2PK1B2/1r2N3/8 b - g3 5 56"]
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
      (let [fen "6k1/6p1/1p2q2p/1p5P/1P3RP1/2PK1B2/1r2N3/8 b - g3 5 56"
            board (board fen)
            bitboards (:bitboards board)]
        (is (= 5.404360704E9 (:whitePawns bitboards)))
        (is (= 2048.0 (:whiteKnights bitboards)))
        (is (= 1048576.0 (:whiteKing bitboards)))
        (is (= 262144.0 (:whiteBishops bitboards)))
        (is (= 0 (:whiteQueens bitboards)))
        (is (= 6.7108864E7 (:whiteRooks bitboards)))
        (is (= 6.34693087133696E14 (:blackPawns bitboards)))
        (is (= 0 (:blackKnights bitboards)))
        (is (= 1.44115188075855872E17 (:blackKing bitboards)))
        (is (= 0 (:blackBishops bitboards)))
        (is (= 8.796093022208E12 (:blackQueens bitboards)))
        (is (= 16384.0 (:blackRooks bitboards)))
        (is (= :black (:mover board)))
        (is (= 17 (:enPassantSquare board)))
        )
      (let [fen "6k1/6p1/1p2q2p/1p5P/1P3RP1/2PK1B2/1r2N3/8 w - - 5 56"
            board (board fen)
            bitboards (:bitboards board)]
        (is (= :white (:mover board)))
        (is (= -1 (:enPassantSquare board)))
        (is (= 5 (:halfMoves board)))
        )
      ))


