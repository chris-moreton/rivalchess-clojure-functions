# Clojure Chess Functions

A Clojure implementation of the current Kotlin version of Rival Chess.

This is a work in progress, just doing a few functions per day.

## Examples

    (sort (map algebraicMoveFromCompactMove 
        (knightMoves (position "n5k1/6n1/1n2q2p/1p5P/1P3RP1/2PK1B2/1r2N3/8 b kQKq g3 5 56"))))

    (a8c7 b6a4 b6c4 b6c8 b6d5 b6d7 g7e8 g7f5 g7h5)

