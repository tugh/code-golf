(ns pftv.370-test
  (:require [clojure.test :refer [are deftest testing]]
            [pftv.370 :refer [->hashtags]]))

(deftest ->hashtags-tests
  (testing "Default tests"
    (are [x y] (= (->hashtags x) y)
      "Violently Bored Americans Begin Looting Puzzle Stores"
      '("#violently" "#americans" "#looting")

      "Trump Quietly Checks With Aides To Make Sure He’d Be Included In Receiving $1,000 Government Checks"
      '("#government" "#receiving" "#included")

      "Nation Demands More Slow-Motion Footage Of Running Basset Hounds"
      '("#demands" "#footage" "#running"))))
