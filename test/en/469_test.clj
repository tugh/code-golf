(ns en.469-test
  (:require
   [clojure.test :refer [are deftest testing]]
   [en.469 :refer [fib-seq]]))

(deftest fib-seq-tests
  (testing
   (are [n f x y expected] (= (take n (fib-seq f x y)) expected)
     3 str "j" "h"
     '("j" "h" "hj")

     5 str "e" "a"
     '("e" "a" "ae" "aea" "aeaae")

     6 str "n" "k"
     '("n" "k" "kn" "knk" "knkkn" "knkknknk")

     1 str "f" "g"
     '("f")

     7 + 1 1
     '(1 1 2 3 5 8 13)

     7 str "x" "o"
     '("x" "o" "ox" "oxo" "oxoox" "oxooxoxo" "oxooxoxooxoox")

     7 * 1 1
     '(1 1 1 1 1 1 1)

     7 * 1 2
     '(1 2 2 4 8 32 256)

     7 * 1 -1
     '(1 -1 -1 1 -1 -1 1))))
