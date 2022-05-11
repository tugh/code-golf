(ns pftv.466)

(defn sort-with
  [order coll]
  (let [index (zipmap order (range (count order)))
        sort-fn #(get index %)]
    (sort-by sort-fn coll)))
