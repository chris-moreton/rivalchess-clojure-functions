(ns rivalchess-text.core)

(defn orWithURightShiftedSelf [x y] (bit-or x (unsigned-bit-shift-right x y)))
(defn orWithLeftShiftedSelf [x y] (bit-or x (bit-shift-left x y)))
(defn southFill [x] (orWithURightShiftedSelf (orWithURightShiftedSelf (orWithURightShiftedSelf x 8) 16) 32))
(defn northFill [x] (orWithLeftShiftedSelf (orWithLeftShiftedSelf (orWithLeftShiftedSelf x 8) 16) 32))
