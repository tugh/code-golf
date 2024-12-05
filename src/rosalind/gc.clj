(ns rosalind.gc
  (:require
   [clojure.java.io :as io]
   [rosalind.util :as util]))

(defn ->gc-content
  [data]
  (let [freqs (frequencies data)]
    (double (/ (+ (freqs \G)
                  (freqs \C))
               (->> freqs
                    vals
                    (apply +))))))

(->> "rosalind/gc"
     io/resource
     slurp
     util/read-fasta
     (map (fn [[label data]] [label (->gc-content data)]))
     (sort-by second)
     last
     (#(do
         (println (first %))
         (println (second %)))))