(ns rivalchess-text.search.move-generator
  (:use [rivalchess-text.bitboards.util.bitboard_utils]))

(defn bitboardForMover [position piece]
  (let [mover (:mover position)
        index (case piece
    :king (if (= mover :white) :whiteKing :blackKing)
    :knight (if (= mover :white) :whiteKnights :blackKnights)
    :queen (if (= mover :white) :whiteQueens :blackQueens)
    :bishop (if (= mover :white) :whiteBishops :blackBishops)
    :rook (if (= mover :white) :whiteRooks :blackRooks)
    :pawn (if (= mover :white) :whitePawns :blackPawns))]
    (index (:bitboards position))))

(defn bitRefList [bitboard]
  (loop [bitboard bitboard
         result []]
    (if (= 0 bitboard)
      result
      (let [square (Long/numberOfTrailingZeros bitboard)
            bitMask (bit-shift-left 1 square)]
        (recur (bit-xor bitboard bitMask) (conj result square))
      ))))

(defn bitString [bitboard]
  (loop [bitboard bitboard
         bitRef 63
         result ""]
    (if (= -1 bitRef)
      result
      (let [bitMask (bit-shift-left 1 bitRef)]
        (recur (bit-xor bitboard bitMask) (dec bitRef) (str result (if (= bitMask (bit-and bitMask bitboard)) "1" "0")))
        ))))

(defn allSquaresExceptFriendly [position]
(let [bitboards (:bitboards position)]
(bit-not (reduce bit-or (if (= (:mover position) :white)
                          [(:whitePawns bitboards)
                           (:whiteKnights bitboards)
                           (:whiteQueens bitboards)
                           (:whiteBishops bitboards)
                           (:whiteRooks bitboards)
                           (:whiteKing bitboards)]
                          [(:blackPawns bitboards)
                           (:blackKnights bitboards)
                           (:blackQueens bitboards)
                           (:blackBishops bitboards)
                           (:blackRooks bitboards)
                           (:blackKing bitboards)])))))

(defn movesToSquares [fromSquare toSquares]
  (let [shiftedFrom (bit-shift-left fromSquare 16)]
    (loop [toSquares toSquares
         moves []]
    (if (= [] toSquares)
      moves
      (recur (into [] (rest toSquares)) (conj moves (bit-or shiftedFrom (first toSquares))))
      ))))

(defn knightMoves [position]
  (let [bitboard (bitboardForMover position :knight)]
    (loop [fromSquares (bitRefList bitboard)
           moves []]
       (if (= [] fromSquares)
         moves
         (let [fromSquare (first fromSquares)
               toSquares (and (fromSquare KNIGHT_MOVES) (allSquaresExceptFriendly position))]
               (recur (rest fromSquares) (movesToSquares fromSquare toSquares)))))))