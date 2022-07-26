(ns en.471-test
  (:require
   [clojure.test :refer [are deftest testing]]
   [en.471 :refer [regroupv1 regroupv2]]))

(deftest regroup-tests
  (testing
   (are [plate size expected] (and (= (regroupv1 plate size) expected)
                                   (= (regroupv2 plate size) expected))
     "A5-GG-B88" 3 "A-5GG-B88"
     "A5-GG-B88" 2 "A-5G-GB-88"
     "6776"      2 "67-76"
     "F33"       1 "F-3-3"
     "IIO"       7 "IIO")))
