(ns pftv.373)

(defn cols
  [m]
  (apply mapv vector m))

(defn diag
  [m]
  (loop [acc []
         m m]
    (if (empty? m)
      acc
      (recur (->> m
                  first
                  first
                  (conj acc))
             (->> m
                  rest
                  (map rest))))))

(defn diags [m]
  [(diag m)
   (diag (->> m
              (map reverse)
              reverse))])

(defn winner
  [board]
  (loop [[line & lines] (apply conj
                               board
                               (cols board)
                               (diags board))]
    (if (empty? line)
      :draw
      (if (every? #(= % (first line)) line)
        (first line)
        (recur lines)))))

