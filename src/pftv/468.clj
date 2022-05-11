(ns pftv.468
  (:require
   [clojure.math.combinatorics :as comb]
   [clojure.string :as str]))

(defn- number->digits
  [n]
  (mapv #(Long/parseLong (str %))
        (str n)))

(defn- digits->numbers
  [digits]
  (->> digits
       str/join
       Long/parseLong))

(defn- swap-digits
  [digits [i j]]
  (-> digits
      transient
      (assoc! i (nth digits j)
              j (nth digits i))
      persistent!))

(defn- all-pair-swaps
  [digits]
  (->> (comb/combinations (range (count digits)) 2)
       (map #(swap-digits digits %))))

(defn swapmaxmin
  [n]
  (->> n
       number->digits
       all-pair-swaps
       (remove #(-> % first zero?))
       (map digits->numbers)
       (concat [n])
       (apply (juxt max min))))
