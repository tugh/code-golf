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

(defn all-increasing?
  [xs]
  (every? (fn [[a b]] (> b a)) xs))

(defn all-decreasing?
  [xs]
  (every? (fn [[a b]] (< b a)) xs))

(defn obey-limits?
  [xs]
  (every? (fn [[a b]] (< (abs (- a b)) 4)) xs))

(defn safe?
  [xs]
  (->> xs
       (#(map vector (butlast %) (rest %)))
       (#(and (or (all-increasing? %)
                  (all-decreasing? %))
              (obey-limits? %)))))

(defn solve-p1!
  []
  (->> (read-input!)
       (filter safe?)
       count))

(defn prepare-alternatives
  [xs]
  (loop [acc [xs]
         i 0]
    (if (= i (count xs))
      acc
      (recur (conj acc (concat (take i xs) (drop (inc i) xs)))
             (inc i)))))

(defn solve-p2!
  []
  (->> (read-input!)
       (map prepare-alternatives)
       (filter #(some safe? %))
       count))

(comment
  (solve-p1!)
  ;;=> 502
  
  (solve-p2!)
  ;;=> 544

  )