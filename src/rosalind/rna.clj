(ns rosalind.rna
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(->> "rosalind/rna"
     io/resource
     slurp
     (map #(if (= % \T)
             \U
             %))
     str/join)