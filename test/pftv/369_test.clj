(ns pftv.369-test
  (:require [clojure.test :refer :all]
            [pftv.369 :refer [depth]]))

(deftest depth-tests
  (testing "Default tests"
    (are [x y] (= x y)
      0 (depth 0)
      1 (depth [])
      3 (depth [[0] [2] [1 [2]]]))))
