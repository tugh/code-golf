(ns en.475-test
  (:require
   [clojure.test :refer [are deftest testing]]
   [en.475 :refer [lcm]]))

(deftest lcm-tests
  (testing
   (are [xs expected] (= (lcm xs) expected)
     [] nil
     [10] 10
     [2 4] 4
     [3 7] 21
     [2 4 10] 20
     [15 2 4] 60)))
