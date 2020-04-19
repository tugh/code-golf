(ns pftv.370-test
  (:require [clojure.test :refer :all]
            [pftv.370 :refer [->hashtags]]))

(deftest ->hastags-tests
  (testing "Default tests"
    (are [x y] (= x y)
      '("#violently" "#americans" "#looting") (->hashtags "Violently Bored Americans Begin Looting Puzzle Stores")
      '("#government" "#receiving" "#included") (->hashtags "Trump Quietly Checks With Aides To Make Sure He’d Be Included In Receiving $1,000 Government Checks")
      '("#demands" "#footage" "#running") (->hashtags "Nation Demands More Slow-Motion Footage Of Running Basset Hounds"))))
