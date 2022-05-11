(ns pftv.373-test
  (:require [clojure.test :refer [are deftest testing]]
            [pftv.373 :refer [winner]]))

(deftest winner-tests
  (testing "Default tests"
    (are [x y] (= (winner x) y)
      [[:x :o :x]
       [:o :x :o]
       [:o :x :x]]
      :x

      [[:o :o :o]
       [:o :x :x]
       [nil :x :x]]
      :o

      [[:x :x :o]
       [:o :o :x]
       [:x :x :o]]
      :draw)))
