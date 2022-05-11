(ns advent-of-code.2021.02
  (:require
   [advent-of-code.2021.io :refer [read-input!]]
   [clojure.string :as string]))

; TODO

(defn normalize
  [input]
  (map (fn [x]
         (let [[direction length] (string/split x #" ")]
           [(keyword direction)
            (Integer/parseInt length)]))
       input))

(defn solve
  [data]
  (let [horizontal (filter #(= (first %) :forward) data)
        vertical   nil #_(filter #())]))

(comment
  (-> "02"
      read-input!
      normalize
      solve)
  )