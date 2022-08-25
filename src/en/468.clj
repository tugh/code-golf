(ns en.468
  "Maxie and Minnie

   The maxxie of a number n is the largest number you can achieve by swapping
   two of its digits (in decimal) (or choosing not to swap if it is already the
   largest possible). The minnie is the smallest with one swap (though you can't
   swap a zero digit into the most significant position).

   Your task is to write a function that takes an integer and returns a tuple of
   the maxxie and minnie."
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
