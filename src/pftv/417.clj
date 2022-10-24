(ns pftv.417)

(defn index-map
  [v]
  (->> v
       (map vector (range))
       (group-by second)
       (map (fn [[x y]] [x (->> y (map first) set)]))
       (into {})))
