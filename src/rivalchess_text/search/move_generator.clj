(ns rivalchess-text.search.move-generator
  (:require [rivalchess-text.model.game_model]))

(defn bitboardForMover [gameModel piece mover]
  (let [index (case piece
    :king (if (= mover :white) :whiteKing :blackKing)
    :knight (if (= mover :white) :whiteKnights :blackKnights)
    :queen (if (= mover :white) :whiteQueens :blackQueens)
    :bishop (if (= mover :white) :whiteBishops :blackBishops)
    :rook (if (= mover :white) :whiteRooks :blackRooks)
    :pawn (if (= mover :white) :whitePawns :blackPawns))]
    (index (:bitboards gameModel))))

(defn bitRefList [bitboard]
  (loop [bitboard bitboard
         result []]
    (if (= 0 bitboard)
      result
      (let [square (Long/numberOfTrailingZeros bitboard)
            bitMask (bit-shift-left 1 square)]
        (recur (bit-xor bitboard bitMask) (conj result square))
      ))))

(defn knightMoves [gameModel]

  )
;private fun generateKnightMoves() {
;                                   applyToSquares(knightBitboardForMover) { from ->
;                                                                           val fromShifted = from shl 16
;                                                                           applyToSquares(knightMoves[from] and allSquaresExceptFriendlyPieces) { to ->
;                                                                                                                                                 moves[moveCount++] = (fromShifted or to)
;                                                                                                                                                 }
;                                                                           }
;                                   }
;
