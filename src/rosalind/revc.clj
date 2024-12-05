(ns rosalind.revc
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(defn complement-dna-protein
  [x] )

(->> "rosalind/revc"
     io/resource
     slurp
     reverse
     (map {\A \T
           \T \A
           \G \C
           \C \G})
     str/join)