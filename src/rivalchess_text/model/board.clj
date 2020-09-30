(ns rivalchess-text.model.board
  (:require [clojure.string :as str]))

; example FEN: "6k1/6p1/1p2q2p/1p5P/1P3RP1/2PK1B2/1r2N3/8 b - g3 5 56"

(defn bitboards [fenBoardPart]
  (:whitePawns 0 :whiteKnights 0 :whiteBishops 0 :whiteQueens 0 :whiteKing 0 :whiteRooks 0
    :blackPawns 0 :blackKnights 0 :blackBishops 0 :blackQueens 0 :blackKing 0 :blackRooks 0
    :enPassantSquare 0 ))

(defn isFileNumber [c] (some #(= c %) (seq "12345678")))

(defn rankBits [fenRankChars pieceChar]
  (loop [rankChars (seq fenRankChars)
         result []]
    (if (= [] rankChars) result
      (recur (into [] (rest rankChars))
             (let [c (first rankChars)]
               (concat result
                       (if (isFileNumber c)
                         (repeat (- (int c) 48) 0)
                         (if (= c pieceChar) [1] [0]))))))))

(defn fenBoardPart [fen] (first (str/split fen #" ")))

(defn fenRanks [fenBoardPart] (str/split fenBoardPart #"/"))

(defn board [fen] (:bitboards (bitboards fen) :mover :white :castlePrivs 0 :halfMoves 0 :moveHistory []))
