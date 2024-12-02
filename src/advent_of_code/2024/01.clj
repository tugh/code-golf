(ns advent-of-code.2024.01
  (:require
   [advent-of-code.2024.io :as io]
   [clojure.string :as str]))

(defn read-input!
  []
  (->> "01"
       io/read-input-as-lines!
       (map #(str/split % #"\s+"))
       (map (fn [[x y]]
              [(Integer/parseInt x)
               (Integer/parseInt y)]))))

(defn solve-p1!
  []
  (->> (read-input!)
       (apply mapv vector)
       (map sort)
       (apply mapv vector)
       (map (fn [[x y]] (abs (- x y))))
       (apply +)))

(defn solve-p2!
  []
  (let [[items items'] (->> (read-input!)
                            (apply mapv vector))
        freqs (frequencies items')]
    (->> items
         (map (fn [x]
                (if-let [occ (get freqs x)]
                  (* x occ)
                  0)))
         (apply +))))

(comment
  (solve-p1!)
  ;;=> 1222801

  (solve-p2!)
  ;;=> 22545250

  )
