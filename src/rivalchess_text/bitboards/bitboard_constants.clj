(ns rivalchess-text.bitboards.bitboard_constants
  (:use [rivalchess-text.bitboards.util.bitboard_utils :refer :all]))

(def A1 7)
(def B1 6)
(def C1 5)
(def D1 4)
(def E1 3)
(def F1 2)
(def G1 1)
(def H1 0)

(def A2 (+ A1 8))
(def B2 (+ B1 8))
(def C2 (+ C1 8))
(def D2 (+ D1 8))
(def E2 (+ E1 8))
(def F2 (+ F1 8))
(def G2 (+ G1 8))
(def H2 (+ H1 8))

(def A3 (+ A2 8))
(def B3 (+ B2 8))
(def C3 (+ C2 8))
(def D3 (+ D2 8))
(def E3 (+ E2 8))
(def F3 (+ F2 8))
(def G3 (+ G2 8))
(def H3 (+ H2 8))

(def A4 (+ A3 8))
(def B4 (+ B3 8))
(def C4 (+ C3 8))
(def D4 (+ D3 8))
(def E4 (+ E3 8))
(def F4 (+ F3 8))
(def G4 (+ G3 8))
(def H4 (+ H3 8))

(def A5 (+ A4 8))
(def B5 (+ B4 8))
(def C5 (+ C4 8))
(def D5 (+ D4 8))
(def E5 (+ E4 8))
(def F5 (+ F4 8))
(def G5 (+ G4 8))
(def H5 (+ H4 8))

(def A6 (+ A5 8))
(def B6 (+ B5 8))
(def C6 (+ C5 8))
(def D6 (+ D5 8))
(def E6 (+ E5 8))
(def F6 (+ F5 8))
(def G6 (+ G5 8))
(def H6 (+ H5 8))

(def A7 (+ A6 8))
(def B7 (+ B6 8))
(def C7 (+ C6 8))
(def D7 (+ D6 8))
(def E7 (+ E6 8))
(def F7 (+ F6 8))
(def G7 (+ G6 8))
(def H7 (+ H6 8))

(def A8 (+ A7 8))
(def B8 (+ B7 8))
(def C8 (+ C7 8))
(def D8 (+ D7 8))
(def E8 (+ E7 8))
(def F8 (+ F7 8))
(def G8 (+ G7 8))
(def H8 (+ H7 8))

(def RANK_1 (setBits [0 1 2 3 4 5 6 7]))
(def RANK_2 (bit-shift-left RANK_1 8))
(def RANK_3 (bit-shift-left RANK_2 8))
(def RANK_4 (bit-shift-left RANK_3 8))
(def RANK_5 (bit-shift-left RANK_4 8))
(def RANK_6 (bit-shift-left RANK_5 8))
(def RANK_7 (bit-shift-left RANK_6 8))
(def RANK_8 (bit-shift-left RANK_7 8))

(def FILE_A (everyEighthBitFrom A8))
(def FILE_B (everyEighthBitFrom B8))
(def FILE_C (everyEighthBitFrom C8))
(def FILE_D (everyEighthBitFrom D8))
(def FILE_E (everyEighthBitFrom E8))
(def FILE_F (everyEighthBitFrom F8))
(def FILE_G (everyEighthBitFrom G8))
(def FILE_H (everyEighthBitFrom H8))

(def MIDDLE_FILES_8_BIT (setBits [D1 E1]))
(def NONMID_FILES_8_BIT (setBits [A1 B1 C1 F1 G1 H1]))

(def F1G1 (setBits [F1 G1]))
(def G1H1 (setBits [G1 H1]))
(def A1B1 (setBits [A1 B1]))
(def B1C1 (setBits [B1 C1]))
(def F8G8 (setBits [F8 G8]))
(def G8H8 (setBits [G8 H8]))
(def A8B8 (setBits [A8 B8]))
(def B8C8 (setBits [B8 C8]))

(def FILES [FILE_H FILE_G FILE_F FILE_E FILE_D FILE_C FILE_B FILE_A])

(def DARK_SQUARES (setBits [A1 A3 A5 A7 B2 B4 B6 B8 C1 C3 C5 C7 D2 D4 D6 D8 E1 E3 E5 E7 F2 F4 F6 F8 G1 G3 G5 G7 H2 H4 H6 H8]))
(def LIGHT_SQUARES (setBits [A2 A4 A6 A8 B1 B3 B5 B7 C2 C4 C6 C8 D1 D3 D5 D7 E2 E4 E6 E8 F1 F3 F5 F7 G2 G4 G6 G8 H1 H3 H5 H7]))

(def LOW32 (bit-or RANK_1 RANK_2 RANK_3 RANK_4))

(def WHITEKINGSIDECASTLESQUARES (setBits [F1 G1]))
(def WHITEQUEENSIDECASTLESQUARES (setBits [B1 C1 D1]))
(def BLACKKINGSIDECASTLESQUARES (setBits [F8 G8]))
(def BLACKQUEENSIDECASTLESQUARES (setBits [B8 C8 D8]))

(def WHITEKINGSIDECASTLEMOVEMASK (setBits [E1 G1]))
(def WHITEQUEENSIDECASTLEMOVEMASK (setBits [E1 C1]))
(def BLACKKINGSIDECASTLEMOVEMASK (setBits [E8 G8]))
(def BLACKQUEENSIDECASTLEMOVEMASK (setBits [E8 C8]))

(def WHITEKINGSIDECASTLEROOKMOVE (setBits [H1 F1]))
(def WHITEQUEENSIDECASTLEROOKMOVE (setBits [A1 D1]))
(def BLACKKINGSIDECASTLEROOKMOVE (setBits [H8 F8]))
(def BLACKQUEENSIDECASTLEROOKMOVE (setBits [A8 D8]))

(def WHITEKINGSIDEROOKMASK (setBits [H1]))
(def WHITEQUEENSIDEROOKMASK (setBits [A1]))
(def BLACKKINGSIDEROOKMASK (setBits [H8]))
(def BLACKQUEENSIDEROOKMASK (setBits [A8]))
