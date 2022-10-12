(ns pftv.454-test
  (:require
   [clojure.test :refer [are deftest testing]]
   [pftv.454 :refer [apply-bs]]))

(deftest apply-bs-tests
  (testing "Default tests"
    (are [s expected] (= (apply-bs s) expected)
      "abc#" "ab"
      "abc###" ""
      "###abc" "abc"
      "there###eir" "their")))
