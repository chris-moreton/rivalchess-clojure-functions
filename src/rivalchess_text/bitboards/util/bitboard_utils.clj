(ns rivalchess-text.bitboards.util.bitboard_utils
  (:use [rivalchess-text.bitboards.bitboard_constants :refer :all]))

(defn orWithURightShiftedSelf [x y] (bit-or x (unsigned-bit-shift-right x y)))
(defn orWithLeftShiftedSelf [x y] (bit-or x (bit-shift-left x y)))
(defn southFill [x] (orWithURightShiftedSelf (orWithURightShiftedSelf (orWithURightShiftedSelf x 8) 16) 32))
(defn northFill [x] (orWithLeftShiftedSelf (orWithLeftShiftedSelf (orWithLeftShiftedSelf x 8) 16) 32))
(defn everyEighthBitFrom [x] (if (< x 8) (bit-shift-left 1 x) (bit-or (bit-shift-left 1 x) (everyEighthBitFrom (- x 8)))))
(defn setBits [x] (if (empty? x) 0 (bit-or (bit-shift-left 1 (last x)) (setBits (pop x)))))
(defn getPawnMovesCaptureOfColour [colour] (if (= colour :white) whitePawnMovesCapture blackPawnMovesCapture))
