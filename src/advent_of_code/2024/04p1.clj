(ns advent-of-code.2024.04p1
  (:require
   [advent-of-code.2024.io :as io]))

(defn read-input!
  []
  (->> (io/read-input-as-lines! "04")
       (mapv vec)))

; --- ;

(defn- ->next-horizontal-coord
  [size coord]
  (let [[_ _ _ [x y]] coord]
    (cond
      (and
       (= x (dec size))
       (= y (dec size)))
      nil

      (= y (dec size))
      (let [x-coord (inc (ffirst coord))]
        [[x-coord 0] [x-coord 1] [x-coord 2] [x-coord 3]])

      :else
      (mapv (fn [[x y]] [x (inc y)]) coord))))

(defn- ->horizontal-coords
  ([size]
   (let [initial-coord [[0 0] [0 1] [0 2] [0 3]]]
     (lazy-seq (cons initial-coord
                     (->horizontal-coords size initial-coord)))))
  ([size prev-coord]
   (when-let [coord (->next-horizontal-coord size prev-coord)]
     (lazy-seq (cons coord
                     (->horizontal-coords size coord))))))

; --- ;

(defn- ->next-vertical-coord
  [size coord]
  (let [[_ _ _ [x y]] coord]
    (cond
      (and
       (= x (dec size))
       (= y (dec size)))
      nil
      
      (= x (dec size))
      (let [y-coord (inc y)]
        [[0 y-coord] [1 y-coord] [2 y-coord] [3 y-coord]])
      
      :else
      (mapv (fn [[x y]] [(inc x) y]) coord))))

(defn- ->vertical-coords
  ([size]
   (let [initial-coord [[0 0] [1 0] [2 0] [3 0]]]
     (lazy-seq (cons initial-coord
                     (->vertical-coords size initial-coord)))))
  ([size prev-coord]
   (when-let [coord (->next-vertical-coord size prev-coord)]
     (lazy-seq (cons coord
                     (->vertical-coords size coord))))))

; --- ;

; from top-left to bottom-right
(defn- ->next-diagonal-1-coord
  [size coord]
  (let [[_ _ _ [x3 y3]] coord]
    (cond
      (and
       (= x3 (dec size))
       (= y3 (dec size)))
      nil
      
      (= y3 (dec size))
      (let [[[x0 _] [x1 _] [x2 _] [x3 _]] coord]
        [[(inc x0) 0] [(inc x1) 1] [(inc x2) 2] [(inc x3) 3]])
      
      :else
      (mapv (fn [[x y]] [x (inc y)]) coord))))

; from top-left to bottom-right
(defn- ->diagonal-1-coords
  ([size]
   (let [initial-coord [[0 0] [1 1] [2 2] [3 3]]]
     (lazy-seq (cons initial-coord
                     (->diagonal-1-coords size initial-coord)))))
  ([size prev-coord]
   (when-let [coord (->next-diagonal-1-coord size prev-coord)]
     (lazy-seq (cons coord
                     (->diagonal-1-coords size coord))))))

; --- ;

; from bottom-left to top right
(defn- ->next-diagnol-2-coord
  [size coord]
  (let [[[x0 _] _ _ [_ y3]] coord]
    (cond
      (and
       (= x0 (dec size))
       (= y3 (dec size)))
      nil
      
      (= y3 (dec size))
      (let [[[x0 _] [x1 _] [x2 _] [x3 _]] coord]
        [[(inc x0) 0] [(inc x1) 1] [(inc x2) 2] [(inc x3) 3]])
      
      :else
      (mapv (fn [[x y]] [x (inc y)]) coord))))

; from bottom-left to top right
(defn- ->diagonal-2-coords
  ([size]
   (let [initial-coord [[3 0] [2 1] [1 2] [0 3]]]
     (lazy-seq (cons initial-coord
                     (->diagonal-2-coords size initial-coord)))))
  ([size prev-coord]
   (when-let [coord (->next-diagnol-2-coord size prev-coord)]
     (lazy-seq (cons coord
                     (->diagonal-2-coords size coord))))))

; --- ;

(defn- coord-valid?
  [input coord]
  (let [w1 (map (fn [[x y]] (nth (nth input x) y)) coord)
        w2 (reverse w1)]
    (+
     (if (= w1 '(\X \M \A \S))
       1 0) ; todo: do we really need this?
     (if (= w2 '(\X \M \A \S))
       1 0)))) ; todo: do we really need this?

(defn- solve-p1
  [input]
  (->> input
       count
       ((juxt ->horizontal-coords ->vertical-coords ->diagonal-1-coords ->diagonal-2-coords))
       (map #(->> %
                  (map (partial coord-valid? input))
                  (apply +)))
       (apply +)))

(comment
  (def input (read-input!))

  (solve-p1 input)
  ;;=> 2583

  )