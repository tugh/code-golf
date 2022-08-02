(ns en.472-test
  (:require
   [clojure.test :refer [are deftest testing]]
   [en.472 :refer [move]]))

(deftest move-tests
  (testing
   (are [distances expected] (= (move distances) expected)
     [] [0 0]
     [10] [0 10]
     [10 2] [2 10]
     [10 2 3] [2 7])))
