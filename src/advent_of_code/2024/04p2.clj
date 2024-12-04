(ns advent-of-code.2024.04p2
  (:require
   [advent-of-code.2024.04p1 :refer [read-input!]]))

(defn- ->next-coord
  [size prev-coord]
  (let [[[_ _ [x y]] _] prev-coord]
    (cond
      (and (= x (dec size))
           (= y (dec size)))
      nil

      (= y (dec size))
      (let [[[[c1x1 _] [c1x2 _] [c1x3]]
             [[c2x1 _] [c2x2 _] [c2x3]]] prev-coord]
        [[[(inc c1x1) 0] [(inc c1x2) 1] [(inc c1x3) 2]]
         [[(inc c2x1) 0] [(inc c2x2) 1] [(inc c2x3) 2]]])

      :else
      (let [[c1 c2] prev-coord]
        [(map (fn [[x y]] [x (inc y)]) c1)
         (map (fn [[x y]] [x (inc y)]) c2)]))))

(defn- ->coords
  ([size]
   (let [initial-coord [[[0 0] [1 1] [2 2]]
                        [[2 0] [1 1] [0 2]]]]
     (lazy-seq (cons initial-coord
                     (->coords size initial-coord)))))
  ([size prev-coord]
   (when-let [coord (->next-coord size prev-coord)]
     (lazy-seq (cons coord
                     (->coords size coord))))))

(defn- coord->word
  [input coord]
  (map (fn [[x y]] (nth (nth input x) y)) coord))

(defn- coord-valid?
  [input [c1 c2]]
  (let [w1 (coord->word input c1)
        w1' (reverse w1)
        w2 (coord->word input c2)
        w2' (reverse w2)]
    (and
     (or (= w1 '(\M \A \S))
         (= w1' '(\M \A \S)))
     (or (= w2 '(\M \A \S))
         (= w2' '(\M \A \S))))))

(defn- solve-p2
  [input]
  (->> input
       count
       ->coords
       (filter (partial coord-valid? input))
       count))

(comment
  (def input (read-input!))
  (solve-p2 input)
  ;;=> 1978
  
  )