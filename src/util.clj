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
