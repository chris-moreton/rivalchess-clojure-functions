(ns rivalchess-text.core-test
  (:require [clojure.test :refer :all]
            [rivalchess-text.core :refer :all]))

(deftest southFill-test
  (testing "South Fill"
    (is (= 4629952088967215103 (southFill 4611936708517363882)))))

(deftest northFill-test
  (testing "North Fill"
    (is (= -1332566 (northFill 4611936708517363882)))))

(deftest rankBitboards-test (testing "RANK_8 is calculated correctly" (is (= -72057594037927936 RANK_8))))

(deftest fileBitboards-test
  (testing "RANK_8 is calculated correctly"
    (is (= -9187201950435737472 FILE_A)))
    (is (= 72340172838076673 FILE_H)))

(deftest middleFiles-test
  (testing "RANK_8 is calculated correctly"
    (is (= 24 MIDDLE_FILES_8_BIT))
    (is (= 231 NONMID_FILES_8_BIT))))



