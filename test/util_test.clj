(ns util-test
  (:require
   [clojure.test :refer [are deftest testing]]
   [util :refer [digits->number number->digits]]))

(deftest digits->number-tests
  (testing
   (are [digits number] (= (digits->number digits) number)
     [1] 1
     [1 2 3] 123
     [1 2 9 1 2 9 1 2 0 2 1] 12912912021)))

(deftest number->digits-tests
  (testing
   (are [number digits] (= (number->digits number) digits)
     1 [1]
     123 [1 2 3]
     12912912021 [1 2 9 1 2 9 1 2 0 2 1])))

(deftest cross-tests
  (let [n1 (rand-int 1000000000)
        n2 (rand-int 1000000000)
        n3 (rand-int 1000000000)]
    (testing "number->digits->number"
      (are [number] (= number (-> number
                                  number->digits
                                  digits->number))
        n1
        n2
        n3))))
