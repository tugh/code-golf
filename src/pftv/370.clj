(ns pftv.370)

(defn ->hashtags [headline]
  (->> (clojure.string/split headline #" |-")
       (sort-by count >)
       (take 3)
       (map #(str "#" %))
       (map clojure.string/lower-case)))
