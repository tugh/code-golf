(ns util
  (:require
   [clojure.string :as str]))

(defn digits->number
  [digits]
  (->> digits
       str/join
       Long/parseLong))

(defn number->digits
  [n]
  (mapv #(Long/parseLong (str %))
        (str n)))

(defn two?
  [x]
  (= x 2))

(defn at-least-two?
  [x]
  (>= x 2))

(defn upper?
  [s]
  (= (str s) (str/upper-case s)))

(defn capitalized?
  "Is the first character of the string a capital letter?"
  [s]
  (-> s first upper?))

(defn ends-with-dot?
  [s]
  (-> s last (= \.)))

(defn letter?
  [c]
  (Character/isLetter c))

(defn alphabetic?
  [s]
  (every? letter? s))
