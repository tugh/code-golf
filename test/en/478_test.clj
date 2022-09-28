(ns en.478-test
  (:require
   [clojure.test :refer [are deftest testing]]
   [en.478 :refer [superdigit]]))

(deftest superdigit-tests
  (testing
   (are [n k expected] (= (superdigit n k) expected)
     1  1  1
     10 2 2
     11 3 6
     38 7 5
     38789789374294723947328946 1000000000000000 8)))
