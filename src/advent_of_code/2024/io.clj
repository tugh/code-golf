(ns advent-of-code.2024.io
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(defn read-input!
  [f]
  (->> f
       (str "advent-of-code/2024/")
       io/resource
       slurp))

(defn read-input-as-lines!
  [f]
  (-> f
      read-input!
      str/split-lines))
