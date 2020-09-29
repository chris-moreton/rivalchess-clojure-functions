(ns rivalchess-text.bitboards.util.bitboard-utils-test
  (:require [clojure.test :refer :all]
            [rivalchess-text.bitboards.util.bitboard_utils :refer :all]))

(deftest southFill-test
  (testing "South Fill"
    (is (= 4629952088967215103 (southFill 4611936708517363882)))))

(deftest northFill-test
  (testing "North Fill"
    (is (= -1332566 (northFill 4611936708517363882)))))

(deftest rankBitboards-test (testing "RANK_8 is calculated correctly" (is (= -72057594037927936 RANK_8))))

(deftest constants-test
  (is (= -9187201950435737472 FILE_A))
  (is (= 72340172838076673 FILE_H))
  (is (= 24 MIDDLE_FILES_8_BIT))
  (is (= 231 NONMID_FILES_8_BIT))
  (is (= 6 F1G1))
  (is (= 3 G1H1))
  (is (= 192 A1B1))
  (is (= 96 B1C1))
  (is (= 432345564227567616 F8G8))
  (is (= 216172782113783808 G8H8))
  (is (= -4611686018427387904 A8B8))
  (is (= 6917529027641081856 B8C8))
  (is (= -6172840429334713771 LIGHT_SQUARES))
  (is (= 6172840429334713770 DARK_SQUARES))
  (is (= 4294967295 LOW32))
  )

