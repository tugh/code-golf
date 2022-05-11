(ns pftv.466-test
  (:require
   [clojure.test :refer [are deftest testing]]
   [pftv.466 :refer [sort-with]]))

(deftest sort-with-tests
  (testing "Default tests"
    (are [order coll expected] (= (sort-with order coll) expected)
      [:breakfast :lunch :dinner]
      #{:lunch :breakfast :dinner}
      '(:breakfast :lunch :dinner)

      [2 3 4 :jack :queen :king :ace]
      [4 2 4 :king 2]
      [2 2 4 4 :king])))
