(ns advent-of-code.2023.io
  (:require
   [clojure.java.io :as io]))

(defn read-input!
  [f]
  (->> f
       (str "advent-of-code/2023/")
       io/resource
       slurp))
