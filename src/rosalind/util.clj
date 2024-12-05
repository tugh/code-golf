(ns rosalind.util
  (:require
   [clojure.string :as str]))


(defn read-fasta
  [s]
  (->> (str/split s #">")
       rest
       (map str/split-lines)
       (map #(let [[label & data] %
                   data (apply str data)]
               [label data]))))
