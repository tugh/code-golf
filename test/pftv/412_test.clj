(ns pftv.412-test
  (:require
   [clojure.test :refer [are deftest testing]]
   [pftv.412 :refer [valid-name?]]))

(deftest winner-tests
  (testing "Default tests"
    (are [x y] (= (valid-name? x) y)
      "George R. R. Martin" true
      "Abraham Lincoln" true
      "J. R. Bob Dobbs" true
      "H. G. Wells" true
      "J R Tolkien" false
      "J. F. K." false
      "Franklin" false)))
