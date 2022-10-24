(ns pftv.417-test
  (:require
   [clojure.test :refer [are deftest testing]]
   [pftv.417 :refer [index-map index-map-v2]]))

(deftest index-map-tests
  (testing "index-map"
    (are [v expected] (= (index-map v) expected)
      [] {}
      [1 2 3] {1 #{0} 2 #{1} 3 #{2}}
      [1 1 1] {1 #{0 1 2}}
      [1 2 1 2 1] {1 #{0 2 4} 2 #{1 3}})))

(deftest index-map-v2-tests
  (testing "index-map"
    (are [v expected] (= (index-map-v2 v) expected)
      [] {}
      [1 2 3] {1 #{0} 2 #{1} 3 #{2}}
      [1 1 1] {1 #{0 1 2}}
      [1 2 1 2 1] {1 #{0 2 4} 2 #{1 3}})))
