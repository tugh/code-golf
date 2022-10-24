(ns pftv.417
  (:require
   [clojure.set :as set]))

(defn index-map
  [v]
  (->> v
       (map vector (range))
       (group-by second)
       (map (fn [[x y]] [x (->> y (map first) set)]))
       (into {})))

(defn index-map-v2
  [v]
  (loop [[x & v] v
         i 0
         acc {}]
    (if (nil? x)
      acc
      (recur v (inc i) (update acc x set/union #{i})))))
