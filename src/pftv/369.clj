(ns pftv.369)

(defn depth [x]
  (if (coll? x)
    (+ 1 (reduce max 0 (map depth x)))
    0))
