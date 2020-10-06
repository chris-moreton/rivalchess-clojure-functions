(ns rivalchess-text.core
  (:require [rivalchess-text.bitboards.util.bitboard_utils :refer :all])
  (:require [rivalchess-text.bitboards.magic-bitboards :refer :all])
  (:require [rivalchess-text.search.move-generator :refer :all])
  (:require [rivalchess-text.model.game_model :refer :all]))

(println (into [] (sort (map algebraicMoveFromCompactMove (knightMoves (position "n5k1/6n1/1n2q2p/1p5P/1P3RP1/2PK1B2/1r2N3/8 b kQKq g3 5 56"))))))
