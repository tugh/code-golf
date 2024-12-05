(ns rosalind.hamm
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(defn hamming-distance
  [dna1 dna2]
  (->> (map #(not= %1 %2) dna1 dna2)
       (filter true?)
       count))

(->> "rosalind/hamm"
     io/resource
     slurp
     str/split-lines
     (apply hamming-distance))