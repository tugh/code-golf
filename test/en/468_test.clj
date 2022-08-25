(ns en.468-test
  (:require
   [clojure.test :refer [are deftest testing]]
   [en.468 :refer [swapmaxmin]]))

(deftest swapmaxmin-tests
  (testing "Default tests"
    (are [n maxxie minnie] (= (swapmaxmin n) [maxxie minnie])
      12340
      42310 10342

      98761
      98761 18769

      9000
      9000 9000

      11321
      31121 11123

      213
      312 123

      12345
      52341 12345

      100
      100 100)))
