(ns en.477-test
  (:require
   [clojure.test :refer [are deftest testing]]
   [en.477 :refer [assemble]]))

(deftest assemble-tests
  (testing
   (are [s l m expected] (= (assemble s l m) expected)
     100 100 2 {:small 1 :large 0}
     100 100 1 nil
     100 100 10 {:small 0 :large 2}
     10 2 20 {:large 2 :small 5})))
