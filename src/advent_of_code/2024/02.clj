(ns advent-of-code.2024.02
  (:require
   [advent-of-code.2024.io :as io]
   [clojure.string :as str]))

(defn read-input!
  []
  (->> "02"
       io/read-input-as-lines!
       (map #(str/split % #" "))
       (map #(map (fn [x] (Integer/parseInt x)) %))))

(defn prepare
  [xs]
  (map vector (butlast xs) (rest xs)))

(defn all-increasing?
  [xs]
  (every? (fn [[a b]] (> b a)) xs))

(defn all-decreasing?
  [xs]
  (every? (fn [[a b]] (< b a)) xs))

(defn obey-limits?
  [xs]
  (every? (fn [[a b]] (< (abs (- a b)) 4)) xs))

(defn solve-p1!
  []
  (->> (read-input!)
       (map prepare)
       (filter #(and (or (all-increasing? %)
                         (all-decreasing? %))
                     (obey-limits? %)))
       count))

(comment
  (solve-p1!)
  ;;=> 502
  
  )