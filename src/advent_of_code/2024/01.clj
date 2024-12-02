(ns advent-of-code.2024.01
  (:require
   [advent-of-code.2024.io :as io]
   [clojure.string :as str]))

(defn solve-p1!
  []
  (->> "01"
       io/read-input-as-lines!
       (map #(str/split % #"\s+"))
       (map (fn [[x y]]
              [(Integer/parseInt x)
               (Integer/parseInt y)]))
       (apply mapv vector)
       (map sort)
       (apply mapv vector)
       (map (fn [[x y]] (abs (- x y))))
       (apply +)))

(comment
  (solve-p1!)
  ;;=> 1222801

  )
