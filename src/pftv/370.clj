(ns pftv.370
  (:require
   [clojure.string :as str]))

(defn ->hashtags [headline]
  (->> (str/split headline #" |-")
       (sort-by count >)
       (take 3)
       (map #(str "#" %))
       (map str/lower-case)))
