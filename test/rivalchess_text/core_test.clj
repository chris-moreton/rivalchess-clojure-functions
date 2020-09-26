(ns rivalchess-text.core-test
  (:require [clojure.test :refer :all]
            [rivalchess-text.core :refer :all]))

(deftest southFill-test
  (testing "South Fill"
    (is (= 4629952088967215103 (southFill 4611936708517363882)))))

(deftest northFill-test
  (testing "North Fill"
    (is (= -1332566 (northFill 4611936708517363882)))))
