(ns pftv.373-test
  (:require [clojure.test :refer :all]
            [pftv.373 :refer [winner]]))

(deftest winner-tests
  (testing "Default tests"
    (are [x y] (= x y)
      :x (winner [[:x :o :x]
                  [:o :x :o]
                  [:o :x :x]])
      :o (winner [[:o :o :o]
                  [:o :x :x]
                  [nil :x :x]])
      :draw (winner [[:x :x :o]
                     [:o :o :x]
                     [:x :x :o]]))))
