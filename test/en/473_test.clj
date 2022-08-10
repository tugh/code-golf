(ns en.473-test
  (:require
   [clojure.test :refer [are deftest testing]]
   [en.473 :refer [simplify]]))

(deftest simplify-tests
  (testing
   (are [numbers expected] (= (apply simplify numbers) expected)
     [10 10] [1 1]
     [1 3] [1 3]
     [2 4] [1 2]
     [100 40] [5 2])))
