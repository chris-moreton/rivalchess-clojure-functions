(ns rivalchess-text.model.board
  (:require [clojure.string :as str]))

(defn isFileNumber [c] (some #(= c %) (seq "12345678")))

(defn rankBits [fenRankChars pieceChar]
  (loop [fenRankChars (seq fenRankChars)
         result []]
    (if (= [] fenRankChars)
      result
      (recur (into [] (rest fenRankChars))
             (let [c (first fenRankChars)]
               (concat result
                       (if (isFileNumber c)
                         (repeat (- (int c) 48) 0)
                         (if (= c pieceChar) [1] [0]))))))))

(defn fenBoardPart [fen] (first (str/split fen #" ")))

(defn fenRanks [fenBoardPart] (str/split fenBoardPart #"/"))

(defn boardBits [fenRanks pieceChar]
  (loop [fenRanks fenRanks
         result []]
    (if (= [] fenRanks)
      (into [] result)
      (recur (rest fenRanks) (concat result (rankBits (first fenRanks) pieceChar)))
      )
    )
  )

(defn bitArrayToDecimal [bits]
  (loop [bits bits
         bitnum 63
         result 0]
    (if (= bitnum -1)
      result
      (recur (rest bits) (dec bitnum) (+ result (if (= (first bits) 1) (Math/pow 2 bitnum) 0)))
      ))
  )

(defn bitRefFromAlgebraicSquareRef [algebraic]
  (let [s (seq algebraic)
        fileNum (- (int (first s)) 97)
        rankNum (- (int (first (rest s))) 49)]
    (+ (* rankNum 8) (- 7 fileNum))
    )
  )

(defn enPassantBitRef [enPassantFenPart]
  (if (= enPassantFenPart "-") -1 (bitRefFromAlgebraicSquareRef enPassantFenPart)))

(defn pieceBitboard [fenRanks pieceChar]
  (bitArrayToDecimal (boardBits fenRanks pieceChar)))

(defn pieceBitboards [fen]
  (let [fenRanks (fenRanks (fenBoardPart fen))]
     {:whitePawns (pieceBitboard fenRanks \P)
      :whiteKnights (pieceBitboard fenRanks \N)
      :whiteBishops (pieceBitboard fenRanks \B)
      :whiteQueens (pieceBitboard fenRanks \Q)
      :whiteKing (pieceBitboard fenRanks \K)
      :whiteRooks (pieceBitboard fenRanks \R)
      :blackPawns (pieceBitboard fenRanks \p)
      :blackKnights (pieceBitboard fenRanks \n)
      :blackBishops (pieceBitboard fenRanks \b)
      :blackQueens (pieceBitboard fenRanks \q)
      :blackKing (pieceBitboard fenRanks \k)
      :blackRooks (pieceBitboard fenRanks \r)
      }))

(defn fenPart [fen index]
  (nth (str/split fen #" ") index))

(defn mover [fen]
  (let [mover (fenPart fen 1)]
    (if (= mover "w") :white :black)
    ))

(defn enpassantFenPart [fen]
  (let [enpassantSquare (fenPart fen 3)]
    enpassantSquare
    ))

(defn board [fen] {
                   :bitboards (pieceBitboards fen)
                   :mover (mover fen)
                   :enPassantSquare (enPassantBitRef (enpassantFenPart fen))
                   :castlePrivs 0
                   :halfMoves (Integer/parseInt (fenPart fen 4))
                   :moveHistory []
                   })


