(ns pftv.369-test
  (:require [clojure.test :refer [are deftest testing]]
            [pftv.369 :refer [depth]]))

(deftest depth-tests
  (testing "Default tests"
    (are [x y] (= (depth x) y)
      0  0
      [] 1
      [[0] [2] [1 [2]]] 3)))
