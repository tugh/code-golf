(ns rosalind.dna
  (:require
   [clojure.java.io :as io]))

(-> "rosalind/dna"
    io/resource
    slurp
    frequencies
    ((juxt #(get % \A) #(get % \C) #(get % \G) #(get % \T))))