(ns util-test
  (:require
   [clojure.test :refer [are deftest testing]]
   [util :refer [digits->number number->digits two? at-least-two? upper? capitalized? ends-with-dot? letter? alphabetic?]]))

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

(deftest two?-tests
  (testing "two?"
    (are [x expected] (= (two? x) expected)
      0 false
      1 false
      2 true
      3 false
      42 false
      666 false)))

(deftest at-least-two?-tests
  (testing "at-least-two?"
    (are [x expected] (= (at-least-two? x) expected)
      0 false
      1 false
      2 true
      3 true
      42 true
      666 true)))

(deftest upper?-tests
  (testing "upper?"
    (are [s expected] (= (upper? s) expected)
      "s" false
      "S" true
      "1" true
      "Selam" false
      "SELAM" true
      \a false
      \A true
      \1 true
      \. true)))

(deftest capitalized?-test
  (testing "capitalized?"
    (are [s expected] (= (capitalized? s) expected)
      "s" false
      "S" true
      "1" true
      "1a" true
      "a1" false
      "Selam" true
      "SELAM" true)))

(deftest ends-with-dot?-tests
  (testing "ends-with-dot?"
    (are [s expected] (= (ends-with-dot? s) expected)
      "a" false
      ".a" false
      "..." true
      "selam" false
      "selam." true
      "SELAM." true
      "........." true
      [\. \. \.] true)))

(deftest letter?-tests
  (testing "letter?"
    (are [c expected] (= (letter? c) expected)
      \1 false
      \. false
      \\ false
      \a true
      \z true)))

(deftest alphabetic?-tests
  (testing "alphabetic?"
    (are [s expected] (= (alphabetic? s) expected)
      "Selam" true
      "s3l4m" false
      "111" false
      [\1 \1 \1] false
      [\a \b \c] true)))
