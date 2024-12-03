(ns advent-of-code.2024.03
  (:require
   [advent-of-code.2024.io :as io]))

(defn solve-p1
  [input]
  (->> input
       (re-seq #"mul\((\d+)\,(\d+)\)")
       (map (fn [[_ x y]] (* (Integer/parseInt x) (Integer/parseInt y))))
       (apply +)))

(comment
  (-> (io/read-input! "03")
      solve-p1)
  ;;=> 157621318
  
  )