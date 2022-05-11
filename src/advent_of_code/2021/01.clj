(ns advent-of-code.2021.01
  (:require 
   [advent-of-code.2021.io :refer [read-input!]]
   [clojure.string :as string]))

(defn normalize
  [input]
  (map #(Integer/parseInt %)
       (string/split input #"\n")))

(defn increased?
  [[x y]]
  (> y x))

(defn solve-part1
  [data]
  (->> data
       (partition 2 1)
       (filter increased?)
       count))

(defn solve-part2
  [data]
  (->> data
       (partition 3 1)
       (map (partial reduce +))
       solve-part1))

(comment
  
  (-> (read-input! "01")
      (normalize)
      solve-part1)

  (-> (read-input! "01")
      (normalize)
      solve-part2)
  )
