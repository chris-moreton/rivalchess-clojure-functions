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


